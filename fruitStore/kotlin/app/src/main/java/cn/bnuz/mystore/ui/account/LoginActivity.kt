package cn.bnuz.mystore.ui.account

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.MainActivity

import cn.bnuz.mystore.base.BaseActivity
import cn.bnuz.mystore.databinding.ActivityLoginBinding
import cn.bnuz.mystore.entity.User
import org.greenrobot.eventbus.EventBus

private const val TAG = "LoginActivity"
class LoginActivity : BaseActivity<ActivityLoginBinding>(){
    private lateinit var nameEditText : EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnLog : Button

    private val accountViewModel:AccountActivityViewModel by lazy{
        ViewModelProvider(this)[AccountActivityViewModel::class.java]
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initView(binding: ActivityLoginBinding) {
        nameEditText = binding.inputName
        passwordEditText = binding.inputPassword
        btnLog = binding.buttonLog

        btnLog.setOnClickListener{
            //ApiRepository.get().erasureAll()
            if (nameEditText.text.toString() == "" || passwordEditText.text.toString() == ""){
                Toast.makeText(this, "输入不能为空 !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val tuser = User()
            tuser.userName = nameEditText.text.toString()
            tuser.userPassword = passwordEditText.text.toString()
            accountViewModel.login(tuser)
            accountViewModel.loginOk.observe(this, Observer { reaLogin -> reaLogin.let {
                if (reaLogin) {
                    Toast.makeText(this, "登录成功 !", Toast.LENGTH_SHORT).show()
                    //val intent = Intent()
                    //intent.setClass(this@LoginActivity,MainActivity::class.java)

                    setResult(Activity.RESULT_OK)
                    //刷新activity
                    finish()
                    //startActivity(intent)

                }else{
                    Toast.makeText(this, "登录失败,请检查账号或密码 !", Toast.LENGTH_SHORT).show()
                }
            }
            })
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}