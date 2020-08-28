package com.katana.koin.ui.login

import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.utils.SchedulerProvider

class LoginViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<LoginNavigator>(dataManager, schedulerProvider){

    fun onLoginClick() {
        getNavigator().loginClick()
    }

    fun saveLoginMode(mode : Boolean){
        dataManager.saveLoginMode(mode)
    }
}