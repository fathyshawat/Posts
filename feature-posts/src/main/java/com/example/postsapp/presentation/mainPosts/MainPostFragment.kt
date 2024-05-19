package com.example.postsapp.presentation.mainPosts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_posts.R
import com.example.feature_posts.databinding.MainFragmentBinding
import com.example.postsapp.base.BaseFragment
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.extenstions.collect
import com.example.postsapp.extenstions.navigateSafe
import com.example.postsapp.extenstions.showMessageDialog
import com.example.postsapp.network.Resource
import com.example.postsapp.presentation.CREATE
import com.example.postsapp.presentation.UPDATE
import com.example.postsapp.resProvider.IResourceProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainPostFragment : BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {


    private val viewModel: MainPostViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter

    @Inject
    lateinit var resourceProvider: IResourceProvider

    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getPosts()
        viewBinding().btnAdd.setOnClickListener {
            navigateSafe(MainPostFragmentDirections.actionMainPostsFragmentToAddUpdatePostFragment(
                PostsModel(),
                CREATE
            ))

        }
        observer()
    }

    private fun observer() {
        collect(viewModel.postsModel) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    initPosts(it.value)
                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessageDialog(it.exception.message ?: "")
                }

                else -> showLoading()
            }
        }

        collect(viewModel.deletePostModel) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    if (it.value.success)
                        Toast.makeText(
                            requireActivity(),
                            resourceProvider.getText(R.string.deleted_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        Toast.makeText(
                            requireActivity(),
                            resourceProvider.getText(R.string.deleted_local), Toast.LENGTH_SHORT
                        ).show()

                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessageDialog(it.exception.message ?: "")
                }

                else -> showLoading()
            }
        }
    }

    private fun initPosts(list: List<PostsModel?>?) {
        postsAdapter = PostsAdapter({
            viewModel.deletePost(it)
        }, {
            navigateSafe(MainPostFragmentDirections.
            actionMainPostsFragmentToAddUpdatePostFragment(it, UPDATE))
        }
        )
        postsAdapter.submitList(list)
        viewBinding().recyclerPosts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postsAdapter

        }
    }
}