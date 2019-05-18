package juniar.common.androidx.utils

import android.text.Html
import android.util.Patterns
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.util.regex.Pattern

fun isPhoneValid(phone: String) = Patterns.PHONE.matcher(phone).matches()

fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun String.logDebug() = Timber.d(this)

fun isAlphabetOnly(text: String): Boolean {
    val expression = "^[\\p{L} .'-]+$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(text)
    return matcher.matches()
}

fun Any.encodeJson() = Gson().toJson(this)

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

@Suppress("DEPRECATION")
fun String.toHtmlText() = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) Html.fromHtml(
    this,
    Html.FROM_HTML_MODE_LEGACY
) else Html.fromHtml(this)