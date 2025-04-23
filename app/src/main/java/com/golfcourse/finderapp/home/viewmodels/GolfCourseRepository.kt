package com.golfcourse.finderapp.home.viewmodels


import androidx.lifecycle.LiveData
import com.golfcourse.finderapp.database.GolfFinderDao
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.home.models.golfcoursedetail.GolfCourseDetailResponse
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import com.golfcourse.finderapp.network.RetrofitClient
import retrofit2.Response

class GolfCourseRepository(private var golfCourseDataSource: GolfCourseDataSource) {



    // this function is bridge between viewmodel and datasource of course list

    suspend fun getGolfCourseList(searchQuery:String): Response<GolfCourseListResponse> {
        return golfCourseDataSource.getGolfCourseList(searchQuery)
    }

    // this function is bridge between viewmodel and datasource of course detail

    suspend fun getGolfCourseDetail(course_id:String): Response<GolfCourseDetailResponse> {
        return golfCourseDataSource.getGolfCourseDetail(course_id)
    }
}