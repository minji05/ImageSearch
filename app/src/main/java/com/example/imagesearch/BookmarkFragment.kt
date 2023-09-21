//package com.example.imagesearch
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import com.example.imagesearch.databinding.FragmentBookmarkBinding
//
//class BookmarkFragment : Fragment() {
//
//    private lateinit var contexts: Context
//
//    private var binding: FragmentBookmarkBinding? = null
//    private lateinit var adapter: BookmarkAdapter
//
//
//    private var bookmarkeList: List<SearchModel> = listOf()
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        contexts = context
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val mainActivity = activity as MainActivity
//        bookmarkeList = mainActivity.bookmarkeList
//
//        adapter = BookmarkAdapter(contexts).apply {
//            items = bookmarkeList.toMutableList()
//        }
//
//        // 바인딩 및 RecyclerView 설정
//        binding = FragmentBookmarkBinding.inflate(inflater, container, false).apply {
//            bookmarkRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            bookmarkRecyclerView.adapter = adapter
//        }
//
//        return binding?.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
//}
//
//
