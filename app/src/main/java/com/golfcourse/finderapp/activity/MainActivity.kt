package com.golfcourse.finderapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.golfcourse.finderapp.databinding.ActivitySplashBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // wait for 3 seconds then start the GolfCourseActivity activity


        Timer().schedule(object : TimerTask() {
            override fun run() {
                // Code to run after 5 seconds
                startActivity(Intent(this@MainActivity, GolfCourseActivity::class.java))
                finish()
            }
        }, 3000)
    }
}