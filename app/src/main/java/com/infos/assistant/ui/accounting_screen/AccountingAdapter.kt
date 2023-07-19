package com.infos.assistant.ui.accounting_screen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.infos.assistant.R
import com.infos.assistant.data.AccountingData
import com.infos.assistant.databinding.AccountingRowBinding

class AccountingAdapter(
    private var clickListener: IAccountingListener
): RecyclerView.Adapter<AccountingAdapter.AccountingHolder>() {
    private var list = emptyList<AccountingData>()
    class AccountingHolder(val binding : AccountingRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountingHolder {
        val binding = AccountingRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AccountingHolder(binding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: AccountingHolder, position: Int) {
        holder.binding.apply {
            amountTv.text = list[position].amount.toString()
            descTv.text = list[position].description.toString()
            dateTv.text = list[position].date.toString()

            deleteButton.setOnClickListener {
                clickListener.delete(list[position])
            }
            editButton.setOnClickListener {
                amountTv.visibility = View.GONE
                descEt.visibility = View.VISIBLE
                editButton.visibility = View.GONE
                doneButton.visibility = View.VISIBLE
            }

            doneButton.setOnClickListener {
                amountTv.visibility = View.VISIBLE
                descEt.visibility = View.GONE
                editButton.visibility = View.VISIBLE
                doneButton.visibility = View.GONE
                val amount = descEt.text.toString()
                clickListener.edit(list[position],amount.toInt())
            }
        }
            if (list[position].amount!! < 0){
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#973535"))
                holder.binding.editButton.setBackgroundColor(Color.parseColor("#973535"))
                holder.binding.deleteButton.setBackgroundColor(Color.parseColor("#973535"))
                holder.binding.doneButton.setBackgroundColor(Color.parseColor("#973535"))
            }else {

                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#4E8E2B"))
                holder.binding.editButton.setBackgroundColor(Color.parseColor("#4E8E2B"))
                holder.binding.deleteButton.setBackgroundColor(Color.parseColor("#4E8E2B"))
                holder.binding.doneButton.setBackgroundColor(Color.parseColor("#4E8E2B"))
            }


    }

    override fun getItemCount() = list.size

    fun setData(newList: List<AccountingData>) {
        list = newList
        notifyDataSetChanged()
    }
}