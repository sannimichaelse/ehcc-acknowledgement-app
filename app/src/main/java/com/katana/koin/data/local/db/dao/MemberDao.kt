package com.katana.koin.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.katana.koin.data.local.db.entities.Member

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMember(data:Member) : Long

    @Query("SELECT * FROM ${Member.TABLE_NAME} ORDER BY id DESC")
    fun getAllMembers(): List<Member>

    @Delete
    fun deleteMember(member: Member): Int

    @Query("DELETE FROM ${Member.TABLE_NAME} WHERE ${Member.EMAIL} = :email")
    fun deleteByEmail(email: String): Int

    @Update
    fun updateMember(member: Member)

}