import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.MainActivity
import com.example.imagesearch.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private lateinit var contexts: Context
    private var binding: FragmentBookmarkBinding? = null
    private lateinit var adapter: BookmarkAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contexts = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding!!.root
    }

    override fun onResume() {
        super.onResume()
        loadBookmarkedItems()
    }

    private fun setupRecyclerView() {
        adapter = BookmarkAdapter(contexts)
        binding?.bookmarkRecyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding?.bookmarkRecyclerView?.adapter = adapter
    }

    private fun loadBookmarkedItems() {
        val mainActivity = activity as MainActivity
        adapter.items = mainActivity.bookmarkeList.toMutableList()
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
