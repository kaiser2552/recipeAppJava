package com.kaiser.recipeappjava.injection.module;

import android.app.Application;
import android.content.Context;
import com.kaiser.recipeappjava.injection.context.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }
}
