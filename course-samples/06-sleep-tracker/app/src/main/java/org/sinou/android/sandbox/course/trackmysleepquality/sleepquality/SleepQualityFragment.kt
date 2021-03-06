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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.sinou.android.sandbox.course.trackmysleepquality.R
import org.sinou.android.sandbox.course.trackmysleepquality.database.SleepDatabase
import org.sinou.android.sandbox.course.trackmysleepquality.databinding.FragmentSleepQualityBinding

/**
 * Fragment that displays a list of clickable icons,
 * each representing a sleep quality rating.
 * Once the user taps an icon, the quality is set in the current sleepNight
 * and the database is updated.
 */
class SleepQualityFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_quality, container, false
        )

        // Do the piping
        val args: SleepQualityFragmentArgs by navArgs()
        val application = requireNotNull(this.activity).application
        val datasource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(args.sleepNightKey, datasource)
        val sleepQualityViewModel: SleepQualityViewModel by viewModels { viewModelFactory }
        binding.sleepQualityViewModel = sleepQualityViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Navigate (back?) to the tracker once the quality has been set
        sleepQualityViewModel.navigateToSleepTracker.observe(this, Observer { flag ->
            flag?.let {
                findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment()
                )
                sleepQualityViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
