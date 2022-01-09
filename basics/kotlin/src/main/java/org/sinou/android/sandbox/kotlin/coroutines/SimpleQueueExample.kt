package org.sinou.android.sandbox.kotlin.coroutines

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun orderThumbDL(url: String, queue: Channel<String>) {
    println("DL Thumb for $url")
    queue.send(url)
}

suspend fun processThumbDL(queue: Channel<String>, ackQueue: Channel<Boolean>) {
    for (msg in queue) { // iterate over incoming messages
        when (msg) {
            "done" -> {
                println("Received done message, forwarding to done channel.")
                ackQueue.send(true)
                return
            }
            else ->  {
                delay(
                    500L)
                println("Downloaded thumb for $msg.")
            }
        }
    }
}

fun main() = runBlocking(block = {
    val terminate = Channel<Boolean>()
    val queue = Channel<String>() // a shared table
    launch { processThumbDL(queue, terminate) }

    repeat(10) {
        launch {
            val url = "https://example.com/slug/${it}"
            orderThumbDL(url, queue)
        }
    }

    // Send the ack
    launch {
        queue.send("done")
    }

    // Blocking until all message have been processed
    for (msg in terminate) {
        println("Finished processing the queue, exiting...")
        break
    }
    // Processing done
    coroutineContext.cancelChildren()
})
