package com.example.advancedagger2.di.auth

import androidx.lifecycle.ViewModel
import com.example.advancedagger2.di.ViewModelKey
import com.example.advancedagger2.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    /**
     * Binds the auth view model dependency with [ViewModelKey] to group similar [ViewModel]
     *
     * Under the hood it is providing [com.example.advancedagger2.viewmodel.AuthViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel) : ViewModel
}