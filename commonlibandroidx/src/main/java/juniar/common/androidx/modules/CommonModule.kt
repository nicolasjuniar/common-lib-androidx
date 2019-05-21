package juniar.common.androidx.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import juniar.common.androidx.helper.ConnectionLiveData
import juniar.common.androidx.helper.DiffCallback
import javax.inject.Singleton

@Module
open class CommonModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesConnectionLiveData() = ConnectionLiveData(context)

    @Provides
    @Singleton
    fun providesDiffCallback() = DiffCallback()
}