package com.bignerdranch.android.criminalintent

import android.app.Application
import com.bignerdranch.android.criminalintent.repository.CrimeRepository

/**
 * This is where the repository singleton is initialized.
 */
class CriminalIntentApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}