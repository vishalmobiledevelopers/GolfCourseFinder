package com.golfcourse.finderapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.golfcourse.finderapp.database.models.GolfSearchRequest

@Dao
interface GolfFinderDao {


    // this function will retrieve data from golfSearch Table

    @Query("SELECT * FROM golfSearch")
    fun getAllGolfCourses(): List<GolfSearchRequest>

    //this function will adding  data to golfSearch Table

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGolfCourse(golfSearchRequest: GolfSearchRequest)

    //this function will delete all golf course records

    @Query("DELETE FROM golfSearch")
    suspend fun deleteallGolfCourse()
}