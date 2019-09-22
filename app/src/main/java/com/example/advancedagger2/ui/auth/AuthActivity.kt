package com.example.advancedagger2.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.advancedagger2.R
import com.example.advancedagger2.ui.main.MainActivity
import com.example.advancedagger2.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var logo: Drawable

    val TAG = AuthActivity::class.java.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel::class.java)

        setLogo()

        loginButtonClick()

        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.observeUser().observe(this, Observer {authResource ->
            //Log.d(TAG,  "On Change: ${authResource.emailAddress}")
            when(authResource.status) {
                AuthResource.AuthStatus.LOADING -> {
                    showProgressBar(true)
                }

                AuthResource.AuthStatus.AUTHENTICATED -> {
                    showProgressBar(false)
                    Log.d(TAG,  "Login Success: ${authResource.data?.emailAddress}")
                    onLoginSuccess()
                }

                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    showProgressBar(false)

                }

                AuthResource.AuthStatus.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(this,
                        "${authResource.message} \nDid you entered user id between 1 and 10?",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    /**
     * Navigate to [MainActivity] from [AuthActivity] on login success
     */
    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Show and hide the progress bar
     *
     * @param isVisible Boolean indicating that show or hide the progressbar
     *
     */
    private fun showProgressBar(isVisible: Boolean) {
        progress_bar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun loginButtonClick() {
        login_button.setOnClickListener {
            if (TextUtils.isEmpty(user_id_input.text.toString())) return@setOnClickListener

            atemptLogin()
        }
    }

    private fun atemptLogin() {
        val userID = Integer.parseInt(user_id_input.text.toString())
        viewModel.authenticateUserById(userID)
    }

    /**
     * Set the logo in the image view
     */
    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }


}
