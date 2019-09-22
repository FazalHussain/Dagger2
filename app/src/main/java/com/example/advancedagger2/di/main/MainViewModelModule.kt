package com.example.advancedagger2.di.main

import androidx.lifecycle.ViewModel
import com.example.advancedagger2.di.ViewModelKey
import com.example.advancedagger2.ui.auth.AuthViewModel
import com.example.advancedagger2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Profile View Model Module
 */
@Module
abstract class MainViewModelModule {


    /**
     * Binds the Profile View Model dependency with [ViewModelKey] to group similar [ViewModel]
     *
     * Under the hood it is providing [ProfileViewModel]
     */
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel) : ViewModel
}