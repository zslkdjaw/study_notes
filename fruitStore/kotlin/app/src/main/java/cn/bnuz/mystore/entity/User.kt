package cn.bnuz.mystore.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class User(
    @PrimaryKey
    var userId: Int? = -1,
    var userName: String? = "",
    var userPassword: String? = "",
    var userAddress: String? = "",
    var userPhone: String?= ""
) : Serializable
