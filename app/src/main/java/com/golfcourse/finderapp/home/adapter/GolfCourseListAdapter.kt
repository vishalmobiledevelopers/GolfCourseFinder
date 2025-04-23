package com.golfcourse.finderapp.home.adapter


import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.golfcourse.finderapp.R
import com.golfcourse.finderapp.databinding.RowGolfCourseListBinding
import com.golfcourse.finderapp.home.models.golfcourselist.Course
import com.golfcourse.finderapp.utils.ItemClickAdapter


class GolfCourseListAdapter(
    var ctx: Context, private val golfcourseList: List<Course>,
    val listener: ItemClickAdapter
) :RecyclerView.Adapter<GolfCourseListAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowGolfCourseListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // set data into views from List



        val golfCourse = golfcourseList[position]

        holder.binding.txtCourseName.text = golfCourse.course_name

        holder.binding.txtClubName.text = golfCourse.club_name


        holder.binding.txtAddress.text = golfCourse.location.address


        // check if female tee is available or not

        // api will not give female data that's why adding try catch block otherwise check empty data

        try {
            val ratings=golfCourse.tees.female[0].course_rating.toString().trim()
            holder.binding.txtRating.text=ratings.toString()
        }
        catch (e:Exception){
            holder.binding.txtRating.text=ctx.resources.getString(R.string.no_rating)
        }



        //click events when user preess that raw
        
        holder.itemView.setOnClickListener {
            val pos=position
            listener.onItemClick(pos,"Category")
        }
    }

    override fun getItemCount(): Int {
        return golfcourseList.size
    }

    inner class ViewHolder(val binding: RowGolfCourseListBinding) :
        RecyclerView.ViewHolder(binding.root)



}