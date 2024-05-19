package com.example.postsapp.extenstions

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.SharedFlow

fun <T> Fragment.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        sharedFlow.collect {
            block(it)
        }
    }
}

fun Fragment.showMessageDialog(message: String) {
    val builder = AlertDialog.Builder(this.requireActivity())
    builder.setMessage(message)
    builder.setPositiveButton("OK") { dialog, which ->
        dialog.dismiss()
    }
    builder.create().show()
}

fun Fragment.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
    runCatching {
        findNavController().navigate(directions, navOptions)
    }
}