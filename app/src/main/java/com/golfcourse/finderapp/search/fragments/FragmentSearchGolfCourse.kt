package com.golfcourse.finderapp.search.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anbr.anbrsales.common.base.BaseFragment
import com.golfcourse.finderapp.R

import com.golfcourse.finderapp.databinding.FragmentSearchGolfCourseBinding
import com.golfcourse.finderapp.home.adapter.GolfCourseListAdapter
import com.golfcourse.finderapp.search.adapter.RecentSearchAdapter
import com.golfcourse.finderapp.utils.AppConstants
import com.golfcourse.finderapp.utils.ItemClickAdapter
import com.golfcourse.finderapp.utils.toast


class FragmentSearchGolfCourse : BaseFragment() {



    private var _binding: FragmentSearchGolfCourseBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchGolfCourseBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickevents()
        setRecentSearchObserver()
    }
    fun setRecentSearchObserver(){

        // retrieve golf course from room database and set it in recycler view
        golfCourseViewModel.allGolfCourses.observe(viewLifecycleOwner, Observer {List ->




            if(List.isNotEmpty()){

                val uniqueCourses = List.distinctBy { it.name }
                binding.rvRecentSearch.layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,false)
                binding.rvRecentSearch.setHasFixedSize(true)
                val categoryAdapter = RecentSearchAdapter(activity as Context,  uniqueCourses,
                    object : ItemClickAdapter {
                        override fun onItemClick(pos: Int, from: String)
                        {
                            val intent = Intent()
                            intent.putExtra(AppConstants.SEARCH_TEXT,uniqueCourses[pos].name.toString().trim())
                            requireActivity().setResult(Activity.RESULT_OK, intent)
                            requireActivity().finish()
                        }
                    })
                binding.rvRecentSearch.adapter = categoryAdapter
                binding.llRecentSearches.visibility=View.VISIBLE
            }
            else{
                binding.llRecentSearches.visibility=View.GONE


            }


        })
    }


    private fun clickevents() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        // if user press done button from keyboard then it will go to next screen

        binding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val intent = Intent()

                intent.putExtra(AppConstants.SEARCH_TEXT,binding.edtSearch.text!!.toString().trim())
                requireActivity().setResult(Activity.RESULT_OK, intent)
                requireActivity().finish()
                true
            } else {
                false
            }
        }

        // if user press search button from keyboard then it will go to next screen

        binding.txtSearch.setOnClickListener {
            if(binding.edtSearch.text!!.isNotEmpty()){

                val intent = Intent()


                intent.putExtra(AppConstants.SEARCH_TEXT,binding.edtSearch.text!!.toString().trim())
                requireActivity().setResult(Activity.RESULT_OK, intent)
                requireActivity().finish()

            }
            else{
                toast(resources.getString(R.string.no_search))

            }
        }

        // clear all recent search from room database

        binding.rlClear.setOnClickListener {
            golfCourseViewModel.deleteAllGolfCourse()

            binding.llRecentSearches.visibility=View.GONE
        }
    }
}