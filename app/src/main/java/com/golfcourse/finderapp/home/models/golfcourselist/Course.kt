package com.golfcourse.finderapp.home.models.golfcourselist

data class Course(
    val club_name: String,
    val course_name: String,
    val id: Int,
    val location: Location,
    val tees: Tees
)