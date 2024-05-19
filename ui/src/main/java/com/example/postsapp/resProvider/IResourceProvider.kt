package com.example.postsapp.resProvider

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.Locale

interface IResourceProvider {
    fun getColor(@ColorRes resId: Int): Int

    fun getDrawable(@DrawableRes resId: Int): Drawable

    fun getText(@StringRes resId: Int, vararg values: Any): String


    fun getLocale(): Locale


    fun getSystemResources(): Resources

}