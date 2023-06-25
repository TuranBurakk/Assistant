package com.infos.assistant.ui.home_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.infos.assistant.data.MessageDataRv
import com.infos.assistant.databinding.MessagesRowBinding
import com.infos.assistant.databinding.ResponseRowBinding


class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messageList = listOf<MessageDataRv>()

    class UserMessageViewHolder(val binding:MessagesRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    class BotMessageViewHolder(val binding:ResponseRowBinding) : RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ViewBinding
        return if(viewType == 0){
            binding = MessagesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            UserMessageViewHolder(binding)
        }else{
            binding = ResponseRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            BotMessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sender = messageList[position].sender
        when(sender){
            "user" -> (holder as UserMessageViewHolder).binding.messageTv.text = messageList[position].message
            "bot" -> (holder as BotMessageViewHolder).binding.botTv.text = messageList[position].message
        }
    }

    override fun getItemCount() = messageList.size

    override fun getItemViewType(position: Int): Int {
        return  when(messageList[position].sender){
            "user" -> 0
            "bot" -> 1
            else -> 1
        }
    }
    fun setData(newList: List<MessageDataRv>) {
        messageList = newList
        notifyDataSetChanged()
    }
}
