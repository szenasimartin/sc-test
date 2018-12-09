package com.szenasi.test.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.szenasi.test.R

fun ImageView.load(pathString: String?, onLoadingFinished: () -> Unit = {}) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    val requestOptions = RequestOptions.placeholderOf(circularProgressDrawable).centerCrop()
    GlideApp.with(context)
        .load("$BASE_URL_GLIDE$pathString")
        .error(R.drawable.ic_error_outline_black_24dp)
        .apply(requestOptions)
        .into(this)
}