package com.katana.koin.data.local.db

import androidx.lifecycle.LiveData
import com.katana.koin.data.local.db.entities.Mail
import com.katana.koin.data.local.db.entities.Member
import io.reactivex.Observable


class AppDbHelper constructor(private val appDatabase: AcknowledgementDatabase) : DbHelper {

    override fun getAllMember(): List<Member> = appDatabase.memberDao().getAllMembers()

    override fun insertMember(member: Member): Observable<Long> {
        return Observable.fromCallable { appDatabase.memberDao().insertMember(member) }
    }

    override fun getAllMail(): List<Mail> = appDatabase.mailDao().getAllMails()

    override fun insertMail(mail: Mail): Observable<Long> {
        return Observable.fromCallable { appDatabase.mailDao().insertMail(mail)}
    }

    override fun deleteMemberByEmail(email: String): Observable<Int> {
        return Observable.fromCallable {
            appDatabase.memberDao().deleteByEmail(email)
        }
    }
}