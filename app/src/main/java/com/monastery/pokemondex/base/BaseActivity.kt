package com.monastery.pokemondex.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.monastery.pokemondex.R
import java.lang.Exception

abstract class BaseActivity(@LayoutRes private val rootLayout: Int?): ComponentActivity() {
    lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootLayout?.let { v ->
            setContentView(v)
        }

    }
}