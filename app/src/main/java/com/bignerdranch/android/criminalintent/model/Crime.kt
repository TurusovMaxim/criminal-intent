package com.bignerdranch.android.criminalintent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @param id -  crime identifier.
 * @param title - headline of the crime.
 * @param date - date of the crime.
 * @param isSolved - crime status.
 *
 * It's a data model that describes crimes in the application.
 */
@Entity
data class Crime(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = " ",
    var date: Date = Date(),
    var isSolved: Boolean = false
)