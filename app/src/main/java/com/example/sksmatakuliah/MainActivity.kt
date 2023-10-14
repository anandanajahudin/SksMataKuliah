package com.example.sksmatakuliah

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseViewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
        courseAdapter = CourseAdapter(courseViewModel.getCourseList(), ::onCourseDelete)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = courseAdapter
        }

        addButton.setOnClickListener { addCourse() }
        calculateButton.setOnClickListener { showTotalSksAndIpk() }
        resetButton.setOnClickListener { resetCourse() }
    }

    private fun addCourse() {
        val sksString = sksEditText.text.toString()
        val nilaiString = nilaiEditText.text.toString()

        if (sksString.isNotBlank() && nilaiString.isNotBlank()) {
            val sks = sksString.toIntOrNull()
            val nilai = nilaiString.toDoubleOrNull()

            if (sks != null && nilai != null && sks > 0 && nilai >= 0 && nilai <= 4) {
                val namaMataKuliah = namaMataKuliahEditText.text.toString()
                val course = CourseModel(sks, nilai, namaMataKuliah)

                courseViewModel.addCourse(course)
                courseAdapter.notifyDataSetChanged()

                Toast.makeText(this, "Input SKS has been successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Input SKS and Score are invalid", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter SKS and Score", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onCourseDelete(position: Int) {
        courseViewModel.removeCourse(position)
        courseAdapter.notifyDataSetChanged()
    }

    private fun showTotalSksAndIpk() {
        val totalSks = courseViewModel.getTotalSks()
        val ipk = courseViewModel.calculateIPK()

        totalSksTextView.text = getString(R.string.totalSksResult, totalSks)
        ipkTextView.text = getString(R.string.ipkResult, ipk)
    }

    private fun resetCourse() {
        sksEditText.text?.clear()
        nilaiEditText.text?.clear()
        namaMataKuliahEditText.text?.clear()
    }
}