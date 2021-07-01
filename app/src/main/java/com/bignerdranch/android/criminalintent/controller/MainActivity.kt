package com.bignerdranch.android.criminalintent.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.criminalintent.R
import java.util.*

/**
 * UI fragment hosting (CrimeFragment)
 * MainActivity - host
 */
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check if our fragment was created
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    /**
     * It is an Implementation of the List Fragment interface (Callbacks).
     * To go to the detailed view of the fragment
     */
    override fun onCrimeSelected(crimeId: UUID) {
        val fragment = CrimeFragment.newInstance(crimeId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}