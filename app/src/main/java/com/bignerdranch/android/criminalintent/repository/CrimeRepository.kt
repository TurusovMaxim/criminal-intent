package com.bignerdranch.android.criminalintent.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDao
import com.bignerdranch.android.criminalintent.database.CrimeDatabase
import com.bignerdranch.android.criminalintent.database.migration_1_2
import com.bignerdranch.android.criminalintent.model.Crime
import java.io.File
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

/**
 * The repository provides access to the class that represents the database (CrimeDatabase).
 */
class CrimeRepository private constructor(context: Context){

    //This is a link to the database
    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME)
        .addMigrations(migration_1_2)
        .build()

    //This is a link to the DAO
    private val crimeDao: CrimeDao = database.crimeDao()

    //This is an executor for performing database operations (UID) in a background thread
    private val executor = Executors.newSingleThreadExecutor()

    //получаем дескриптор каталога для приватных файлов
    private val filesDir = context.applicationContext.filesDir

    //GET all data
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()

    //GET the crime
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    //UPDATE the crime
    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    //INSERT new crime
    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    //возвращает объекты File, указывающие в нужные места
    fun getPhotoFile(crime: Crime): File =
        File(filesDir, crime.photoFileName)

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