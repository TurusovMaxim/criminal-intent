package com.bignerdranch.android.criminalintent.viewmodel

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.criminalintent.data.Crime

/**
 * список преступлений
 */
class CrimeListViewModel: ViewModel() {

    val crimes = mutableListOf<Crime>()

    //создание фиктивных преступлений для вывода списка
    init {
        for (i in 0 until 100) {
            val crime = Crime()

            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
}