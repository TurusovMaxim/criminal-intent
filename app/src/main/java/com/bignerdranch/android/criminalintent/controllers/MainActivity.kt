package com.bignerdranch.android.criminalintent.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.criminalintent.R

/**
 * UI fragment hosting (CrimeFragment)
 * MainActivity - host
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check if the fragment was created
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}