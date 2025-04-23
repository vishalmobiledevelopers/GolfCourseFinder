package com.golfcourse.finderapp.network


import com.golfcourse.finderapp.home.models.golfcoursedetail.GolfCourseDetailResponse
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse

import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

import retrofit2.http.Query


interface API {


    // this function is used to get course list from api

    @GET("search")
    suspend fun getGolfCourseList(@Query ("search_query") search_query:String):Response<GolfCourseListResponse>

    // this function is used to get course detail from api

    @GET("courses/{course_id}")
    suspend fun getGolfCourseDetail(@Path ("course_id") course_id:String):Response<GolfCourseDetailResponse>


}