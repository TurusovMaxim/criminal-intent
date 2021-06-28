package com.bignerdranch.android.criminalintent.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.R
import com.bignerdranch.android.criminalintent.model.Crime
import com.bignerdranch.android.criminalintent.viewmodel.CrimeListViewModel

private const val TAG = "CrimeListFragment"

/**
 * This fragment represents a list of crimes.
 */
class CrimeListFragment: Fragment() {

    //RecyclerView
    private lateinit var crimeRecyclerView: RecyclerView

    //Initializing the adapter
    private var crimeAdapter: CrimeAdapter? = CrimeAdapter(emptyList())

    //Initializing the ViewModel
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Initializing the RecyclerView
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)

        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView

        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        crimeRecyclerView.adapter = crimeAdapter

        return view
    }

    //LiveData observer
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            { crimes ->
                crimes?.let {
                    Log.d(TAG, "Got crimes ${crimes.size}")

                    updateUI(crimes)
                }
            }
        )
    }

    //It's a reaction to receiving new data from a LiveData object
    private fun updateUI(crimes: List<Crime>) {
        crimeAdapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = crimeAdapter
    }

    //Create the fragment instance
    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    //ViewHolder to create an itemView for the RecyclerView
    private inner class CrimeHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var crime: Crime

        private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)

        private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)

        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved_img)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            this.crime = crime

            //Headline of a crime
            titleTextView.text = this.crime.title

            //Date of a crime
            dateTextView.text = this.crime.date.toString()

            //ImageStatusCrime, visible == true, if a crime is solved
            solvedImageView.visibility = if (crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        //Press an itemView to show a crime
        override fun onClick(p0: View?) {
            Toast.makeText(context, "${crime.title} pressed!" , Toast.LENGTH_LONG).show()
        }
    }

    // Adapter creates instances of ViewHolder and fills view elements with data
    private inner class CrimeAdapter(var crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
           val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)

            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]

            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

    }
}