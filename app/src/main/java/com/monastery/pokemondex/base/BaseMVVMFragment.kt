package com.monastery.pokemondex.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.monastery.pokemondex.ext.logi

abstract class BaseMVVMFragment<M: BaseModelView>(rootLayout: Int): BaseFragment(rootLayout), LifecycleOwner {

    protected var viewModel: M? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserveModelViewBase()
        setupObserveModelView(viewModel)
    }

    override fun onDestroyView() {
        if (viewModel != null) {
            viewModel?.onUnsubscribe()
        }
        super.onDestroyView()
    }


    protected abstract fun setupObserveModelView(viewModel: M?)

    private fun setupObserveModelViewBase() {
        viewModel?.onLoadingApiFail?.observe(viewLifecycleOwner, Observer { msg -> loadAPIFail(msg) })

        viewModel?.onLoadingApiError?.observe(viewLifecycleOwner, Observer { throwable -> loadApiError(throwable) })

        viewModel?.onShowLoadingApi?.observe(
            viewLifecycleOwner,
            Observer { isShow ->
                if (isShow == true) {
                   "onLoading".logi()
                } else {
                    "hide Loading".logi()
                }
            }
        )
    }


    private fun loadAPIFail(message: String) {
        message.logi()
    }

    private fun loadApiError(throwable: Throwable) {
        throwable.message?.let {
            it.logi()
        }
    }
}