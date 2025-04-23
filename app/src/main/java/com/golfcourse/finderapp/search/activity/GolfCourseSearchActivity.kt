package com.golfcourse.finderapp.search.activity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.golfcourse.finderapp.databinding.ActivityGolfCourseBinding
import com.golfcourse.finderapp.databinding.ActivitySearchGolfCourseBinding


class GolfCourseSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchGolfCourseBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchGolfCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}