package com.bignerdranch.android.criminalintent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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

    //GET the crime
    @Query("SELECT * FROM Crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>

    @Update
    fun updateCrime(crime: Crime)

    @Insert
    fun addCrime(crime: Crime)

}