package org.sinou.android.sandbox.basics.compose04.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sinou.android.sandbox.basics.compose04.ui.theme.Compose04BasicStateTheme


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ }, modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

/**
 * Note: It's a good practice to provide a default Modifier to all composable functions,
 * as it increases re-usability.
 * It should appear as the first optional parameter in the parameter list,
 * after all required parameters.
 */
@Composable
fun WaterCounter_FirstHops(modifier: Modifier = Modifier, initialCount: Int = 0) {

    // Outside compose
    // var count = 0
    // Transient: Changes to count are now tracked by Compose but forgotten after recomposition
    var count by remember {
        mutableStateOf(initialCount)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {

        //  Text("You've had $count glasses.")
        if (count > 0) {

            var showTask by remember {
                mutableStateOf(true)
            }

            var checked by remember {
                mutableStateOf(false)
            }

            // This text is present if the button has been clicked
            // at least once; absent otherwise
            Text(
                text = "You've had $count glasses.",
                modifier = Modifier
                    .fillMaxWidth()
            )
            if (showTask) {
                WellnessTaskItem(
                    taskName = "This is a task",
                    checked = checked,
                    onCheckedChange = { checked = it },
                    onClose = { showTask = false })
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Button(
                onClick = { count++ },
                enabled = count < 10,
            ) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                enabled = count > 0,
                modifier = Modifier.padding(start = 8.dp),
            ) {
                Text("Clear water count")
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun WaterCounter_FirstHopsPreview() {
    Compose04BasicStateTheme() {
        WaterCounter_FirstHops(Modifier, 3)
    }
}

