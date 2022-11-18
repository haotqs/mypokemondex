package com.monastery.pokemondex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monastery.pokemondex.base.BaseActivity
import com.monastery.pokemondex.ui.HomeFragment

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(HomeFragment())


    }


}