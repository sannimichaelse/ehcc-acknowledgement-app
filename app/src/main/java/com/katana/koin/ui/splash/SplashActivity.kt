package com.katana.koin.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.base.BaseActivity
import com.katana.koin.BR
import com.katana.koin.R
import com.katana.koin.databinding.ActivitySplashBinding
import com.katana.koin.ui.MainActivity
import com.katana.koin.ui.contacts.ContactActivity
import com.katana.koin.ui.login.LoginActivity
import com.utils.Logger
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private val splashViewModel: SplashViewModel by viewModel()

    private val SPLASH_TIME_OUT = 3000

    companion object {
        val logger = Logger.getLogger(SplashActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.error("onCreate")
        logger.error("onCreate")
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getBindingVariable(): Int = BR.splashModel

    override fun updateUI(savedInstanceState: Bundle?) {
        splashViewModel.setNavigator(this)
        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        } else {
            Handler().postDelayed({
                val loggedIn = splashViewModel.getLoginMode()
                if(loggedIn){
                    splashViewModel.gotoDashboardActivity()
                }else{
                    splashViewModel.gotoLoginActivity()
                }
                finish()
            }, SPLASH_TIME_OUT.toLong())
        }
    }

    override fun getViewModel(): SplashViewModel = splashViewModel

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

    override fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun openDashboardActivity() {
        val intent = Intent(this, ContactActivity::class.java)
        startActivity(intent);
    }
}
