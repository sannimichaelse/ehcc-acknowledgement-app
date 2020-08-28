package com.katana.koin.data

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.katana.koin.data.local.db.DbHelper
import com.katana.koin.data.local.db.entities.Mail
import com.katana.koin.data.local.db.entities.Member
import com.katana.koin.data.local.prefs.PrefsHelper
import com.katana.koin.data.remote.ApiHelper
import com.katana.koin.data.remote.model.MailResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tomiwa on 10:14 2018-12-19
 */
class AppDataManager constructor(private val prefsHelper: PrefsHelper,private val dbHelper: DbHelper,private val apiHelper: ApiHelper) : DataManager {

    override fun getUser(): String? = prefsHelper.getUser()

    override fun saveUser(user: String) = prefsHelper.saveUser(user)

    override fun getLoginMode(): Boolean = prefsHelper.getLoginMode()

    override fun saveLoginMode(mode: Boolean) = prefsHelper.saveLoginMode(mode)

    override fun getAllMember(): List<Member> = dbHelper.getAllMember()

    override fun insertMember(member: Member): Observable<Long> = dbHelper.insertMember(member)

    override fun getAllMail(): List<Mail>  = dbHelper.getAllMail()

    override fun insertMail(mail: Mail): Observable<Long> = dbHelper.insertMail(mail)

    override fun deleteMemberByEmail(email: String): Observable<Int> =  dbHelper.deleteMemberByEmail(email)

    override fun sendMail(params: MutableMap<String, Any>): Single<MailResponse> = apiHelper.sendMail(params)

}