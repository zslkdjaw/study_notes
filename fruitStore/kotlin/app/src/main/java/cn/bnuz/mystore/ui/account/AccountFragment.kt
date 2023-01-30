package cn.bnuz.mystore.ui.account

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.MainActivity
import cn.bnuz.mystore.R
import cn.bnuz.mystore.base.BaseFragment
import cn.bnuz.mystore.entity.User
import org.greenrobot.eventbus.EventBus

private const val TAG = "AccountFragment"

class AccountFragment : BaseFragment() {
    private lateinit var nameTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var loginBtn: Button

    override fun getLayoutRestId(): Int {
        return R.layout.fragment_account
    }

    private lateinit var user: User
    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
            }
        }
    override fun initView(rootView: View?) {

        nameTextView = rootView!!.findViewById(R.id.user_name)
        phoneTextView = rootView!!.findViewById(R.id.user_phone)
        addressTextView = rootView!!.findViewById(R.id.user_address)
        loginBtn = rootView.findViewById(R.id.button_login)

    }

    override fun onResume() {
        super.onResume()
        val paren = activity as (MainActivity)
        this.user = paren.getLoginState()
        if (this.user.userId != -1) {
            nameTextView.text = user.userName
            phoneTextView.text = user.userPhone
            addressTextView.text = user.userAddress
            loginBtn.text = "注销"
            loginBtn.setOnClickListener {
                ApiRepository.get().erasureAll()
                EventBus.getDefault().postSticky(User())
                Toast.makeText(activity, "当前账号已经退出 !", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).onBackPressed()
            }
        } else {
            loginBtn.setOnClickListener {

                val intent = Intent(activity as MainActivity,LoginActivity::class.java)
                startForResult.launch(intent)
                //startActivityForResult(intent, 110)


            }
            Toast.makeText(activity, "请登录 !", Toast.LENGTH_SHORT).show()
        }
    }


}