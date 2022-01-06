package org.sinou.android.sandbox.course.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModel(finalScore: Int) : ViewModel() {

    private val TAG = "ScoreViewModel"

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        Log.i(TAG, "Creating VM, final score: ${finalScore}")
        _score.value = finalScore
    }

    // Manage an observable "play again event" flag to trigger navigation when it changes
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    fun startNewGame(){
        _eventPlayAgain.value = true
    }

    fun navigationToNewGameDone(){
        _eventPlayAgain.value = false
    }
}


