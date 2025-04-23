package com.golfcourse.finderapp.home.models.golfcoursedetail

data class Location(
    val address: String,
    val city: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val state: String
)