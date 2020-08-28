package com.katana.koin.ui.splash

import com.google.gson.Gson
import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider, private val gson: Gson) :
        BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    fun gotoLoginActivity(){
        getNavigator().openLoginActivity()
    }

    fun gotoDashboardActivity(){
        getNavigator().openDashboardActivity()
    }

    fun getLoginMode(): Boolean{
        return dataManager.getLoginMode();
    }
}