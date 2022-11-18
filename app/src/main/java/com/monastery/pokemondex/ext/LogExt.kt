package com.monastery.pokemondex.ext

import android.util.Log

fun Any.logi() {
    Log.i("HaoTest", this.toString())
}

fun Any.logd() {
    Log.d("HaoTest", this.toString())
}

fun Any.loge() {
    Log.e("HaoTest", this.toString())
}