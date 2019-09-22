package com.example.advancedagger2.ui.auth

import androidx.lifecycle.*
import com.example.advancedagger2.SessionManager
import com.example.advancedagger2.models.Users
import com.example.advancedagger2.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function


/**
 * Auth Api View Model Class
 */
class AuthViewModel @Inject constructor(private var authApi: AuthApi,
                                        private var sessionManager: SessionManager) : ViewModel() {

    private val TAG = AuthViewModel::class.java.name

    private val compositeDisposable = CompositeDisposable()


    fun authenticateUserById(userID: Int) {

        sessionManager.authenticatedWithId(queryUserId(userID))
    }

    fun queryUserId(userID: Int) : LiveData<AuthResource<Users>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUserById(userID)
                .onErrorReturn(object : Function<Throwable, Users> {
                    override fun apply(t: Throwable): Users {
                        val user = Users()
                        user.id = -1
                        return user
                    }

                })
                .map(object : Function<Users, AuthResource<Users>> {
                    override fun apply(user: Users): AuthResource<Users> {
                        if (user.id == -1) {
                            return AuthResource.error("Could not authenticated", null)
                        }
                        return AuthResource.authenticated(user)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeUser(): LiveData<AuthResource<Users>> {
        return sessionManager.getAuthUser()
    }



    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }

}