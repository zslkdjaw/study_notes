package cn.bnuz.mystore.entity

import androidx.versionedparcelable.ParcelField
import java.io.Serializable


data class Goods (
    val goodsId: Int,
    val goodsName: String,
    val goodsPrice: Double,
    val goodsStocks: Int,
    val goodsDescription: String,
    val filePath : String
) :Serializable