package com.example.advancedagger2.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.advancedagger2.R
import com.example.advancedagger2.models.Users
import com.example.advancedagger2.ui.auth.AuthResource
import com.example.advancedagger2.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

/**
 * Profile Fragment
 */
class ProfileFragment : DaggerFragment() {


    lateinit var profileViewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    companion object {
        val TAG = ProfileFragment::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.d(TAG, "Profile Fragment")

        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "Profile View Holder was created....")

        profileViewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel::class.java)

        subscribeObservers()

    }

    private fun subscribeObservers() {
        profileViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when(resource.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserInfo(resource.data)
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        setErrorMessage(resource.message)
                    }

                    else -> {

                    }
                }
            }
        })
    }

    private fun setErrorMessage(message: String?) {
        email.text = message
        username.text = "Error"
        website.text = "Error"
    }

    private fun setUserInfo(data: Users?) {
        email.text = data?.emailAddress
        username.text = data?.userName
        website.text = data?.website
    }
}