package com.bignerdranch.android.criminalintent.database

import androidx.room.TypeConverter
import java.util.*

/**
 * A type converter converts a special type to a format for storage in a database.
 */
class CrimeTypeConverters {

    //DATE(date)
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }


    //UUID(id)
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID {
        return UUID.fromString(uuid)
    }
}