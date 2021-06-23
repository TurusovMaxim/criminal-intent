package com.bignerdranch.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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

        if (currentFragment != null) {
            val fragment = CrimeFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}