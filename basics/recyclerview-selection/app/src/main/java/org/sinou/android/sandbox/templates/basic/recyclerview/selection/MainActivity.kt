package org.sinou.android.sandbox.templates.basic.recyclerview.selection

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sinou.android.sandbox.templates.basic.recyclerview.selection.ui.main.MainAdapter
import org.sinou.android.sandbox.templates.basic.recyclerview.selection.ui.main.MyItemDetailsLookup
import java.util.*

class MainActivity : AppCompatActivity() {

    private val adapter = MainAdapter()
    private var tracker: SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.list = createRandomIntList()
        tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            recyclerView,
            MyItemKeyProvider(recyclerView),
            MyItemDetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()
        adapter.tracker = tracker
        adapter.notifyDataSetChanged()

        val changeButton = findViewById<Button>(R.id.change_button)
        changeButton.setOnClickListener {
            Log.e("MainActivity", "Clicked on change button")
            adapter.list = createRandomIntList()
            adapter.notifyDataSetChanged()
        }

        val doButton = findViewById<Button>(R.id.do_button)
        doButton.setOnClickListener {
            Log.e("MainActivity", "Clicked on do button")
           launchSum(tracker?.selection!!)
            // tracker.apply { it.t }
        }

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val items = tracker?.selection!!.size()
                    if (items == 2) {
                        launchSum(tracker?.selection!!)
                    }
                }
            })

    }

    private fun launchSum(selection: Selection<Long>) {
        val list = selection.map {
            adapter.list[it.toInt()]
        }.toList()

        for (value in list){
            Log.e("MainActivity", "- $value")
        }

        // SumActivity.launch(this, list as ArrayList<Int>)
    }

    private fun createRandomIntList(): List<Int> {
        val random = Random()
        return (1..10).map { random.nextInt() }
    }

    inner class MyItemKeyProvider(private val recyclerView: RecyclerView) :
        ItemKeyProvider<Long>(ItemKeyProvider.SCOPE_MAPPED) {

        override fun getKey(position: Int): Long? {
            return recyclerView.adapter?.getItemId(position)
        }

        override fun getPosition(key: Long): Int {
            val viewHolder = recyclerView.findViewHolderForItemId(key)
            return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
        }
    }
}
