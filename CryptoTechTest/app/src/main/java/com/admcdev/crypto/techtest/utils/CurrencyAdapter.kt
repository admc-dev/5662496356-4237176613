package com.admcdev.crypto.techtest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.admcdev.crypto.techtest.R
import com.admcdev.crypto.techtest.data.CurrencyInfo

class CurrencyAdapter(
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<CurrencyAdapter.DataVHolder>() {

    private val currencyList: MutableList<CurrencyInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return DataVHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataVHolder, position: Int) {

        holder.letterText.text = currencyList[position].name[0].toString()
        holder.symbolText.text = currencyList[position].symbol
        holder.nameText.text = currencyList[position].name
    }

    override fun getItemCount(): Int = currencyList.size

    inner class DataVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val letterText = itemView.findViewById(R.id.letterTextView) as TextView
        val symbolText = itemView.findViewById(R.id.symbolTextView) as TextView
        val nameText = itemView.findViewById(R.id.nameTextView) as TextView

        init {
            itemView.setOnClickListener {
                listener.itemClick(currencyList[adapterPosition])
            }
        }

    }

    fun setCurrencies(currencies: List<CurrencyInfo>) {
        currencyList.clear()
        currencyList.addAll(currencies)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun itemClick(currencyInfo: CurrencyInfo)
    }
}