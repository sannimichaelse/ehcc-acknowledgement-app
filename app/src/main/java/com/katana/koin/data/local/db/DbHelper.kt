package com.katana.koin.data.local.db

import androidx.lifecycle.LiveData
import com.katana.koin.data.local.db.entities.Mail
import com.katana.koin.data.local.db.entities.Member
import io.reactivex.Observable

interface DbHelper {

    fun getAllMember(): List<Member>

    fun insertMember(member: Member): Observable<Long>

    fun getAllMail(): List<Mail>

    fun insertMail(mail: Mail): Observable<Long>

    fun deleteMemberByEmail(email:String): Observable<Int>

}