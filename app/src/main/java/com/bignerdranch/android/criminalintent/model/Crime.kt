package com.bignerdranch.android.criminalintent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @param id -  crime identifier (database primary key).
 * @param title - headline of a crime.
 * @param date - date of a crime.
 * @param isSolved - crime status.
 *
 * It's a data model that describes crimes in the application and
 * also it's a database entity.
 */
@Entity
data class Crime(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isSolved: Boolean = false,
    var suspect: String = ""
)