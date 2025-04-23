package com.golfcourse.finderapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.golfcourse.finderapp.R

fun Activity.toast(text: String) {

    val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
    toast.show()


    val handler = Handler()
    handler.postDelayed({
        toast.cancel() // Cancel the toast after 1 second
    }, 2000)

}
fun Fragment.toast(text: String) {

    val toast = Toast.makeText(this.context, text, Toast.LENGTH_SHORT)
    toast.show()


    val handler = Handler()
    handler.postDelayed({
        toast.cancel() // Cancel the toast after 1 second
    }, 2000)

}


fun Fragment.setProgressDialog(context:Context): Dialog {
    val dialog = Dialog(context)
    val inflate = LayoutInflater.from(context).inflate(R.layout.row_progress, null)
    dialog.setContentView(inflate)
    dialog.setCancelable(false)
    dialog.window!!.setBackgroundDrawable(
        ColorDrawable(Color.WHITE)
    )
    return dialog
}



