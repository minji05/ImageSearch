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
import com.example.imagesearch.Utils.getDateFromTimestampWithFormat
import com.example.imagesearch.databinding.SearchItemBinding


class SearchAdapter(private val mContext: Context) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    var items = ArrayList<SearchModel>()


    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(mContext)
            .load(currentItem.uri)
            .into(holder.imgThumbnail)

        holder.imgLike.visibility = if (currentItem.like) View.VISIBLE else View.INVISIBLE
        holder.txtTitle.text = currentItem.title
        holder.txtDateTime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var imgThumbnail: ImageView = binding.imgThumbnail
        var imgLike: ImageView = binding.imgLike
        var txtTitle: TextView = binding.txtTitle
        var txtDateTime: TextView = binding.txtDateTime
        var thumbItem: ConstraintLayout = binding.thumbItem

        init {
            imgLike.visibility = View.GONE
            imgThumbnail.setOnClickListener(this)
            thumbItem.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            item.like = !item.like

            if (item.like) {
                (mContext as MainActivity).addLikedItem(item)
            } else {
                (mContext as MainActivity).removeLikedItem(item)
            }

            notifyItemChanged(position)
        }
    }
}