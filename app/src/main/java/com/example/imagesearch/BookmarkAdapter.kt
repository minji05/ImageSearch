import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.SearchModel
import com.example.imagesearch.Utils
import com.example.imagesearch.databinding.SearchItemBinding

class BookmarkAdapter(private val context: Context) :
    RecyclerView.Adapter<BookmarkAdapter.ItemViewHolder>() {

    var items: MutableList<SearchModel> = mutableListOf()
    private var itemClickListener: ((SearchModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context)
            .load(item.uri)
            .into(holder.binding.imgThumbnail)

        holder.binding.txtTitle.text = item.title
        holder.binding.txtDateTime.text = Utils.getDateFromTimestampWithFormat(
            item.dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )

        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(newItems: List<SearchModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: (SearchModel) -> Unit) {
        itemClickListener = listener
    }

    inner class ItemViewHolder(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root)
}
