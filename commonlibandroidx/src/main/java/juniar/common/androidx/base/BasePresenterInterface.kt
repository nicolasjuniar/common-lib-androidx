package juniar.common.androidx.base

interface BasePresenterInterface<T> {
    fun init(view: T)
    fun destroy()
}