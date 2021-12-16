package org.sinou.android.sandbox.course.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val TAG = "GameViewModel"

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        //const val COUNTDOWN_TIME = 60000L
        const val COUNTDOWN_TIME = 3000L
    }

    private val _timer: CountDownTimer

    private val _timeRemaining = MutableLiveData<String>()
    val timeRemaining: LiveData<String>
        get() = _timeRemaining

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean>
        get() = _eventGameFinished


    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        Log.i(TAG, "GameViewModel created!")
        resetList()
        nextWord()
        _eventGameFinished.value = false
        _score.value = 0

        _timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _timeRemaining.value = DateUtils.formatElapsedTime(millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _eventGameFinished.value = true
            }
        }.start()

        _timeRemaining.value = DateUtils.formatElapsedTime(COUNTDOWN_TIME / ONE_SECOND)
    }

    override fun onCleared() {
        super.onCleared()
        _timer.cancel()
        Log.i(TAG, "GameViewModel destroyed!")
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    // No timer option
//    private fun nextWord() {
//        //Select and remove a word from the list
//        if (wordList.isEmpty()) {
//            _eventGameFinished.value = true
//        } else {
//            _word.value = wordList.removeAt(0)
//        }
//    }


    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    fun onGameFinishComplete() {
        _eventGameFinished.value = false
    }
}
