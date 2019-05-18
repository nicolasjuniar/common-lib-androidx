package juniar.common.androidx.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import juniar.common.androidx.common.Constant.Common.Companion.EMPTY_STRING

fun <T> Observable<T>.transform(): Observable<T> {
    return this.observeOn(AndroidSchedulers.mainThread())
        .onErrorResumeNext(Function { Observable.error(it) })
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.uiSubscribe(
    onNext: (T) -> Unit = {}, onError: (Throwable) -> Unit = {},
    onCompleted: () -> Unit = {}
): Disposable {
    return this.transform().toFlowable(BackpressureStrategy.BUFFER)
        .subscribe({
            onNext.invoke(it)
        }, {
            onError.invoke(it)
        }, {
            onCompleted.invoke()
        })
}

fun Context.getColorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

fun Activity.showDialog(
    title: String,
    message: String = EMPTY_STRING,
    cancelable: Boolean = false,
    positiveButton: String = EMPTY_STRING,
    positiveAction: () -> Unit = {},
    negativeButton: String = EMPTY_STRING,
    negativeAction: () -> Unit = {}
) {

    val dialogBuilder = AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(cancelable)
        if (positiveButton.isNotEmpty()) {
            setPositiveButton(positiveButton) { dialog, _ ->
                positiveAction.invoke()
                dialog.dismiss()
            }
        }
        if (negativeButton.isNotEmpty()) {
            setNegativeButton(negativeButton) { dialog, _ ->
                negativeAction.invoke()
                dialog.dismiss()
            }
        }
    }
    dialogBuilder.create().show()
}

fun Context.callIntent(phoneNumber: String) =
    startActivity(Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:$phoneNumber")))


fun Context.smsIntent(phoneNumber: String) =
    startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber")))

fun Context.openUrl(url: String) = startActivity(
    Intent(Intent.ACTION_VIEW)
        .setData(Uri.parse(url))
)

val sdkVersion = Build.VERSION.SDK_INT