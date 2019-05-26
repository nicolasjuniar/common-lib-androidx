package juniar.common.androidx.base

import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import juniar.common.androidx.R
import juniar.common.androidx.helper.ConnectionLiveData
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData

    protected val disposables = CompositeDisposable()

    protected fun <T> LiveData<T>.onResult(action: (T) -> Unit) {
        observe(this@BaseActivity, Observer { data -> data?.let(action) })
    }

    protected fun boundNetwork(action: (Boolean) -> Unit = {}) {
        connectionLiveData.onResult {
            action.invoke(it)
        }
    }

    protected fun Disposable.track() {
        disposables.add(this)
    }

    fun setupToolbar(
        toolbarId: Toolbar,
        @StringRes title: Int = R.string.empty_string,
        @DrawableRes drawable: Int? = null
    ) {
        setSupportActionBar(toolbarId)
        supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)
            val thisSupportActionBar = it
            drawable?.let { toolbarDrawable ->
                thisSupportActionBar.setDisplayHomeAsUpEnabled(true)
                thisSupportActionBar.setDisplayShowHomeEnabled(true)
                thisSupportActionBar.setHomeAsUpIndicator(toolbarDrawable)
            }
        }
        toolbar_title.setText(title)
    }

    fun changeToolbarTitle(@StringRes title: Int) {
        toolbar_title.setText(title)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}