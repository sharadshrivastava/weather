package com.test.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.test.app.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupActionBarWithNavController(this, NavHostFragment.
            findNavController(navHostFragment))
    }

    override fun onSupportNavigateUp()
            = NavHostFragment.findNavController(navHostFragment).navigateUp()
}