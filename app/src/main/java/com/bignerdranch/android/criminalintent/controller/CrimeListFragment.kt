package com.bignerdranch.android.criminalintent.controller

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.R
import com.bignerdranch.android.criminalintent.model.Crime
import com.bignerdranch.android.criminalintent.viewmodel.CrimeListViewModel
import java.util.*

private const val TAG = "CrimeListFragment"

/**
 * This fragment represents a list of crimes.
 */
class CrimeListFragment: Fragment() {

    /**
     * It is a callback interface that is implemented in the fragment host.
     * This is required to interact with another fragment.
     * Passing the crime identifier to another fragment via the host.
     */
    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    //it is a property to hold an object (Callbacks)
    private var callbacks: Callbacks? = null

    //RecyclerView
    private lateinit var crimeRecyclerView: RecyclerView

    //Initializing the adapter
    private var crimeAdapter: CrimeAdapter? = CrimeAdapter(emptyList())

    //Initializing the ViewModel
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
            })
    }

    //It's a reaction to receiving new data from a LiveData object
    private fun updateUI(crimes: List<Crime>) {
        crimeAdapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = crimeAdapter
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.new_crime -> {
                val crime = Crime()
                crimeListViewModel.addCrime(crime)
                callbacks?.onCrimeSelected(crime.id)

                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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

            //ImageStatusCrime, visible == true, if the crime is solved
            solvedImageView.visibility = if (crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        //Press an itemView to show a crime
        override fun onClick(p0: View?) {
            callbacks?.onCrimeSelected(crime.id)
        }
    }

    //Adapter creates instances of ViewHolder and fills view elements with data
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