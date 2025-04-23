package com.golfcourse.finderapp

import android.app.Application
import android.content.Context


class GolfCourseApp : Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: GolfCourseApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}