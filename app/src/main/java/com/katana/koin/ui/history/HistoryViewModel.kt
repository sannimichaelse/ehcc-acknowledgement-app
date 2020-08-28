package com.katana.koin.ui.history

import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.katana.koin.data.local.db.entities.Mail
import com.utils.SchedulerProvider


class HistoryViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<HistoryNavigator>(dataManager, schedulerProvider) {

    fun getMails(): List<Mail>{
        return dataManager.getAllMail()
    }

}