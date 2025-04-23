package com.golfcourse.finderapp.database


import androidx.lifecycle.LiveData
import com.golfcourse.finderapp.database.GolfFinderDao
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import retrofit2.Response

class GolfCourseLocalRepository(private var goldCourseLocalDataSource: GolfCourseLocalDataSource) {



    // this function will called of Datasource of add golfCourse Function

    suspend fun addGolfCourse( golfSearchRequest: GolfSearchRequest){

        return goldCourseLocalDataSource.addGolfCourse(golfSearchRequest)


    }

    // this function will called of Datasource of deleteAllGolfCourse Function

    suspend fun deleteAllGolfCourse(){

        return goldCourseLocalDataSource.deleteAllGolfCourse()
    }

    // this function will called of Datasource of getAllGolfCourses Function

    suspend fun getAllGolfCourses(): List<GolfSearchRequest> {

        return goldCourseLocalDataSource.getAllGolfCourses()
    }



}