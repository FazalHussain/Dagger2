# Dagger2
Implementation of dagger 2 with MVVM architecture, Retrofit, RxJava

Dagger 2.10 released with android support module and android compiler. I think this was a huge change for us and all android developers should switch to new dagger android injection as soon as possible.


![alt text](https://github.com/FazalHussain/Dagger2/blob/master/screenshot/Screen%20Shot%202019-06-30%20at%2011.56.15%20AM.png)

In the old dagger activity know about the dependency injection initialize the dependency let's have a look

        ((MyApplication)getApplication())
                .getAppComponent()
                .plus(new LobbyActivityModule())
                .inject(this);
    
The code above makes it apparent that the activity knows quite a bit about how it’s initialized with the dependencies. This violates the core principle of dependency injection known as Inversion of Control: The client delegates the responsibility of providing its dependencies to external code (the injector). The client code does not need to know about the injecting code.

Dagger 2.10+ and its dagger.android aim to address the issue above in addition to simplifying dependency injection in Android in general.
