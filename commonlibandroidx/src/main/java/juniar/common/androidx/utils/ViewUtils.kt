package juniar.common.androidx.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions()
                .centerCrop()
        )
        .into(this)
}

fun ImageView.setDrawableVectorCompat(@DrawableRes drawableId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        this.setImageDrawable(
            VectorDrawableCompat.create(this.context.resources, drawableId, this.context.theme)
        )
    } else {
        this.setImageResource(drawableId)
    }
}

fun Window.blockTouchScreen() {
    this.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun Window.unblockTouchScreen() {
    this.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Context.showToast(message: String, isLong: Boolean = false) = Toast.makeText(
    this, message, if (isLong) {
        Toast.LENGTH_LONG
    } else {
        Toast.LENGTH_SHORT
    }
).show()

fun EditText.textToString() = this.text.toString()

fun TextView.textToString() = this.text.toString()