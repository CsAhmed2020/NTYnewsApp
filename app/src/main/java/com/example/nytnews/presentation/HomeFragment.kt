package com.example.nytnews.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytnews.R
import com.example.nytnews.data.Resource
import com.example.nytnews.databinding.FragmentHomeBinding
import com.example.nytnews.presentation.adapter.NewsAdapter

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels() //navGraphViewModels(R.navigation.app_navigation) //by activityViewModels()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeOnNews()

    }

    private fun observeOnNews(){
        viewModel.newsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> Log.d("Adel Error", it.message.toString())
                is Resource.Loading -> Log.d("Adel Loading", "loading")
                is Resource.Success -> {
                    Log.d("Adel Success", it.data!!.response.docs.first().toString())
                    newsAdapter.submitList(it.data.response.docs)
                }
            }
        }
    }

    private fun setupRecyclerView() = binding.newsRv.apply {
        newsAdapter = NewsAdapter { selectedItem ->
            viewModel.updateSelectedArticle(selectedItem)
            //findNavController().navigate(action)
        }
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}