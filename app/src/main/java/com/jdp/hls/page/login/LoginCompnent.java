package com.jdp.hls.page.login;


import com.jdp.hls.injector.annotation.PerActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.injector.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface LoginCompnent {
    void inject(LoginActivity activity);
}
