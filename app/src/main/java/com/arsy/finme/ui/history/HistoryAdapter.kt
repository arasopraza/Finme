package com.arsy.finme.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsy.finme.data.source.local.entity.IncomeEntity
import com.arsy.finme.databinding.ListItemsBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    private var listHistories = ArrayList<IncomeEntity>()

    fun setItems(items: List<IncomeEntity>) {
        if (items == null) return
        this.listHistories.clear()
        this.listHistories.addAll(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val itemsBinding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val items = listHistories[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return listHistories.size
    }

    class HistoryViewHolder(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: IncomeEntity) {
            with(binding) {
                tvNameRecord.text = items.nameIncome
                tvDateRecord.text = items.dateIncome
                tvAmount.text = items.amountIncome.toString()
                tvCategory.text = items.category
            }
        }
    }
}