package com.monastery.pokemondex.api

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T>: MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            //TODO: Multiple observers registered but only one will be notified of changes.
        }

        super.observe(owner,
            Observer<T> { t->
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            })
    }

    @MainThread
    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }
}