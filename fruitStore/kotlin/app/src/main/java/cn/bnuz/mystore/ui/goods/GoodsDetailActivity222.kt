package cn.bnuz.mystore.ui.goods


import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.bnuz.mystore.R
import cn.bnuz.mystore.databinding.ActivityGoodsdetailBinding
import cn.bnuz.mystore.entity.Goods


class GoodsDetailActivity2 : AppCompatActivity() {

//    private lateinit var binding: ActivityGoodsdetailBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityGoodsdetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//         var titleTextView: TextView = binding.detailTitle
//        var descTextView : TextView = binding.detailDescription
//        var priceTextView : TextView = binding.detailPrice
//         var addBtn : Button = binding.addCart
//        val extraData = intent.getSerializableExtra("data") as Goods
//
//        titleTextView.text = extraData.goodsName
//        descTextView.text = extraData.goodsDescription
//        priceTextView.text = extraData.goodsPrice.toString()
//
//        addBtn.setOnClickListener{
//            Toast.makeText(this," 添加成功! ", Toast.LENGTH_SHORT).show()
//            finish()
//        }
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home){
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
}