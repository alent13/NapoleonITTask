package com.applexis.napoleonittask.utils

import android.net.Uri
import android.view.ViewManager
import com.applexis.napoleonittask.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.custom.ankoView

public inline fun ViewManager.roundImageView(theme: Int = 0) = roundImageView(theme) {}
public inline fun ViewManager.roundImageView(theme: Int = 0, init: RoundedImageView.() -> Unit) = ankoView({ RoundedImageView(it) }, theme, init)

fun RoundedImageView.load(uri: Uri, placeholder: Int) {
    Picasso.get()
        .load(uri)
        .placeholder(placeholder)
        .into(this)
}