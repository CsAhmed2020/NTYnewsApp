package com.example.nytnews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytnews.databinding.ArticleItemBinding
import com.example.nytnews.domain.model.Doc
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(val onItemClick:(doc : Doc) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var binding: ArticleItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val doc = differ.currentList[position]
        holder.itemView.apply {
            val thumbnail = doc.multimedia.find {
                it.subtype == "thumbnail"
            }
            Glide.with(this).load(thumbnail?.subtype).into(binding.newsImage)
            binding.title.text = doc.headline.main
            binding.author.text = doc.byline.original
            val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            binding.date.text = dateFormat.format(doc.pub_date)
            binding.root.setOnClickListener {
                onItemClick(doc)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(binding : ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)

    val diffCallback = object : DiffUtil.ItemCallback<Doc>() {
        override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list: List<Doc>) = differ.submitList(list)

}