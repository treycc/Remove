package com.jdp.hls.base;


import com.jdp.hls.page.businesslist.BusinessListActivity;
import com.jdp.hls.page.businesslist.BusinessListFragment;
import com.jdp.hls.page.crash.CrashActivity;
import com.jdp.hls.page.levy.LevyActivity;
import com.jdp.hls.page.map.RosterActivity;
import com.jdp.hls.page.mine.MineFragment;
import com.jdp.hls.page.setting.SettingActivity;
import com.jdp.hls.injector.annotation.PerActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.injector.module.ActivityModule;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.page.map.MapFragment;
import com.jdp.hls.page.modify.ModifyAndUploadActivity;
import com.jdp.hls.page.modifyPassword.ModifyPasswordActivity;
import com.jdp.hls.page.personSearch.PersonSearchActivity;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.page.rosterlist.RosterListFragment;
import com.jdp.hls.page.suggest.SuggestActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface BaseCompnent {
    void inject(LoginActivity target);
    void inject(ModifyPasswordActivity target);
    void inject(SuggestActivity target);
    void inject(MapFragment target);
    void inject(ProjectListActivity target);
    void inject(ModifyAndUploadActivity target);
    void inject(RosterDetailActivity target);
    void inject(RosterAddActivity target);
    void inject(PersonSearchActivity target);
    void inject(RosterListFragment target);
    void inject(SettingActivity target);
    void inject(CrashActivity target);
    void inject(MineFragment target);
    void inject(RosterActivity target);
    void inject(BusinessListActivity target);
    void inject(BusinessListFragment target);
    void inject(LevyActivity target);
}
