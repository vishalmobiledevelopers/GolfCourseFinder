package com.golfcourse.finderapp.home.models.golfcourselist

data class Female(
    val back_bogey_rating: Double,
    val back_course_rating: Double,
    val back_slope_rating: Double,
    val bogey_rating: Double,
    val course_rating: Double,
    val front_bogey_rating: Double,
    val front_course_rating: Double,
    val front_slope_rating: Double,
    val holes: List<Hole>,
    val number_of_holes: Double,
    val par_total: Double,
    val slope_rating: Double,
    val tee_name: String,
    val total_meters: Double,
    val total_yards: Double
)