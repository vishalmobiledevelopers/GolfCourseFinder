package com.golfcourse.finderapp.home.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anbr.anbrsales.common.base.BaseFragment
import com.codeflixweb.callenza.network.Resource
import com.golfcourse.finderapp.R
import com.golfcourse.finderapp.database.models.GolfSearchRequest
import com.golfcourse.finderapp.databinding.FragmentGolfCourseListBinding
import com.golfcourse.finderapp.databinding.FragmentSplashBinding
import com.golfcourse.finderapp.home.adapter.GolfCourseListAdapter
import com.golfcourse.finderapp.home.models.golfcourselist.GolfCourseListResponse
import com.golfcourse.finderapp.search.activity.GolfCourseSearchActivity
import com.golfcourse.finderapp.utils.AppConstants
import com.golfcourse.finderapp.utils.ItemClickAdapter
import com.golfcourse.finderapp.utils.toast
import com.google.gson.Gson


class GolfCourseListFragment : BaseFragment() {



    private var _binding: FragmentGolfCourseListBinding? = null

    lateinit var golfCourseListResponse: GolfCourseListResponse



    private val binding get() = _binding!!

    private var searchText=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGolfCourseListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        golfCourseListObserver()
        clickevent()


        // this function will be called when user come from course detail screen and in MI and One Plus phone new fragment will be called that's why we need to call this api


        if(searchText.isNotEmpty()){
            golfCourseViewModel.getGolfCourseList(searchText)
            binding.txtSearch.text=searchText
            binding.rvCourseList.visibility=View.GONE
            binding.llNoData.visibility=View.GONE
        }
    }

    private fun clickevent() {

        // when search view click then open GolfCourseSearchActivity activity and in result launcher will find the user search text

        binding.llHeaderSearch.setOnClickListener {

            val intent = Intent(requireContext(), GolfCourseSearchActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun golfCourseListObserver() {
        // this will called when user search the course name and search api has been called


        golfCourseViewModel.golfCourseListResponse.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        progressDialog.dismiss()

                        response.data?.let {
                            golfCourseListResponse=it
                            setGolfCourseData()

                        }
                    }

                    is Resource.Error -> {
                        // if any error comes then progress dialog dismiss and error will be shown

                        progressDialog.dismiss()

                        response.message?.let { message ->
                            toast(message)
                        }
                    }

                    is Resource.Loading -> {
                        // first time when api call then progress dialog will show

                        progressDialog.show()

                    }

                    else -> {}
                }

            }
        }
    }
    private fun setGolfCourseData() {


        // check if course empty no data message will be display otherwise course list will be display

        if (golfCourseListResponse.courses.isNotEmpty()){
            binding.rvCourseList.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)
            binding.rvCourseList.setHasFixedSize(true)
            val categoryAdapter = GolfCourseListAdapter(activity as Context,  golfCourseListResponse.courses,
                object : ItemClickAdapter {
                    override fun onItemClick(pos: Int, from: String)
                    {

                        // OPEN DETAIL PAGE WHEN USER CLICK ON COURSE LIST
                        val bundle=Bundle()
                        bundle.putString(AppConstants.COURSE_ID,golfCourseListResponse.courses[pos].id.toString())
                        findNavController().navigate(R.id.action_golfCourseListFragment_to_golfCourseDetailFragment,bundle)
                    }
                })

            // hide no data view and show recyclerview
            binding.rvCourseList.adapter = categoryAdapter
            binding.rvCourseList.visibility=View.VISIBLE
            binding.llNoData.visibility=View.GONE
        }
        else{
            // show no data view and hide recyclerview

            binding.rvCourseList.visibility=View.GONE
            binding.llNoData.visibility=View.VISIBLE
            toast(resources.getString(R.string.no_course_found))
        }


        // save search text into room database so that user can view previous searches


        val golfSearchRequest=GolfSearchRequest()
        golfSearchRequest.name=binding.txtSearch.text.toString()

        val gson = Gson()
        val json = gson.toJson(golfCourseListResponse)
        golfSearchRequest.response=json.toString()

        golfCourseViewModel.addGolfCourse(golfSearchRequest)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            // search text display in search view and api will be called
            val data: Intent? = result.data

            val searchTextData=data!!.getStringExtra(AppConstants.SEARCH_TEXT)

            searchText=searchTextData!!

            golfCourseViewModel.getGolfCourseList(searchText)

            binding.txtSearch.text=searchText

            binding.rvCourseList.visibility=View.GONE
            binding.llNoData.visibility=View.GONE

        }
    }

}