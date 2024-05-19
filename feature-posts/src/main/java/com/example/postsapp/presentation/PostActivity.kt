package com.example.postsapp.presentation

import android.os.Bundle
import com.example.feature_posts.R
import com.example.feature_posts.databinding.PostMainActivityBinding
import com.example.postsapp.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BaseActivity<PostMainActivityBinding>({ PostMainActivityBinding.inflate(it) }) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setUpNavController(R.id.postFragmentContainerView, this)
    }
}