package com.example.postsapp.presentation.addUpdatePost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.feature_posts.R
import com.example.feature_posts.databinding.AddUpdatePostFragmentBinding
import com.example.postsapp.base.BaseFragment
import com.example.postsapp.data.model.PostsModel
import com.example.postsapp.extenstions.collect
import com.example.postsapp.extenstions.hide
import com.example.postsapp.extenstions.navigateSafe
import com.example.postsapp.extenstions.showMessageDialog
import com.example.postsapp.network.Resource
import com.example.postsapp.presentation.PostsValidation
import com.example.postsapp.presentation.UPDATE
import com.example.postsapp.resProvider.IResourceProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class AddUpdateFragment :
    BaseFragment<AddUpdatePostFragmentBinding>(AddUpdatePostFragmentBinding::inflate) {

    @Inject
    lateinit var resourceProvider: IResourceProvider
    private val viewModel: AddUpdateViewModel by viewModels()
    private var action: String? = null
    private var postsModel: PostsModel? = null

    private fun getFragmentArguments() {
        val args: AddUpdateFragmentArgs by navArgs()
        action = args.action
        postsModel = args.post
    }

    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {

        getFragmentArguments()
        observer()
        viewBinding().apply {
            if (action == UPDATE) {
                etTitle.setText(postsModel?.title)
                etBody.setText(postsModel?.body)
            }
            confirm.setOnClickListener {
                etTitle.hide()
                etBody.hide()
                viewModel.addUpdatePost(
                    tvTitle.text.toString(),
                    tvBody.text.toString(),
                    action ?: ""
                )
            }
        }
    }

    private fun observer() {
        collect(viewModel.validation) {
            when (it) {
                PostsValidation.DataValid -> {}
                PostsValidation.InvalidData ->
                    showMessageDialog(resourceProvider.getText(R.string.please_put_correct_data))
            }
        }
        collect(viewModel.addPostModel) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    if (it.value.success) {
                        Toast.makeText(
                            requireActivity(),
                            resourceProvider.getText(R.string.created_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            resourceProvider.getText(R.string.created_local), Toast.LENGTH_SHORT
                        ).show()
                    }

                    navigateSafe(
                        AddUpdateFragmentDirections.actionAddUpdatePostFragmentToMainPostsFragment()
                    )
                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessageDialog(it.exception.message ?: "")
                }

                else -> showLoading()
            }
        }

        collect(viewModel.updatePostModel) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    if (it.value.success)
                        Toast.makeText(
                            requireActivity(),
                            resourceProvider.getText(R.string.updated_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        Toast.makeText(
                            requireActivity(),
                            getString(R.string.updated_local), Toast.LENGTH_SHORT
                        ).show()

                    navigateSafe(
                        AddUpdateFragmentDirections.actionAddUpdatePostFragmentToMainPostsFragment()
                    )

                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessageDialog(it.exception.message ?: "")
                }

                else -> showLoading()
            }
        }
    }

}