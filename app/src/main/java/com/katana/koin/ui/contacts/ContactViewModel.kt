package com.katana.koin.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.katana.koin.base.BaseViewModel
import com.katana.koin.data.DataManager
import com.katana.koin.data.local.db.AcknowledgementDatabase
import com.katana.koin.data.local.db.entities.Mail
import com.katana.koin.data.local.db.entities.Member
import com.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ContactViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
        BaseViewModel<ContactNavigator>(dataManager, schedulerProvider){

    fun getMails(): List<Mail>{
        return dataManager.getAllMail()
    }

    fun saveLoginMode(mode: Boolean){
        dataManager.saveLoginMode(mode)
    }

    fun getMembers(): List<Member>{
        return dataManager.getAllMember()
    }


    fun saveMember(member: Member) {
        compositeDisposable.add(dataManager.insertMember(member)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({
                    getNavigator().getResponse(it)
                }, {
                    getNavigator().handleError(it)
                }))
    }

    fun deleteMember(email: String) {
        println("got here too")
        compositeDisposable.add(dataManager.deleteMemberByEmail(email)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({
                    getNavigator().deleteMemberResponse(it)
                }, {
                    getNavigator().handleError(it)
                }))
    }

}