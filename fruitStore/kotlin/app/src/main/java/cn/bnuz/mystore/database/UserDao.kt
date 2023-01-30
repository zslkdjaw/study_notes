package cn.bnuz.mystore.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cn.bnuz.mystore.entity.User

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query("SELECT * FROM user")
    fun getUser(): List<User>
    @Query("DELETE FROM User")
    fun deleteAll()
}