package com.naze.todayfeeling.presentation.clinic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naze.todayfeeling.databinding.ListItemClinicBinding
import com.naze.todayfeeling.databinding.ListItemLoadingBinding
import com.naze.todayfeeling.domain.model.ClinicData
import com.naze.todayfeeling.util.ItemDiffCallback
import com.naze.todayfeeling.util.LoadingViewHolder
import java.util.Calendar

class ClinicAdapter(
    items: ArrayList<ClinicData?>?
): ListAdapter<ClinicData, RecyclerView.ViewHolder>(
    ItemDiffCallback<ClinicData>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.id == new.id}
    )
) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private var filteredList = items

    inner class ClinicViewHolder(private val binding: ListItemClinicBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(clinic: ClinicData) {
                binding.clinic = clinic
                binding.btnFavoriteClinic.text = if (clinic.favCount > 99 ) "좋아요 99+" else "좋아요 ${clinic.favCount}"
                binding.btnFavoriteClinic.setOnClickListener {
                    binding.btnFavoriteClinic.run {
                        this.isSelected = !this.isSelected
                        if (this.isSelected) {
                            if (clinic.favCount + 1 > 99) {
                                this.text = "좋아요 99+"
                            } else {
                                this.text = "좋아요 ${clinic.favCount + 1}"
                            }
                        }
                        else {
                            if (clinic.favCount > 99) {
                                this.text = "좋아요 99+"
                            } else {
                                this.text = "좋아요 ${clinic.favCount }"
                            }
                        }
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
                val binding = ListItemClinicBinding.inflate(layoutInflater, parent, false)
                ClinicViewHolder(binding)
            }
           else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ClinicViewHolder) {
            val item = filteredList?.get(position)
            val itemHolder = holder as ClinicViewHolder
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

    fun updateItem(list:ArrayList<ClinicData?>?) {
        this.filteredList = list
    }
}