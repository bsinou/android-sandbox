package org.sinou.android.sandbox.basics.compose04.model

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sinou.android.sandbox.basics.compose04.ui.WellnessTaskItem


@Composable
fun WellnessTaskList(
    tasks: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(
            items = tasks,

/*
* Note: The items method receives a key parameter.
* By default, each item's state is keyed against the position of the item in the list.
*
* In a mutable list, this causes issues when the data set changes,
* since items that change position effectively lose any remembered state.
*
* You can easily fix this by using the id of each WellnessTaskItem as the key for each item.
* More info there: https://developer.android.com/jetpack/compose/lists
*/
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(taskName = task.label, onClose = { onCloseTask(task) })
        }
    }
}

/**
 * NOTE: see implementation of LazyColumn.
 * @Composable fun LazyColumn(
 * ...
 * state: LazyListState = rememberLazyListState(),
 * ...
 *
 * The composable function rememberLazyListState creates an initial state
 * for the list using rememberSaveable. When the Activity is recreated,
 * the scroll state is maintained without you having to code anything.
 *
 * Many apps need to react and listen to scroll position, item layout changes,
 * and other events related to the list's state.
 * Lazy components, like LazyColumn or LazyRow, support this use case through
 * hoisting the LazyListState.
 *
 * You can learn more about this pattern in the documentation for state in lists.
 *
 * Having a state parameter with a default value provided by a public rememberX
 * function is a common pattern in built-in composable functions.
 * Another example can be found in Scaffold, which hoists state using rememberScaffoldState.
 */
//@Composable
//fun WellnessTaskList(
//    modifier: Modifier = Modifier,
//    tasks: List<WellnessTask> = remember { getWellnessTasks() }
//) {
//    LazyColumn() {
//        items(tasks) { task ->
//            WellnessTaskItem(taskName = task.label, onClose = { /*TODO*/ })
//        }
//    }
//}

// Helpers
// private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
