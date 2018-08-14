package com.jdp.hls.base;


import com.jdp.hls.injector.annotation.PerActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.injector.module.ActivityModule;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.page.map.MapFragment;
import com.jdp.hls.page.modify.ModifyActivity;
import com.jdp.hls.page.modifyPassword.ModifyPasswordActivity;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.page.suggest.SuggestActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface BaseCompnent {
    void inject(LoginActivity activity);
    void inject(ModifyPasswordActivity activity);
    void inject(SuggestActivity activity);
    void inject(MapFragment mapFragment);
    void inject(ProjectListActivity activity);
    void inject(ModifyActivity activity);
    void inject(RosterDetailActivity activity);
}
