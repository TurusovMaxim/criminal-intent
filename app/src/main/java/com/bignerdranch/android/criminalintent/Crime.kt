package com.bignerdranch.android.criminalintent

import java.util.*

/**
 * @param id -  crime identifier.
 * @param title - headline of the crime.
 * @param date - date of the crime.
 * @param isSolved - crime status.
 *
 * It's a data model that describes crimes in the application.
 */
data class Crime(
    val id: UUID = UUID.randomUUID(),
    var title: String = " ",
    var date: Date = Date(),
    var isSolved: Boolean = false
)