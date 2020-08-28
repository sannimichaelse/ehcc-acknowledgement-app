package com.katana.koin.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

/**
 * Created by Sanni Michael
 */
@Entity(tableName = Mail.TABLE_NAME)
class Mail(@PrimaryKey(autoGenerate = true)
           @ColumnInfo(name = ID) var id: Int? = null,
           @ColumnInfo(name = TO) var to: String? = null,
           @ColumnInfo(name = NAME) var name: String? = null,
           @ColumnInfo(name = TITHE) var tithe: String? = null,
           @ColumnInfo(name = OFFERING) var offering: String? = null,
           @ColumnInfo(name = SEED) var seed: String? = null,
           @ColumnInfo(name = OTHERS) var others: String? = null,
           @ColumnInfo(name = TOTAL) var total: Float? = null,
           @ColumnInfo(name = DATE) var date: Date? = Date()) {

    companion object {
        const val TABLE_NAME = "mail"
        const val ID = "id"
        const val NAME = "name"
        const val TO = "email"
        const val TITHE = "tithe"
        const val OFFERING = "offering"
        const val SEED = "seed"
        const val OTHERS = "others"
        const val TOTAL = "total"
        const val DATE = "date"
    }

}