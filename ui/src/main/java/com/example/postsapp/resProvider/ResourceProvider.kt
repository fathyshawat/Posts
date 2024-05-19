package com.example.postsapp.resProvider

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class ResourceProvider @Inject constructor(
  @ApplicationContext private val context: Context
) : IResourceProvider {

  override fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(context, resId)



  override fun getDrawable(@DrawableRes resId: Int) = checkNotNull(ContextCompat.getDrawable(context, resId))

  override fun getText(@StringRes resId: Int, vararg values: Any) = context.getString(resId, *values)



  @Suppress("DEPRECATION")
  override fun getLocale(): Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    context.resources.configuration.locales.get(0)
  } else {
    context.resources.configuration.locale
  }



  override fun getSystemResources(): Resources = context.resources


}