package com.golfcourse.finderapp.database


import androidx.lifecycle.LiveData
import com.golfcourse.finderapp.database.GolfCourseDatabase
import com.golfcourse.finderapp.database.GolfFinderDao
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import com.golfcourse.finderapp.network.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class GolfCourseLocalDataSource(private val golfFinderDao: GolfFinderDao) {



    // this function will be called when saving into room database

    suspend fun addGolfCourse(golfSearchRequest: GolfSearchRequest){

        golfFinderDao.addGolfCourse(golfSearchRequest)
    }

    // this function will be called when user want to delete all golf courses

    suspend fun deleteAllGolfCourse(){
        golfFinderDao.deleteallGolfCourse()

    }

    // this function will be called if any data is occurs then it will display into recent searches.

    suspend fun getAllGolfCourses(): List<GolfSearchRequest> {
        return golfFinderDao.getAllGolfCourses()
    }


}