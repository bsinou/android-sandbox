package org.sinou.android.sandbox.basics.compose04.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class WellnessViewModel() : ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks : List<WellnessTask>
        get() = _tasks

    fun remove (task: WellnessTask){
        _tasks.remove(task)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }

    // Helpers:
    // dummy data
    private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

}


