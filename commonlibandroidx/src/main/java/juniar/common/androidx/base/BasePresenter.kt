package juniar.common.androidx.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {
    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.track(){
        compositeDisposable.add(this)
    }

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}