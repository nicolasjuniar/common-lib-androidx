package juniar.common.androidx.helper

import android.content.SharedPreferences
import juniar.common.androidx.common.Constant.Common.Companion.EMPTY_STRING

open class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {

    fun set(key: String, value: Any) {
        when (value) {
            is Boolean -> sharedPreferences.edit().putBoolean(key, value).apply()
            is String -> sharedPreferences.edit().putString(key, value).apply()
            is Int -> sharedPreferences.edit().putInt(key, value).apply()
            is Long -> sharedPreferences.edit().putLong(key, value).apply()
        }
    }

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun getString(key: String, defaultValue: String = EMPTY_STRING) = sharedPreferences.getString(key, defaultValue)

    fun getInt(key: String, defaultValue: Int = 0) = sharedPreferences.getInt(key, defaultValue)

    fun getLong(key: String, defaultValue: Long = 0L) = sharedPreferences.getLong(key, defaultValue)
}