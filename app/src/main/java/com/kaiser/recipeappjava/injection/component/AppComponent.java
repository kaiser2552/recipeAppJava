package com.kaiser.recipeappjava.injection.component;

import android.app.Application;
import android.content.Context;

import com.kaiser.recipeappjava.AppApplication;
import com.kaiser.recipeappjava.base.activity.BaseActivity;
import com.kaiser.recipeappjava.base.activity.BaseMvpActivity;
import com.kaiser.recipeappjava.base.fragment.BaseMvpFragment;
import com.kaiser.recipeappjava.injection.context.ApplicationContext;
import com.kaiser.recipeappjava.injection.module.AppModule;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeActivity;
import com.kaiser.recipeappjava.ui.listrecipe.ListRecipeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    // assigns references in activities, services, or fragments to have access to singletons that define in ModuleType

    /*Activty*/
    void inject(BaseActivity activity);

    void inject(BaseMvpActivity activity);

    void inject(ListRecipeActivity activity);

    void inject(AppApplication mApplication);

    /*Fragment*/
    void inject(BaseMvpFragment fragment);

    void inject(ListRecipeFragment fragment);


    Application application();

    @ApplicationContext
    Context context();
}
