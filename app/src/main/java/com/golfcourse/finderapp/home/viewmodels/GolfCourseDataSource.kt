package com.golfcourse.finderapp.home.viewmodels


import com.golfcourse.finderapp.database.GolfCourseDatabase
import com.golfcourse.finderapp.database.GolfFinderDao
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.home.models.golfcoursedetail.GolfCourseDetailResponse
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import com.golfcourse.finderapp.network.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class GolfCourseDataSource {


    // called when course list api has been called from retrofit api


    suspend fun getGolfCourseList(searchQuery:String): Response<GolfCourseListResponse> {
        return RetrofitClient.instance.getGolfCourseList(searchQuery)
    }

    // called when course detail api has been called from retrofit api

    suspend fun getGolfCourseDetail(course_id:String): Response<GolfCourseDetailResponse> {
        return RetrofitClient.instance.getGolfCourseDetail(course_id)
    }

}