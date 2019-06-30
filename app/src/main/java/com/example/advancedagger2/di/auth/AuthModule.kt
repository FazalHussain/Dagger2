package com.example.advancedagger2.di.auth

import com.example.advancedagger2.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class AuthModule {

    /**
     * Provides auth api
     *
     * @param retrofit The retrofit instance which can be access from app component
     */
    @Provides
    internal fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}