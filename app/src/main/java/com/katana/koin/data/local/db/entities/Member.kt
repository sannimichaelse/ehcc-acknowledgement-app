package com.katana.koin.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

/**
 * Created by Sanni Michael Tomiwa
 */
@Entity(tableName = Member.TABLE_NAME)
class Member(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID) var id: Int? = null,
        @ColumnInfo(name = NAME) var name: String? = null,
        @ColumnInfo(name = EMAIL) var email: String? = null,
        @ColumnInfo(name = PHONE) var phone: String? = null,
        @ColumnInfo(name = DATE) var date: Date? = Date()) {

    companion object {
        const val TABLE_NAME = "member"
        const val ID = "id"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val DATE = "date"
    }

}