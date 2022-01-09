package org.sinou.android.sandbox.kotlin.coroutines

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

sealed class DownloadThumb(var url: String?)
class LaunchThumbDL(url: String) : DownloadThumb(url)
class Done() : DownloadThumb(null)

sealed class DownloadDone
class DoneDownloading() : DownloadDone()

suspend fun orderThumbDL(url: String, queue: Channel<DownloadThumb>) {
    println("DL Thumb for $url")
    queue.send(LaunchThumbDL(url))
}

suspend fun processThumbDL(queue: Channel<DownloadThumb>, ackQueue: Channel<DownloadDone>) {
    for (msg in queue) { // iterate over incoming messages
        when (msg) {
            is LaunchThumbDL -> {
                delay(
                    500L)
                // TODO this is broken because Download thumb object is not parcelable
                //  => it prints the object pointer
                println("Downloaded thumb for $msg.")
            }
            is Done -> {
                println("Received done message, forwarding to done channel.")
                ackQueue.send(DoneDownloading())
                return
            }
        }
    }
}

fun main() = runBlocking(block = {
    val terminate = Channel<DownloadDone>()
    val queue = Channel<DownloadThumb>() // a shared table
    launch { processThumbDL(queue, terminate) }

    repeat(10) {
        launch {
            val url = "https://example.com/slug/${it}"
            orderThumbDL(url, queue)
        }
    }

    // Send the ack
    launch {
        queue.send(Done())
    }

    // Blocking until all message have been processed
    for (msg in terminate) {
        when (msg) {
            is DoneDownloading -> {
                println("Finished processing the queue, exiting...")
                break
            }
        }
    }
    // Processing done
    coroutineContext.cancelChildren()
})
