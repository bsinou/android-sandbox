package org.sinou.android.sandbox.basics.compose04.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//data class WellnessTask(
//    val id: Int,
//    val label: String,
//    // Changes made on this won't be "observed" by the list that only "catch"
//    // addition and removal events
//    // var checked: Boolean = false,
//    val checked: MutableState<Boolean> = mutableStateOf(false)
//)

// To ease usage, we want to use a delegated property for the checked state,
// to do so we must convert the class to **NOT** be a data class (see above)

class WellnessTask(
    val id: Int,
    val label: String,
    // Changes made on this won't be "observed" by the list that only "catch"
    // addition and removal events
    // var checked: Boolean = false,
    initialChecked: Boolean = false,
) {
    var checked by mutableStateOf(initialChecked)
}
