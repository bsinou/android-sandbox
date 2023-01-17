package org.sinou.android.sandbox.basics.compose02

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Profile screen")
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = painterResource(id = R.drawable.guy1)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(text = "Alex", fontSize = 30.sp)
        }
        Text(text = "User description and information")
        StateExample()
    }
}

/** 
Simple example to play with State and derived state of, see:
https://www.youtube.com/watch?v=yJheLbxYd10
 */

@Composable
private fun StateExample() {

    var count by remember { mutableStateOf(0) }
    // Log.d("DEBUG", "READ - Count > 2:  ${count > 2}")

    val calculation by remember {
        derivedStateOf {
            Log.d("DEBUG", "Calculing")
            count > 2
        }
    }

    Log.d("DEBUG", "Current calculated assertion: $calculation")


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                Log.d("DEBUG", "Clicked")
                count += 1
            }
        ) {
            Text(text = "Increment")
        }
       //  Text(text = "Current count: $count")
    }

    Log.d("DEBUG", ".... Done")

}