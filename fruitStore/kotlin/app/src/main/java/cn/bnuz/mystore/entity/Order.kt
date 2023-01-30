package cn.bnuz.mystore.entity

import java.io.Serializable
import java.sql.Timestamp

data class Order(
    val orderId : Int,
    val userId : Int ,
    val orderPrice : Double,
    val orderTime : Timestamp
):Serializable