package com.monastery.pokemondex.base

import androidx.lifecycle.ViewModel
import com.monastery.pokemondex.api.ApiConsumer
import com.monastery.pokemondex.api.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class BaseModelView: ViewModel() {
    private var mCompositeDisposable: CompositeDisposable? = null

    val loadingCompleteSubject: PublishSubject<Any> = PublishSubject.create()
    val onShowLoadingApi = SingleLiveEvent<Boolean>()
    val onLoadingApiFail = SingleLiveEvent<String>()
    val onLoadingApiError = SingleLiveEvent<Throwable>()

    fun onUnsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable?.size()?.let { size ->
                if (size > 0) {
                    mCompositeDisposable?.clear()
                    mCompositeDisposable = null
                }
            }


            if (mCompositeDisposable?.isDisposed == false) {
                mCompositeDisposable?.dispose()
            }
        }
    }

    open fun addSubscription(observer: Observable<*>?, response: ApiConsumer<*>) {

        if (mCompositeDisposable == null) mCompositeDisposable = CompositeDisposable()
        response.onLoading(true)
        observer?.let { obs ->
            mCompositeDisposable?.add(
                obs
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally {
                        loadingCompleteSubject.onNext("loading Finish")
                    }
                    .subscribe({ success ->
                        response.onLoading(false)
                        response.onSuccess(success)
                    }, { throwable ->
                        loadingCompleteSubject.onNext("on error")
                        response.onFailure(throwable)
                    })
            )
        }
    }
}