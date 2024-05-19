package com.example.postsapp.base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {


    private var _viewBinding: VB? = null
    var navController: NavController? = null
     val TAG: String = javaClass.simpleName

    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        _viewBinding = bindingFactory(layoutInflater)
        setContentView(viewBinding().root)

        if (savedInstanceState == null) {
            onActivityCreated(savedInstanceState)
        } else {
            //activity recreated
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "onSaveInstanceState: ")

    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }



    private fun viewBinding(): VB {
        if (_viewBinding == null) throw NullPointerException("viewBinding is null!!")
        return _viewBinding!!
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!(navController?.navigateUp() == true || super.onSupportNavigateUp())) {
            onBackPressedDispatcher.onBackPressed()
        }
        return true
    }

    fun setUpNavController(navContainerId: Int, activity: AppCompatActivity) {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(
            navContainerId
        ) as NavHostFragment

        navHostFragment.navController.apply {
            navController = this
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        _viewBinding = null
    }




}