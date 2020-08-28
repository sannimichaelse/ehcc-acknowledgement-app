package com.katana.koin.data.local.db.dao

import androidx.room.*
import com.katana.koin.data.local.db.entities.Mail

@Dao
interface MailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMail(data: Mail) : Long

    @Query("SELECT * FROM ${Mail.TABLE_NAME} ORDER BY id DESC")
    fun getAllMails(): List<Mail>

}