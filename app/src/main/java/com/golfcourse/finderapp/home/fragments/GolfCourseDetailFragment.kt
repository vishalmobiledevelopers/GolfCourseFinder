package com.golfcourse.finderapp.home.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anbr.anbrsales.common.base.BaseFragment
import com.codeflixweb.callenza.network.Resource
import com.golfcourse.finderapp.R
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.databinding.FragmentGolfCourseDetailBinding
import com.golfcourse.finderapp.databinding.FragmentSplashBinding
import com.golfcourse.finderapp.home.adapter.GolfCourseListAdapter
import com.golfcourse.finderapp.home.models.golfcoursedetail.GolfCourseDetailResponse
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import com.golfcourse.finderapp.utils.AppConstants
import com.golfcourse.finderapp.utils.ItemClickAdapter
import com.golfcourse.finderapp.utils.toast
import com.google.gson.Gson


class GolfCourseDetailFragment : BaseFragment() {



    private var _binding: FragmentGolfCourseDetailBinding? = null

    lateinit var golfCourseDetailResponse: GolfCourseDetailResponse


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGolfCourseDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // retrieve course id from previous fragment and call that course detail api

        val course_id=requireArguments().getString(AppConstants.COURSE_ID)









        golfCourseViewModel.getGolfCourseDetail(course_id!!)


        golfCourseDetailObserver()

        clickevents()



    }
    fun clickevents(){

        // click events

        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            txtAddress.setOnClickListener {

                val destinationLat = golfCourseDetailResponse.course.location.latitude
                val destinationLng = golfCourseDetailResponse.course.location.longitude

                val gmmIntentUri = Uri.parse("google.navigation:q=$destinationLat,$destinationLng")

                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                    setPackage("com.google.android.apps.maps")
                }

                if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(mapIntent)
                } else {
                    toast(resources.getString(R.string.no_google_map))

                }
            }
        }
    }
    private fun golfCourseDetailObserver() {

        // called when course detail data has been received from mutablelive data

        golfCourseViewModel.golfCourseDetailResponse.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        progressDialog.dismiss()

                        response.data?.let {
                            golfCourseDetailResponse=it
                            setGolfCourseData()

                        }
                    }

                    is Resource.Error -> {
                        response.message?.let { message ->
                            toast(message)
                        }

                        progressDialog.dismiss()




                    }

                    is Resource.Loading -> {

                        progressDialog.show()

                    }

                    else -> {}
                }

            }
        }
    }
    private fun setGolfCourseData() {

        // set data into views from golfCourseDetailResponse object


        val ratingCount=golfCourseDetailResponse.course.tees.female[0].course_rating.toString()

        with(binding){
            txtCourseName.text=golfCourseDetailResponse.course.course_name
            txtClubName.text=golfCourseDetailResponse.course.club_name
            txtAddress.text=golfCourseDetailResponse.course.location.address
            txtRating.text=ratingCount.toString()

        }
    }


}