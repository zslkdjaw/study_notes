package cn.bnuz.mystore.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Car(
    var carId: Int = -1,
    var userId: Int = -1,
    var price: Double = 0.00,
    @SerializedName("goodsList")
    var goodsList: List<Goods> = emptyList()
):Serializable