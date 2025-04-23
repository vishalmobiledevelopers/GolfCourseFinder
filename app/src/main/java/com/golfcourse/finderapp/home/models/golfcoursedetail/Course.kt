package com.golfcourse.finderapp.home.models.golfcoursedetail

data class Course(
    val club_name: String,
    val course_name: String,
    val id: Int,
    val location: Location,
    val tees: Tees
)