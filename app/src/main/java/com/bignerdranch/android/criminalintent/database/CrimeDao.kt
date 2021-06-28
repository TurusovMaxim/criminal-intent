package com.bignerdranch.android.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.criminalintent.model.Crime
import java.util.*

/**
 * Data Access Object (DAO) - is an interface
 * that contains functions for each database operation.
 */
@Dao
interface CrimeDao {

    //GET all data
    @Query("SELECT * FROM Crime")
    fun getCrimes(): LiveData<List<Crime>>

    //GET a specific data
    @Query("SELECT * FROM Crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>

}