package com.example.advancedagger2.di.main

import com.example.advancedagger2.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Main Fragment Builder Module which is only accessible from Main Activity
 */
@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment
}