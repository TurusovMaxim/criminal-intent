package com.bignerdranch.android.criminalintent.viewmodel

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.criminalintent.repository.CrimeRepository

/**
 * список преступлений
 */
class CrimeListViewModel: ViewModel() {

    private val crimeRepository: CrimeRepository = CrimeRepository.get()

    val crimeListLiveData = crimeRepository.getCrimes()
}