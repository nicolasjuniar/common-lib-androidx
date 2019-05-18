package juniar.common.androidx.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import juniar.common.androidx.helper.SharedPreferencesHelper
import javax.inject.Singleton

@Module
open class SharedPreferencesModule(
    private val sharedPreferencesName: String,
    private val context: Context
) {

    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesSharedPreferencesHelper(sharedPreferences: SharedPreferences): SharedPreferencesHelper =
        SharedPreferencesHelper(sharedPreferences)

}