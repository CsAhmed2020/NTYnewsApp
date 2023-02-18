package com.example.nytnews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.nytnews.R
import com.example.nytnews.databinding.FragmentDetailsBinding
import com.example.nytnews.domain.model.Doc
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment() {

    private var _binding:FragmentDetailsBinding? =null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels()

    private lateinit var selectedArticle: Doc

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedArticle = viewModel.selectedArticle
        binding.title.text = selectedArticle.headline.main
        binding.subTitle.text = selectedArticle.abstract
        binding.author.text = selectedArticle.byline.original
        binding.content.text = selectedArticle.lead_paragraph
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        binding.date.text = dateFormat.format(selectedArticle.pub_date)
        val image = selectedArticle.multimedia.find {
            it.subtype == "xlarge"
        }
        Glide.with(view).load(image?.subtype).into(binding.image)
    }
}