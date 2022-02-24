package com.example.spacexassignment.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.spacexassignment.R
import com.example.spacexassignment.common.base.ui.BaseRvAdapter
import com.example.spacexassignment.common.utils.ext.formatToDateDefaults
import com.example.spacexassignment.common.utils.ext.formatToTimeDefaults
import com.example.spacexassignment.common.utils.ext.inflateRvItem
import com.example.spacexassignment.databinding.ItemLaunchBinding
import com.example.spacexassignment.model.network.responses.launches.LaunchesResponseItem
import java.util.*


class LaunchesAdapter(
    val clickListener: (LaunchesResponseItem) -> Unit
) : BaseRvAdapter<LaunchesResponseItem, LaunchesAdapter.LaunchViewHolder>() {

    inner class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLaunchBinding.bind(itemView)
        fun bindView(data: LaunchesResponseItem) {
            binding.card.setOnClickListener {
                clickListener.invoke(data)
            }
            binding.ivPatch.load(data.links.missionPatch)
            binding.tvMissionName.text = "Mission: ${data.missionName}"
            binding.tvRocketType.text =
                "Rocket: ${data.rocket.rocketName} / ${data.rocket.rocketType}"
            binding.tvDateTime.text =
                "Date/Time: ${data.launchDateUtc.formatToDateDefaults()} at ${data.launchDateUtc.formatToTimeDefaults()}"
            if (data.launchSuccess) {
                binding.ivLaunchStatus.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
            } else {
                binding.ivLaunchStatus.setImageResource(R.drawable.ic_baseline_highlight_off_24)
            }

            val diff: Long = Calendar.getInstance().timeInMillis - data.launchDateUtc.time
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24

            if (days > 0) {
                binding.tvDays.text = "Since $days days"
            } else {
                binding.tvDays.text = "$days from now "
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(parent.inflateRvItem(R.layout.item_launch))
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}