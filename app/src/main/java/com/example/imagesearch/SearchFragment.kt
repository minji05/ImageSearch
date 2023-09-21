package com.example.imagesearch


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearch.RetrofitClient.apiService
import com.example.imagesearch.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager

    private var resItems: ArrayList<SearchModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()
        setupListeners()

        return binding.root
    }


    private fun setupViews() {
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchRecyclerView.layoutManager = gridmanager

        adapter = SearchAdapter(mContext)
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.itemAnimator = null

        val lastSearch = Utils.getLastSearch(requireContext())
        binding.editSearch.setText(lastSearch)
    }


    private fun setupListeners() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.txtSearch.setOnClickListener {
            val query = binding.editSearch.text.toString()
            if (query.isNotEmpty()) {
                Utils.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            } else {
                Toast.makeText(mContext, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }

            imm.hideSoftInputFromWindow(binding.editSearch.windowToken, 0)
        }
    }


    private fun fetchImageResults(query: String) {
        apiService.Image(Constants.AUTH_HEADER, query, "recency", 1, 80)
            ?.enqueue(object : Callback<ImageModel?> {
                override fun onResponse(call: Call<ImageModel?>, response: Response<ImageModel?>) {
                    response.body()?.imgmeta?.let { meta ->
                        if (meta.totalCount > 0) {
                            response.body()!!.imgdocument.forEach { document ->
                                val title = document.displaySiteName
                                val datetime = document.datetime
                                val url = document.thumbnailUrl
                                resItems.add(SearchModel(title, datetime, url))
                            }
                        }
                    }
                    adapter.items = resItems
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<ImageModel?>, t: Throwable) {
                }
            })
    }
}


