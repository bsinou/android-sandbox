package org.sinou.android.sandbox.course.trackmysleepquality.sleepdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sinou.android.sandbox.course.trackmysleepquality.database.SleepDatabaseDao
import org.sinou.android.sandbox.course.trackmysleepquality.database.SleepNight

/**
 * ViewModel for SleepQualityFragment.
 *
 * @param sleepNightKey The key of the current night we are working on.
 */
class SleepDetailViewModel(
        private val sleepNightKey: Long = 0L,
        dataSource: SleepDatabaseDao
) : ViewModel() {

    // Hold a reference to SleepDatabase via its SleepDatabaseDao.
    val database = dataSource


    private val night = MediatorLiveData<SleepNight>()
    fun getNight() = night

    init {
        val tmp = database.getNightWithId(sleepNightKey)
        night.addSource(tmp, night::setValue)
    }

    /**
     * Variable that tells the fragment whether it should navigate to [SleepTrackerFragment].
     *
     * This is `private` because we don't want to expose the ability to set [MutableLiveData] to
     * the [Fragment]
     */
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    // When true immediately navigate back to the [SleepTrackerFragment]
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }

}

 
