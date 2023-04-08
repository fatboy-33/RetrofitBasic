package com.example.retrofitbasic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitbasic.models.Data

class UserAdapter(private val listener : (Data) -> Unit) : ListAdapter<Data, UserAdapter.ViewHolder>(DiffUserCallBack()){

    inner class ViewHolder(private val containerView : View) : RecyclerView.ViewHolder(containerView){
        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition))
            }
        }

        fun bind(user : Data){
            containerView.findViewById<TextView>(R.id.firstName).text = user.firstName
            containerView.findViewById<TextView>(R.id.lastName).text = user.lastName
            containerView.findViewById<TextView>(R.id.email).text = user.email

            val imageView = containerView.findViewById<ImageView>(R.id.ProfileImage)
            val imageUrl = user.avatar

            Glide
                .with(imageView)
                .load(imageUrl)
                .centerCrop()
                //.circleCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUserCallBack : DiffUtil.ItemCallback<Data>(){
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}