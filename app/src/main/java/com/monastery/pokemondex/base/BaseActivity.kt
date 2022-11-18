package com.monastery.pokemondex.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.monastery.pokemondex.R
import java.lang.Exception

abstract class BaseActivity(@LayoutRes private val rootLayout: Int?): AppCompatActivity() {
    lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootLayout?.let { v ->
            setContentView(v)
        }

        mFragmentManager = supportFragmentManager
    }

    fun replaceFragment(fragment: BaseFragment, isClearBackStack: Boolean = false) {
        if (isClearBackStack) {
            resetBackStack(fragmentManager = mFragmentManager)
        }

        onReplaceFragment(fragment)
    }


    private fun onReplaceFragment(fragment: BaseFragment) {
        val mFragmentTAG = fragment.javaClass.simpleName

        val transaction = mFragmentManager.beginTransaction()
        transaction.addToBackStack(mFragmentTAG)
            .replace(R.id.fragmentContainer, fragment, mFragmentTAG)
            .commitAllowingStateLoss()
    }

    private fun resetBackStack(fragmentManager: FragmentManager) {
        try {
            while (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStackImmediate()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}