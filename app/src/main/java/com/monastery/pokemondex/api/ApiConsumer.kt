package com.monastery.pokemondex.api

import com.monastery.pokemondex.base.BaseResponse

interface ApiConsumer<M: BaseResponse> {

    fun onSuccess(o: Any) {
        onSuccess(o as M)
    }

    fun onSuccess(response: M)
    fun onFailure(throwable: Throwable)
    fun onLoading(isShowLoading: Boolean)
}