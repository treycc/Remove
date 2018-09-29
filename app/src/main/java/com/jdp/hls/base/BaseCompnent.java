package com.jdp.hls.base;


import com.jdp.hls.page.airphoto.list.AirPhotoListFragment;
import com.jdp.hls.page.airphoto.person.AirPhotoPersonActivity;
import com.jdp.hls.page.business.basic.company.BasicCompanyActivity;
import com.jdp.hls.page.business.basic.personla.BasicPersonalActivity;
import com.jdp.hls.page.business.detail.company.DetailCompanyActivity;
import com.jdp.hls.page.business.detail.personal.DetailPersonalActivity;
import com.jdp.hls.page.business.list.BusinessListActivity;
import com.jdp.hls.page.business.list.BusinessListFragment;
import com.jdp.hls.page.crash.CrashActivity;
import com.jdp.hls.page.deed.company.immovable.DeedCompanyImmovableActivity;
import com.jdp.hls.page.deed.company.land.DeedCompanyLandActivity;
import com.jdp.hls.page.deed.company.license.DeedCompanyLicenseActivity;
import com.jdp.hls.page.deed.company.property.DeedCompanyPropertyActivity;
import com.jdp.hls.page.deed.personal.immovable.DeedPersonalImmovableActivity;
import com.jdp.hls.page.deed.personal.land.DeedPersonalLandActivity;
import com.jdp.hls.page.deed.personal.property.DeedPersonalPropertyActivity;
import com.jdp.hls.page.levy.LevyActivity;
import com.jdp.hls.page.map.RosterActivity;
import com.jdp.hls.page.mine.MineFragment;
import com.jdp.hls.page.node.age.personal.NodePersonalAgeActivity;
import com.jdp.hls.page.node.evaluate.personal.NodePersonalEvaluateActivity;
import com.jdp.hls.page.node.mapping.personal.NodePersonalMappingActivity;
import com.jdp.hls.page.node.measure.personal.NodePersonalMeasureActivity;
import com.jdp.hls.page.publicity.apply.PublicityApplyActivity;
import com.jdp.hls.page.publicity.detail.PublicityDetailActivity;
import com.jdp.hls.page.publicity.list.PublicityListFragment;
import com.jdp.hls.page.publicity.object.PublicityObjectActivity;
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
import com.jdp.hls.page.table.list.TableListActivity;
import com.jdp.hls.page.table.list.TableListFragment;
import com.jdp.hls.service.initialize.InitializeService;

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

    void inject(AirPhotoListFragment target);

    void inject(AirPhotoPersonActivity target);

    void inject(PublicityListFragment target);

    void inject(TableListActivity target);

    void inject(TableListFragment target);

    void inject(InitializeService target);

    void inject(BasicPersonalActivity target);

    void inject(BasicCompanyActivity target);

    void inject(DetailPersonalActivity target);

    void inject(DeedPersonalPropertyActivity target);

    void inject(DeedPersonalLandActivity target);

    void inject(DeedPersonalImmovableActivity target);

    void inject(DeedCompanyPropertyActivity target);

    void inject(DeedCompanyLandActivity target);

    void inject(DeedCompanyImmovableActivity target);

    void inject(DetailCompanyActivity target);

    void inject(DeedCompanyLicenseActivity target);

    void inject(NodePersonalMeasureActivity target);

    void inject(NodePersonalAgeActivity target);

    void inject(NodePersonalMappingActivity target);

    void inject(NodePersonalEvaluateActivity target);

    void inject(PublicityDetailActivity target);

    void inject(PublicityApplyActivity target);

    void inject(PublicityObjectActivity target);
}
