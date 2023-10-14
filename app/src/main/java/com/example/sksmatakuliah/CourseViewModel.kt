package com.example.sksmatakuliah

import androidx.lifecycle.ViewModel

class CourseViewModel : ViewModel() {
    private val courseList = mutableListOf<CourseModel>()

    fun addCourse(course: CourseModel) {
        courseList.add(course)
    }

    fun removeCourse(index: Int) {
        if (index >= 0 && index < courseList.size) {
            courseList.removeAt(index)
        }
    }

    fun getCourseList(): List<CourseModel> {
        return courseList
    }

    fun getTotalSks(): Int {
        return courseList.sumBy { course: CourseModel -> course.sks }
    }

    fun calculateIPK(): Double {
        if (courseList.isEmpty()) return 0.0

        val totalKumulatif = courseList.sumByDouble { course: CourseModel -> course.sks * course.nilai }
        val totalSks = courseList.sumBy { course: CourseModel -> course.sks }

        return totalKumulatif / totalSks
    }
}