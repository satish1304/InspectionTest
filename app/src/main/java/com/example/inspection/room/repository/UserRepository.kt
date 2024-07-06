package com.example.inspection.room.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.inspection.room.dao.UserDao
import com.example.inspection.room.entity.User

class UserRepository(private val userDao: UserDao) {

    //val allUsers: LiveData<List<User>> = userDao.getAllUsers()

   /* @WorkerThread
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }*/

   /* @WorkerThread
    suspend fun validateUser(email: String, password: String) : Boolean {
        return userDao.validateUser(email, password)
    }*/
}