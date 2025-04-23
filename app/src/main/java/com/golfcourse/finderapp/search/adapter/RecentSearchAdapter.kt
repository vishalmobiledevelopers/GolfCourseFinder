package com.golfcourse.finderapp.search.adapter


import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.databinding.RowGolfCourseListBinding
import com.golfcourse.finderapp.databinding.RowSearchBinding
import com.golfcourse.finderapp.home.models.golfcourselist.Course
import com.golfcourse.finderapp.utils.ItemClickAdapter


class RecentSearchAdapter(
    var ctx: Context, private val golfcourseRequestList: List<GolfSearchRequest>,
    val listener: ItemClickAdapter
) :RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowSearchBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        val golfCourse = golfcourseRequestList[position]

        holder.binding.txtName.setText(golfCourse.name.toString())

        holder.itemView.setOnClickListener {
            val pos=position
            listener.onItemClick(pos,"Category")
        }
    }

    override fun getItemCount(): Int {
        return golfcourseRequestList.size
    }

    inner class ViewHolder(val binding: RowSearchBinding) :
        RecyclerView.ViewHolder(binding.root)



}