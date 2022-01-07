package org.sinou.android.sandbox.course.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sinou.android.sandbox.course.trackmysleepquality.database.SleepNight
import org.sinou.android.sandbox.course.trackmysleepquality.databinding.GridItemSleepNightBinding

class SleepNightGridAdapter :
    ListAdapter<SleepNight, SleepNightGridAdapter.GridViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        // Get via the ListAdapter to rely on DiffUtil
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder.from(parent)
    }

    class GridViewHolder private constructor(val binding: GridItemSleepNightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SleepNight) {
            binding.sleep = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GridViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                // Automatically generated at compile time
                val binding = GridItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return GridViewHolder(binding)
            }
        }
    }
}


