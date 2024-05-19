package com.example.postsapp.presentation.mainPosts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_posts.databinding.ItemPostsBinding
import com.example.postsapp.data.model.PostsModel


class PostsAdapter(
    private val itemClickDelete: (Int) -> Unit,
    private val itemClickEdit: (PostsModel) -> Unit
) :
    ListAdapter<PostsModel, PostsAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostsModel>() {
            override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val postsModel = getItem(position)
        holder.bind(postsModel)
    }

    inner class HistoryViewHolder(private val binding: ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivDelete.setOnClickListener {
                itemClickDelete.invoke(getItem(layoutPosition).id)
            }
            binding.ivEdit.setOnClickListener {
                itemClickEdit.invoke(getItem(layoutPosition))
            }
        }

        fun bind(postsModel: PostsModel) {
            binding.apply {
                tvUserID.text = postsModel.userID.toString()
                tvTitle.text = postsModel.title
                tvBody.text = postsModel.body
            }
        }
    }
}