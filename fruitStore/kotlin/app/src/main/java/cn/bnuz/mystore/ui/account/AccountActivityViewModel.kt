package cn.bnuz.mystore.ui.account



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.bnuz.mystore.ApiRepository
import cn.bnuz.mystore.entity.User


class AccountActivityViewModel : ViewModel() {
    var loginOk =  MutableLiveData<Boolean>()
    private val apiRepository = ApiRepository.get()
    fun login(input: User){
        loginOk = apiRepository.login(input) as MutableLiveData<Boolean>
    }
    fun record(user : User){
        apiRepository.record(user)
    }

}