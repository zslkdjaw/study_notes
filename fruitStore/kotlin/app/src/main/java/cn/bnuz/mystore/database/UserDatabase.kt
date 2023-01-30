package cn.bnuz.mystore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cn.bnuz.mystore.entity.User


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
}