package com.example.practiceanimation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(userInfo: UserInfo)

    @Query("select pw from UserInfo where email = :email")
    fun getPwByEmail(email: String): String

    @Query("select email from UserInfo")
    fun getEmail(): List<String>
}