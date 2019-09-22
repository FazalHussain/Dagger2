package com.example.advancedagger2.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.advancedagger2.SessionManager
import javax.inject.Inject

/**
 * Profile View Model
 */
class ProfileViewModel @Inject constructor(sessionManager: SessionManager) : ViewModel() {

    lateinit var sessionManager: SessionManager

    companion object {
        val TAG = ProfileViewModel::class.java.name
    }

    init {
        this.sessionManager = sessionManager
    }

    fun getAuthenticatedUser() = sessionManager.getAuthUser()

}