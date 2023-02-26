package com.naze.todayfeeling.presentation.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naze.todayfeeling.databinding.ListItemClinicBinding
import com.naze.todayfeeling.databinding.ListItemForestBinding
import com.naze.todayfeeling.databinding.ListItemLoadingBinding
import com.naze.todayfeeling.databinding.ListItemReportBinding
import com.naze.todayfeeling.domain.model.ClinicData
import com.naze.todayfeeling.domain.model.ForestData
import com.naze.todayfeeling.domain.model.ReportData
import com.naze.todayfeeling.util.ItemDiffCallback
import com.naze.todayfeeling.util.LoadingViewHolder
import java.util.Calendar

class ReportAdapter(
    items: ArrayList<ReportData?>?
): ListAdapter<ReportData, RecyclerView.ViewHolder>(
    ItemDiffCallback<ReportData>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.id == new.id}
    )
) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private var filteredList = items

    inner class ReportViewHolder(private val binding: ListItemReportBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(report: ReportData) {
                binding.report = report
                binding.btnFavoriteReport.setOnClickListener {
                    binding.btnFavoriteReport.run {
                        this.isSelected = !this.isSelected
                    }
                }
            }
        }

    override fun getItemViewType(position: Int): Int {
        return when (filteredList?.get(position)) {
            null -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemReportBinding.inflate(layoutInflater, parent, false)
                ReportViewHolder(binding)
            }
           else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReportViewHolder) {
            val item = filteredList?.get(position)
            val itemHolder = holder as ReportViewHolder
            itemHolder.bind(item!!)
        } else if (holder is LoadingViewHolder) {

        }
    }

    override fun getItemCount(): Int {
        return if (filteredList == null ) {
            0
        } else {
            filteredList?.size!!
        }
    }

    fun updateItem(list:ArrayList<ReportData?>?) {
        this.filteredList = list
    }
}