package com.golfcourse.finderapp.activity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.golfcourse.finderapp.databinding.ActivityGolfCourseBinding


class GolfCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGolfCourseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGolfCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}