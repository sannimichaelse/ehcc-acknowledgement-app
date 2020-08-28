package com.katana.koin.ui.send_mail

import androidx.databinding.ObservableBoolean
import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.katana.koin.data.local.db.entities.Mail
import com.katana.koin.data.local.db.entities.Member
import com.utils.SchedulerProvider

class MailViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<MailNavigator>(dataManager, schedulerProvider){

    private val mIsLoading = ObservableBoolean()

    fun getMembers(): List<Member>{
        return dataManager.getAllMember()
    }

    fun sendMail(params: MutableMap<String, Any>) {
        setIsLoading(true)
        compositeDisposable.add(dataManager.sendMail(params)
                .compose(schedulerProvider.ioToMainSingleScheduler())
                .subscribe({
                    setIsLoading(false)
                    saveMail(params)
                    getNavigator().handleMailResponse(it)
                }, {
                    setIsLoading(false)
                    getNavigator().handleMailError(it)
                }))
    }



    fun getIsLoading(): ObservableBoolean? {
        return mIsLoading
    }

    private fun saveMail(params: MutableMap<String, Any>){
        val total = params["total"].toString().replace(",","").toFloatOrNull();
        val mail = Mail(to = params["email"].toString(), name = params["name"].toString(), total = total , tithe = params["tithe"].toString().replace(",",""), offering = params["offering"].toString().replace(",",""), seed = params["seed"].toString().replace(",",""), others = params["others"].toString().replace(",",""))
        compositeDisposable.add(dataManager.insertMail(mail)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({
                   getNavigator().handleSaveMailResponse(it)
                }, {
                    getNavigator().handleMailError(it)
                }))
    }

    private fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }
}