package com.task.listofusers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class UserAdapter(private val context: Context, private val onUserClickListener: OnUserClickListener): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    private var userList: MutableList<Result> = mutableListOf()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val photo : ShapeableImageView = itemView.findViewById(R.id.photo_user)
        val name : TextView = itemView.findViewById(R.id.name_user)
        val address : TextView = itemView.findViewById(R.id.address_user)
        val phone : TextView = itemView.findViewById(R.id.phone_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = userList[position].name.first
        holder.address.text = userList[position].location.city
        holder.phone.text = userList[position].phone

        Glide.with(context)
            .load(userList[position].picture.medium)
            .into(holder.photo)

        holder.itemView.setOnClickListener {
            onUserClickListener.onClickUserPosition(position)
        }
    }

    fun updateUsers(listUsers: MutableList<Result>){
        this.userList.apply {
            clear()
            addAll(listUsers)
        }
    }

    interface OnUserClickListener{
        fun onClickUserPosition(position: Int){}
    }
}