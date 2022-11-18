package com.monastery.pokemondex.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

abstract class BaseActivity(@LayoutRes private val rootLayout: Int?): AppCompatActivity() {
    lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootLayout?.let { v ->
            setContentView(v)
        }

        mFragmentManager = supportFragmentManager
    }

    fun replaceFragment(fragment: BaseFragment) {

    }
}