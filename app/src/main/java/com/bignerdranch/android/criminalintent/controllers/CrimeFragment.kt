package com.bignerdranch.android.criminalintent.controllers

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.bignerdranch.android.criminalintent.R
import com.bignerdranch.android.criminalintent.data.Crime

/**
 * It's a fragment of the application that describes the detailed crime screen
 */
class CrimeFragment: Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        titleField = view?.findViewById(R.id.crime_title) as EditText
        dateButton = view?.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view?.findViewById(R.id.crime_solved_img) as CheckBox

        return inflater.inflate(R.layout.fragment_crime, container, false)
    }

    /**
     * Connecting listeners to widgets
     */
    override fun onStart() {
        super.onStart()

        //tittleField - EditText
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            //setting the title of the crime
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                crime.title = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        titleField.addTextChangedListener(titleWatcher)

        //dateButton - Button
        dateButton.apply {
            //setting the date of the crime
            text = crime.date.toString()
            isEnabled = false
        }

        //solvedCheckBox - CheckBox
        solvedCheckBox.apply {
            //setting the status of the crime
            setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
                crime.isSolved = isChecked
            }
        }
    }

    
}