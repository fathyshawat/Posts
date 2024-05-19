package com.example.postsapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    Fragment() {


    private var _viewBinding: VB? = null


     fun viewBinding(): VB {
        if (_viewBinding == null) throw NullPointerException("viewBinding is null!!")
        return _viewBinding!!
    }


    private val loadingDialogDelegate = lazy { LoadingDialog(activity) }
    private val loadingDialog by loadingDialogDelegate
    abstract fun onFragmentCreated(view: View, savedInstanceState: Bundle?)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = bindingFactory(layoutInflater)
        return viewBinding().root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentCreated(view, savedInstanceState)
    }
    fun showLoading(hint: String? = null) = loadingDialog.showDialog(hint)

    fun hideLoading() = loadingDialog.hideDialog()


    override fun onDestroy() {
        if (loadingDialogDelegate.isInitialized()) {
            loadingDialog.clean()
        }
        super.onDestroy()
    }
}