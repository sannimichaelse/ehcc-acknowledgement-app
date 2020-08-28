package com.katana.koin.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.databinding.ActivityLoginBinding
import com.katana.koin.ui.contacts.ContactActivity
import com.katana.koin.ui.splash.SplashActivity
import com.utils.Logger
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    private val loginViewModel: LoginViewModel by viewModel()

    companion object {
        val logger = Logger.getLogger(SplashActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.error("onCreate")
        logger.error("onCreate")
    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getBindingVariable(): Int = BR.loginModel

    override fun updateUI(savedInstanceState: Bundle?) {
        loginViewModel.setNavigator(this)
    }

    override fun getViewModel(): LoginViewModel = loginViewModel

    override fun loginClick() {

        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            showToast("Please Enter Username and Password")
            return
        }

        if(username == "enthronement" && password == "123456"){
            loginViewModel.saveLoginMode(true)
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }else{
            showToast("Invalid Login Credentials")
        }

    }

    override fun onStart() {
        super.onStart()
        logger.error("onStart")
    }

    override fun onPause() {
        super.onPause()
        logger.error("onPause")
    }

    override fun onResume() {
        super.onResume()
        logger.error("onResume")
    }

    override fun onStop() {
        super.onStop()
        logger.error("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.error("onDestroy")
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_LONG)
        toast.show()
    }
}
