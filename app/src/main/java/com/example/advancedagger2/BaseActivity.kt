package com.example.advancedagger2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.advancedagger2.ui.auth.AuthActivity
import com.example.advancedagger2.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Base Activity Class
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    val TAG = BaseActivity::class.java.name

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
    }

    /**
     * Subscribe to observer to check the authentication status from the base class
     */
    private fun subscribeObserver() {
        sessionManager.getAuthUser().observe(this, Observer {authResource ->
            //Log.d(TAG,  "On Change: ${authResource.emailAddress}")
            when(authResource.status) {
                AuthResource.AuthStatus.LOADING -> {

                }

                AuthResource.AuthStatus.AUTHENTICATED -> {

                    Log.d(TAG,  "Login Success: ${authResource.data?.emailAddress}")
                }

                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    navLoginScreen()
                }

                AuthResource.AuthStatus.ERROR -> {

                }
            }
        })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}