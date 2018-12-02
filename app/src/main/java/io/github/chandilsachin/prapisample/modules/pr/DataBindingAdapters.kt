package io.github.chandilsachin.prapisample.modules.pr

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("avatar_url")
fun ImageView.setImageAvatar(link: String) {
    Glide.with(getContext())
        .load(link)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

@BindingAdapter("by")
fun TextView.setBy(user: String) {
    this.text = String.format("By: %s", user)
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("createdAt")
fun TextView.setUpdatedAt(timestamp: String) {
    val date = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(timestamp)
    this.text = String.format("Updated at: %s", DateUtils.formatDateTime(context, date.time, 0))
}


@BindingAdapter("loading")
fun ProgressBar.setLoading(loading: Boolean) {
    this.visibility = if(loading) View.VISIBLE else View.GONE
}

