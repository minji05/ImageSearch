//package com.example.imagesearch
//
//import android.content.Context
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.view.inputmethod.InputMethodManager
//import android.widget.Toast
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import com.example.imagesearch.databinding.FragmentSearchBinding
//
//class SearchFragment : Fragment() {
//
//    private lateinit var binding: FragmentSearchBinding
//    private lateinit var contexts: Context
//    private lateinit var adpater: SearchAdapter
//    private lateinit var gridmanager: StaggeredGridLayoutManager
//
//    private var resItems: ArrayList<SearchModel> = ArrayList()
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        contexts = context
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentSearchBinding.inflate(inflater, container, false)
//
//        setupViews()
//        setupListeners()
//
//        return binding.root
//    }
//
//    private  fun setupViews() {
//        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        binding.rvSearchResult.layoutManager = gridmanager
//
//        adpater = SearchAdapter(contexts)
//        binding.rvSearchResult.adpater = adapter
//        binding.rvSearchResult.itemAnimator = null
//        val lastSearch = Utils.getLastSearch(requireContext())
//        binding.etSearch.setText(lastSearch)
//    }
//
//    private fun setupListeners() {
//
//        binding.tvSearch.setOnClickListener {
//            val query = binding.erSearch.text.toString()
//            if (query.isNotEmpty()) {
//                Utils.saveLastSearch(requireContext(), query)
//                adpater.clearItem()
//                fetchImageResults(query)
//            } else {
//                Toast.makeText(context, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
//            }
//
//            val imm =
//                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//
//            imm.hideSoftInputFromWindow(binding.etSearch.windowToken,0)
//
//
//        }
//    }
//
//    private fun fetchImageResults(query: String) {
//        apiService.image_serach(Constants.AUTH_HEADER, query, "re")
//    }
//
//
//
//}