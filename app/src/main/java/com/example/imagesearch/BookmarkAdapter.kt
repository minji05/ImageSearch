package com.example.imagesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.databinding.SearchItemBinding
import com.example.imagesearch.Utils.getDateFromTimestampWithFormat



class BookmarkAdapter(var contexts: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<SearchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(contexts)
            .load(items[position].uri)
            .into((holder as ItemViewHolder).imgThumbnail)

        holder.txtTitle.text = items[position].title
        holder.imgLike.visibility = View.GONE
        holder.txtDateTime.text =
            getDateFromTimestampWithFormat(
                items[position].dateTime,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "yyyy-MM-dd HH:mm:ss"
            )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var imgThumbnail: ImageView = binding.imgThumbnail
        var imgLike: ImageView = binding.imgLike
        var txtTitle: TextView = binding.txtTitle
        var txtDateTime: TextView = binding.txtDateTime
        var thumbItem: ConstraintLayout = binding.thumbItem

        init {

            imgLike.visibility = View.GONE

            thumbItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

}
