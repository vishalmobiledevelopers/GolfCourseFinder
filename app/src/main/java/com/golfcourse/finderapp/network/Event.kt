package com.codeflixweb.callenza.network

import android.util.Log

open class Event<out T>(private val content: T) {

    // this function is used to handle event if multiple times called then it will give first time content only.

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled() = if (hasBeenHandled) {
        null
    } else {


        hasBeenHandled = true
        content
    }

    fun peekContent() = content
}
