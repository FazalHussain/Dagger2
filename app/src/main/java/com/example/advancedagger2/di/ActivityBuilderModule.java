package com.example.advancedagger2.di;

import android.app.Activity;
import com.example.advancedagger2.di.auth.AuthModule;
import com.example.advancedagger2.di.auth.AuthViewModelModule;
import com.example.advancedagger2.ui.auth.AuthActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This Class {@linkplain ActivityBuilderModule} is responsible for for android injection
 * for the activity with in the application to avoid the seprate injection in each activity
 *
 * {@linkplain dagger.android.AndroidInjection#inject(Activity)}
 *
 * {@link com.example.advancedagger2.viewmodel.AuthViewModel} can be access from Auth Activity
 * only so it is the concept of sub-modules
 *
 */
@Module
public abstract class ActivityBuilderModule {

    /**
     * Automatically create sub-component
     *
     * @return
     */
    @ContributesAndroidInjector(
            modules = { AuthViewModelModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();
}
