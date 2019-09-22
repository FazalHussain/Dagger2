package com.example.advancedagger2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.example.advancedagger2.models.Users
import com.example.advancedagger2.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Session Manager Class
 */
@Singleton
class SessionManager @Inject constructor() {

    val TAG = SessionManager::class.java.name

    //Mediator live data is a sub class of live data which may observe other LiveData
    // objects and react on OnChanged events from them.
    private val cachedUser = MediatorLiveData<AuthResource<Users>>()

    fun authenticatedWithId(source: LiveData<AuthResource<Users>>) {
        if(cachedUser != null) {
            cachedUser.value = AuthResource.loading(null)
            cachedUser.addSource(source, Observer {
                cachedUser.value = it
                cachedUser.removeSource(source)
            })
        } else {
            Log.d(TAG, "authenticatedWithId: previous session detected")
        }
    }

    fun logout() {
        Log.d(TAG, "logout: logging out..")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<Users>> {
        return cachedUser
    }


}