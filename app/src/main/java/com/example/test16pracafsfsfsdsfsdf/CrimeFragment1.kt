package com.example.test16pracafsfsfsdsfsdf


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class CrimeFragment1 : Fragment() {
    private lateinit var crime: Crime1
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var fabButton: FloatingActionButton
    private lateinit var solvedCheckBox: CheckBox
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime1()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        titleField = view.findViewById(R.id.crime_title)
        solvedCheckBox = view.findViewById(R.id.crime_solved)
        dateButton = view.findViewById(R.id.crime_date)
        fabButton = view.findViewById(R.id.fab_crime_date)

        val russianLocale = Locale("ru")


        val dateFormat = SimpleDateFormat("dd MMMM yyyy hh:mm", russianLocale)




        titleField.setText(crime.title)
        solvedCheckBox.isChecked = crime.isSolved


        fabButton.setOnClickListener {
            crime.data = Date()

           dateButton.isEnabled=true
            dateButton.text = dateFormat.format(crime.data)

            showSnackbar("Данные преступления обновлены")
        }
        rootView = view
        return view
    }

    override fun onStart() {
        super.onStart()
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {}
        }
        titleField.addTextChangedListener(titleWatcher)

        solvedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
            if (isChecked) {
                showSnackbar("Преступление раскрыто")
            }
        }
    }

    private fun showSnackbar(message: String) {
        if (::rootView.isInitialized) {

            Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
        }
    }
}
