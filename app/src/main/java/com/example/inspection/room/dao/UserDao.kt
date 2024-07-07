package com.example.inspection.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.inspection.room.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(vararg user: User)

   /* @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT id,email,password FROM users WHERE email = :email AND password = :password")
    fun validateUser(email: String, password: String) : User*/
}