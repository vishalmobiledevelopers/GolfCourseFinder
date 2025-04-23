package com.golfcourse.finderapp.home.viewmodels

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.codeflixweb.callenza.network.Event
import com.codeflixweb.callenza.network.Resource
import com.golfcourse.finderapp.GolfCourseApp
import com.golfcourse.finderapp.R
import com.golfcourse.finderapp.database.GolfCourseDatabase
import com.golfcourse.finderapp.database.GolfCourseLocalDataSource
import com.golfcourse.finderapp.database.GolfCourseLocalRepository
import com.golfcourse.finderapp.database.GolfFinderDao
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.home.models.golfcoursedetail.GolfCourseDetailResponse
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import com.golfcourse.finderapp.utils.hasInternetConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.File
import java.io.IOException

class GolfCourseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GolfCourseRepository(GolfCourseDataSource())
    var  localRepository : GolfCourseLocalRepository


    // mutable live data for saving data and live data will be observer from fragments

    private val _golfCourseListResponse = MutableLiveData<Event<Resource<GolfCourseListResponse>>>()
    val golfCourseListResponse: LiveData<Event<Resource<GolfCourseListResponse>>> = _golfCourseListResponse

    private val _golfCourseDetailResponse = MutableLiveData<Event<Resource<GolfCourseDetailResponse>>>()
    val golfCourseDetailResponse: LiveData<Event<Resource<GolfCourseDetailResponse>>> = _golfCourseDetailResponse

    private var _golfCourseLocalListResponse = MutableLiveData<List<GolfSearchRequest>>()
    val allGolfCourses: LiveData<List<GolfSearchRequest>> get() = _golfCourseLocalListResponse


    var golfCourseDao: GolfFinderDao = GolfCourseDatabase.getInstance(application).golfFinderDao()

    init {
        //local repository initialization and getting all golf course from room database
        localRepository = GolfCourseLocalRepository(GolfCourseLocalDataSource(golfCourseDao))
        getAllGolfCourses()
    }

    //this function will be called from fragment it will call repository function and gave response to live data

    fun getGolfCourseList(searchQuery:String) = viewModelScope.launch {



        _golfCourseListResponse.postValue(Event(Resource.Loading()))

        try {

            // check internet connection

            if (getApplication<GolfCourseApp>().hasInternetConnection()) {
                val response = repository.getGolfCourseList(searchQuery)
                _golfCourseListResponse.postValue(handleGofCourseListResponse(response))
            } else {
                _golfCourseListResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<GolfCourseApp>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        } catch (t: Throwable) {

            // if any error comes then it throws error and display it.

            _golfCourseDetailResponse.postValue(
                Event(
                    Resource.Error(t.message.toString()
                    )
                )
            )


        }
    }
    //this function will be called from fragment it will call repository function and gave response to live data


    fun getGolfCourseDetail(course_id:String) = viewModelScope.launch {

        _golfCourseDetailResponse.postValue(Event(Resource.Loading()))

        try {

            // check internet connection

            if (getApplication<GolfCourseApp>().hasInternetConnection()) {
                val response = repository.getGolfCourseDetail(course_id)
                _golfCourseDetailResponse.postValue(handleGofCourseDetailResponse(response))
            } else {
                _golfCourseDetailResponse.postValue(
                    Event(
                        Resource.Error(
                            getApplication<GolfCourseApp>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                )
            }
        } catch (t: Throwable) {

            // if any error comes then it throws error and display it.


            _golfCourseDetailResponse.postValue(
                Event(
                    Resource.Error(t.message.toString()
                    )
                )
            )
        }
    }




    fun addGolfCourse(golfSearchRequest: GolfSearchRequest) = viewModelScope.launch(
        Dispatchers.IO) {

        localRepository.addGolfCourse(golfSearchRequest)
    }
    fun deleteAllGolfCourse() = viewModelScope.launch(
        Dispatchers.IO) {

        localRepository.deleteAllGolfCourse()
    }

    fun getAllGolfCourses() = viewModelScope.launch(
        Dispatchers.IO) {
        _golfCourseLocalListResponse.postValue(localRepository.getAllGolfCourses())
    }

    private fun handleGofCourseListResponse(response: Response<GolfCourseListResponse>): Event<Resource<GolfCourseListResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

    private fun handleGofCourseDetailResponse(response: Response<GolfCourseDetailResponse>): Event<Resource<GolfCourseDetailResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }
}