package com.bignerdranch.android.criminalintent.viewmodel

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.criminalintent.model.Crime
import com.bignerdranch.android.criminalintent.repository.CrimeRepository

/**
 * Get a list of crimes or add new crime from the database repository.
 */
class CrimeListViewModel: ViewModel() {

    //Get an instance of the database repository
    private val crimeRepository: CrimeRepository = CrimeRepository.get()

    //Get the list of crimes
    val crimeListLiveData = crimeRepository.getCrimes()

    //add new crime
    fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}