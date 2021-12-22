/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sinou.android.sandbox.course.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import org.sinou.android.sandbox.course.guesstheword.R
import org.sinou.android.sandbox.course.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private val TAG = "GameFragment"

    private val viewModel: GameViewModel by viewModels()

    // Keep state between config changes using bundle
    // Was the legacy way of doing things
    // Might prove useful in certain corner case.
    // private val KEY_SCORE = "GameFragment.score"
    // private val KEY_CURR_WORD = "GameFragment.CurrentWord"
    // private val KEY_WORD_LIST = "GameFragment.WordList"

    // Binding to ease access to the views, also much more cost efficient
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "in onCreateView")

        setHasOptionsMenu(true)

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        // This does not work anymore with recent version of the LifeCycle lib
        // viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        // Variant that use bundle
        // if (savedInstanceState != null){
        //     score = savedInstanceState.getInt(KEY_SCORE, 0)
        //     word = savedInstanceState.getString(KEY_CURR_WORD, "")
        //     val tmp = savedInstanceState.getStringArrayList(KEY_WORD_LIST)
        //     if (tmp != null && !tmp.isEmpty()){
        //         wordList = tmp.toMutableList()
        //     } else {
        //         wordList = mutableListOf()
        //     }
        // } else {
        //     resetList()
        //     nextWord()
        // }

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.score.observe(this, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.word.observe(this, Observer { newWord ->
            binding.wordText.text = newWord
        })
        viewModel.timeRemaining.observe(this, Observer { value ->
            binding.timerText.text = value
        })
        viewModel.eventGameFinished.observe(this, Observer { flag ->
            Log.i(TAG, "eventGameFinished.observedb")
            if (flag) {
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        })

        return binding.root
    }

    // override fun onSaveInstanceState(outState: Bundle) {
    //     super.onSaveInstanceState(outState)
    //     outState.putInt(KEY_SCORE, score)
    //     outState.putString(KEY_CURR_WORD, word)
    //
    //     val tmpList = ArrayList<String>();
    //     tmpList.addAll(wordList);
    //     outState.putStringArrayList(KEY_WORD_LIST, tmpList)
    // }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        binding.gameLayout.findNavController().navigate(action)
        // Toast.makeText(this.activity,"No more words", Toast.LENGTH_SHORT).show()
    }

}
