package org.sinou.android.sandbox.basics.compose04.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sinou.android.sandbox.basics.compose04.model.WellnessTask
import org.sinou.android.sandbox.basics.compose04.model.WellnessTaskList
import org.sinou.android.sandbox.basics.compose04.ui.theme.Compose04BasicStateTheme

/**
 * Interesting notes from the codelab:
 *
 * Note:
 * If you type WC in the editor area in Android Studio, it opens a suggestion box.
 * If you press Enter and select the first option, a Column template appears ready to use.
 *
 * More about this at https://developer.android.com/jetpack/compose/tooling#editor-actions .
 */
@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        // First Pass
        WaterCounter_FirstHops(modifier, 0)

        Divider(
            modifier = Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.primary)
        )

        // Second Pass
        StatefulCounter(modifier)

        val list = remember { getWellnessTasks().toMutableStateList() }
        /*
        * Warning: You can use the mutableStateListOf API instead to create the list.
        * However, the way you use it might result in unexpected recomposition
        * and suboptimal UI performance.
        *
        * If you just define the list and then add the tasks in a different operation
        * it would result in duplicated items being added for every recomposition:
        *
        * // Don't do this!
        * val list = remember { mutableStateListOf<WellnessTask>() }
        * list.addAll(getWellnessTasks())
        *
        * Instead, create the list with its initial value in a single operation
        * and then pass it to the remember function, like this:
        *
        * // Do this instead. Don't need to copy
        * val list = remember {
        * mutableStateListOf<WellnessTask>().apply { addAll(getWellnessTasks()) }
        * }
        *
        */

        WellnessTaskList(tasks = list, onCloseTask = { task -> list.remove(task) })
    }
}

@Preview
@Composable
fun WellnessScreenPreview() {
    Compose04BasicStateTheme {
        WellnessScreen()
    }
}

// Helpers
private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
