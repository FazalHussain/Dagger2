package com.example.advancedagger2.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.advancedagger2.BaseApplication
import com.example.advancedagger2.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import java.util.*
import javax.inject.Singleton

/**
 * Component is a graph. We build a component. Component will provide injected instances by using modules.
 * Extends appcomponent with [AndroidInjector] to avoid old way of injection application
 *
 * <code>
 *     fun inject(application: BaseApplication)
 * </code>
 *
 * AppComponent is act as a server whereas, [BaseApplication] act as a client.
 * Dagger interaction is like client-server interaction
 *
 * Anotated with [Singleton] Scope to tell dagger to keep it in the memory while application exists
 * and destroy it when application destroy
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    AppModule::class,
    ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {


    /**
     * Session manager can be access any where in the application
     */
    fun sessionManager() : SessionManager

    @Component.Builder
    interface Builder {

        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

}