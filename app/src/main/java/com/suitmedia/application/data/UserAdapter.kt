package com.suitmedia.application.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.application.data.response.DataItem
import com.suitmedia.application.databinding.ItemUsersBinding

class UserAdapter(private val listUser: List<DataItem>) :  RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var itemUsersBinding: ItemUsersBinding) : RecyclerView.ViewHolder(itemUsersBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listUser[position].avatar)
            .circleCrop()
            .into(holder.itemUsersBinding.imageView)
        holder.itemUsersBinding.tvFullname.text = "${listUser[position].firstName} ${listUser[position].lastName}"
        holder.itemUsersBinding.tvEmail.text = listUser[position].email

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
}