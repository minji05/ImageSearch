//package com.example.imagesearch
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.TextUtils.replace
//import androidx.fragment.app.Fragment
//import com.example.imagesearch.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var searchFragment: SearchFragment
//    private lateinit var bookmarkFragment: BookmarkFragment
//    var bookmarkeList: ArrayList<SearchModel> = ArrayList()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        searchFragment = SearchFragment()
//        bookmarkFragment = BookmarkFragment()
//ㅁ
//        setFragment(searchFragment)
//
//
//        binding.SearchFragmentbtn.setOnClickListener {
//            setFragment(searchFragment)
//        }
//        binding.BookmarkFragmentbtn.setOnClickListener {
//            setFragment(bookmarkFragment)
//        }
//    }
//
//
//
//    private fun setFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.frameLayout, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
//
//
//
////        fun addLikedItem(item: SearchModel) {
////            if(!likedItems.contains(item)) {
////                likedItems.add(item)
////            }
////        }
////
////        fun removeLikedItem(item: SearchModel) {
////            likedItems.remove(item)
////        }
//    }
