package com.bignerdranch.android.criminalintent.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDao
import com.bignerdranch.android.criminalintent.database.CrimeDatabase
import com.bignerdranch.android.criminalintent.model.Crime
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "crime-database"

/**
 * The repository provides access to the class that represents the database (CrimeDatabase).
 */
class CrimeRepository private constructor(context: Context){

    //This is a link to the database
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    ).build()

    //This is a link to the DAO
    private val crimeDao: CrimeDao = database.crimeDao()

    //GET all data
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    //GET the crime
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    companion object {
        private var INSTANCE: CrimeRepository? = null

        //This is Initializing a new instance to the repository
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        //This is getting access to the instance
        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized!")
        }
    }
}