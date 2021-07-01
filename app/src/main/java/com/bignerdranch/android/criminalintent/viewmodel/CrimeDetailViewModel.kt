package com.bignerdranch.android.criminalintent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.criminalintent.model.Crime
import com.bignerdranch.android.criminalintent.repository.CrimeRepository
import java.util.*

/**
 * Performing database operations
 */
class CrimeDetailViewModel : ViewModel() {

    //get an instance of the repository
    private val crimeRepository = CrimeRepository.get()

    //a crime identifier
    private val crimeIdLiveData = MutableLiveData<UUID>()

    //getting data from the database
    var crimeLiveData: LiveData<Crime?> =
        Transformations.switchMap(crimeIdLiveData) { crimeId ->
            crimeRepository.getCrime(crimeId)
        }

    //the helper function for getting data from the database
    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    //saving changes to the database
    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }

}