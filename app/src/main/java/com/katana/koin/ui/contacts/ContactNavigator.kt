package com.katana.koin.ui.contacts

interface ContactNavigator {

    fun getResponse(id : Long)

    fun deleteMemberResponse(deleted :Int)

    fun handleError(error: Throwable)

}