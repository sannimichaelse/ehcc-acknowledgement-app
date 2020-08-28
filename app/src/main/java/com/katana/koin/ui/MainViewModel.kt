package com.katana.koin.ui

import com.google.gson.Gson
import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

/**
 * Created by tomiwa on 10:13 2018-12-19
 */
class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, private val gson: Gson) :
        BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    fun saveUser(user: String) = dataManager.saveUser("Ahihi")

    fun getUser() = dataManager.getUser()
}