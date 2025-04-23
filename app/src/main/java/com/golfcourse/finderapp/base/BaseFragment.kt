package com.anbr.anbrsales.common.base



import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.golfcourse.finderapp.home.viewmodels.GolfCourseViewModel
import com.golfcourse.finderapp.utils.setProgressDialog


open class BaseFragment : Fragment() {


    val golfCourseViewModel: GolfCourseViewModel by viewModels()


    lateinit var progressDialog : Dialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // progress dialog and view model instantiate and will be used in every project
        progressDialog=setProgressDialog(requireContext())

    }

}