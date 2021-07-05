package com.bignerdranch.android.criminalintent.controller

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

/**
 * This is a fragment representing the date picker dialog (day, month, year)
 */
class DatePickerFragment: DialogFragment() {

    //return the user-selected date value
    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dateListener = DatePickerDialog.OnDateSetListener {
                _:DatePicker, year: Int, month: Int, day: Int ->

            val resultDate: Date = GregorianCalendar(year, month, day).time

            //target fragment - CrimeFragment
            //passing the date value to the callback function so CrimeFragment can use it
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onDateSelected(resultDate)
            }

        }

        //get the date value from the arguments package
        val date = arguments?.getSerializable(ARG_DATE) as Date

        val calendar = Calendar.getInstance()

        calendar.time = date

        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDaY= calendar.get(Calendar.DAY_OF_MONTH)

        //set the date
        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDaY)
    }

    /**
     * Get the date value from the model layer using CrimeFragment.
     * CrimeFragment initializes the given fragment.
     * The date value is placed in the arguments package.
     */
    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}