package com.katana.koin.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.katana.koin.data.local.db.dao.MailDao
import com.katana.koin.data.local.db.dao.MemberDao
import com.katana.koin.data.local.db.entities.Mail
import com.katana.koin.data.local.db.entities.Member


@Database(entities = [Member::class, Mail::class], version = DB_VERSION)
@TypeConverters(Converter::class)
abstract class AcknowledgementDatabase : RoomDatabase (){

    abstract fun memberDao(): MemberDao

    abstract fun mailDao(): MailDao

}

const val DB_VERSION = 2
const val DB_NAME = "ehccacknowledgment.db"