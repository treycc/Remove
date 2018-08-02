package com.kingja.zhongminremove.page.login;



import com.kingja.zhongminremove.injector.annotation.PerActivity;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.injector.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface LoginCompnent {
    void inject(LoginActivity activity);
}
