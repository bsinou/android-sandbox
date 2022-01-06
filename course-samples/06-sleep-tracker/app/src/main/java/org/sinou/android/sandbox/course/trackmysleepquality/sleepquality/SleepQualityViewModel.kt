/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sinou.android.sandbox.course.trackmysleepquality.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.sinou.android.sandbox.course.trackmysleepquality.database.SleepDatabaseDao

class SleepQualityViewModel(
    private val sleepNightKey: Long,
    private val database: SleepDatabaseDao
) : ViewModel() {

    // LifeCycle and scopes
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Navigation flag
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating(){
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(quality: Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                val currNight = database.get(sleepNightKey)
                currNight.sleepQuality = quality
                database.update(currNight)
            }
            _navigateToSleepTracker.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}