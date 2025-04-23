/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.golfcourse.finderapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.utils.AppConstants

/**
 * The Room database for this app
 */
@Database(entities = [GolfSearchRequest::class], version = 1, exportSchema = false)
abstract class GolfCourseDatabase : RoomDatabase() {

    abstract fun golfFinderDao(): GolfFinderDao


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: GolfCourseDatabase? = null

        fun getInstance(context: Context): GolfCourseDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GolfCourseDatabase {

            // created the database and return the instance
            return Room.databaseBuilder(
                context,
                GolfCourseDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).build()
        }
    }
}