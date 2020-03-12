package com.kaiser.recipeappjava;


import android.app.Application;
import android.content.Context;
import com.kaiser.recipeappjava.injection.component.AppComponent;
import com.kaiser.recipeappjava.injection.component.DaggerAppComponent;
import com.kaiser.recipeappjava.injection.module.AppModule;

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Context mContext) {
        AppApplication app = (AppApplication) mContext.getApplicationContext();
        return app.mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }
}
