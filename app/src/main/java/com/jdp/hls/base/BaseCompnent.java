package com.jdp.hls.base;


import com.jdp.hls.activity.PhotoPreviewActivity;
import com.jdp.hls.page.admin.contrast.ProjectContrastDetailActivity;
import com.jdp.hls.page.admin.employee.add.projectlist.SuperviseProjectListActivity;
import com.jdp.hls.page.admin.employee.areasupervise.add.AreaSuperviseAddActivity;
import com.jdp.hls.page.admin.group.detail.GroupDetailActivity;
import com.jdp.hls.page.admin.group.list.GroupListActivity;
import com.jdp.hls.page.admin.group.member.MemberSelectActivity;
import com.jdp.hls.page.admin.manager.ManagerListActivity;
import com.jdp.hls.page.admin.project.company.CompanyListActivity;
import com.jdp.hls.page.admin.project.config.ProjectConfigActivity;
import com.jdp.hls.page.admin.project.detail.ProjectDetailActivity;
import com.jdp.hls.page.admin.project.detail.payscheme.PaySchemeActivity;
import com.jdp.hls.page.admin.project.list.ProjectListAdminActivity;
import com.jdp.hls.page.admin.query.ProjectSelectActivity;
import com.jdp.hls.page.admin.query.list.QueryListActivity;
import com.jdp.hls.page.admin.query.list.QueryListFragment;
import com.jdp.hls.page.airphoto.add.AirPhotoApplyActivity;
import com.jdp.hls.page.airphoto.building.AirPhotoBuildingActivity;
import com.jdp.hls.page.airphoto.detail.AirPhotoDetailActivity;
import com.jdp.hls.page.airphoto.list.AirPhotoListFragment;
import com.jdp.hls.page.airphoto.unrecordbuilding.UnrecordBuildingListActivity;
import com.jdp.hls.page.business.basic.company.BasicCompanyActivity;
import com.jdp.hls.page.business.basic.personla.BasicPersonalActivity;
import com.jdp.hls.page.business.detail.company.DetailCompanyActivity;
import com.jdp.hls.page.business.detail.personal.DetailPersonalActivity;
import com.jdp.hls.page.business.detail.personal.branklist.BankListActivity;
import com.jdp.hls.page.business.list.BusinessListActivity;
import com.jdp.hls.page.business.list.BusinessListFragment;
import com.jdp.hls.page.crash.CrashActivity;
import com.jdp.hls.page.deed.company.bank.DeedCompanyBankActivity;
import com.jdp.hls.page.deed.company.immovable.DeedCompanyImmovableActivity;
import com.jdp.hls.page.deed.company.land.DeedCompanyLandActivity;
import com.jdp.hls.page.deed.company.license.DeedCompanyBusinessActivity;
import com.jdp.hls.page.deed.company.property.DeedCompanyPropertyActivity;
import com.jdp.hls.page.business.detail.personal.bankdetail.DeedPersonalBankActivity;
import com.jdp.hls.page.deed.personal.immovable.DeedPersonalImmovableActivity;
import com.jdp.hls.page.deed.personal.land.DeedPersonalLandActivity;
import com.jdp.hls.page.deed.personal.property.DeedPersonalPropertyActivity;
import com.jdp.hls.page.admin.employee.add.EmployeeAddActivity;
import com.jdp.hls.page.admin.employee.detail.EmployeeDetailActivity;
import com.jdp.hls.page.admin.employee.list.EmployeeListActivity;
import com.jdp.hls.page.familyrelation.detail.FamilyMememberDetailActivity;
import com.jdp.hls.page.familyrelation.list.FamilyRelationActivity;
import com.jdp.hls.page.geography.GeographyActivity;
import com.jdp.hls.page.innerdecoration.detail.DecorationDetailActivity;
import com.jdp.hls.page.innerdecoration.list.DecorationListActivity;
import com.jdp.hls.page.levy.LevyActivity;
import com.jdp.hls.page.map.RosterActivity;
import com.jdp.hls.page.admin.message.notification.NotificationActivity;
import com.jdp.hls.page.mine.MineFragment;
import com.jdp.hls.page.module.HomeFragment;
import com.jdp.hls.page.node.age.company.NodeCompanyAgeActivity;
import com.jdp.hls.page.node.age.personal.NodePersonalAgeActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.banklist.ReceiveAccountListActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.detail.PayDetailActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.list.PayListActivity;
import com.jdp.hls.page.node.evaluate.company.NodeCompanyEvaluateActivity;
import com.jdp.hls.page.node.evaluate.company.houseevaluate.NodeCompanyHouseEvaluateActivity;
import com.jdp.hls.page.node.evaluate.company.moneyevaluate.NodeCompanyMoneyEvaluateActivity;
import com.jdp.hls.page.node.evaluate.personal.NodePersonalEvaluateActivity;
import com.jdp.hls.page.node.mapping.company.NodeCompanyMappingActivity;
import com.jdp.hls.page.node.mapping.personal.NodePersonalMappingActivity;
import com.jdp.hls.page.node.measure.company.NodeCompanyMeasureActivity;
import com.jdp.hls.page.node.measure.personal.NodePersonalMeasureActivity;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolActivity;
import com.jdp.hls.page.node.protocol.personal.NodePersonalProtocolActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.NodePersonalProtocolNewActivity;
import com.jdp.hls.page.operate.back.BackDialog;
import com.jdp.hls.page.operate.delete.DeleteDialog;
import com.jdp.hls.page.operate.recover.RecoverDialog;
import com.jdp.hls.page.operate.reminder.ReminderDialog;
import com.jdp.hls.page.operate.review.ReviewDialog;
import com.jdp.hls.page.operate.send.SendDialog;
import com.jdp.hls.page.otherarea.add.OtherAreaAddActivity;
import com.jdp.hls.page.otherarea.detail.OtherAreaDetailActivity;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.page.publicity.PublicityActivity;
import com.jdp.hls.page.publicity.apply.PublicityApplyActivity;
import com.jdp.hls.page.publicity.detail.PublicityDetailActivity;
import com.jdp.hls.page.publicity.list.PublicityListFragment;
import com.jdp.hls.page.publicity.object.PublicityObjectActivity;
import com.jdp.hls.page.rosterdetail.contacts.detail.ContactsDetailActivity;
import com.jdp.hls.page.rosterdetail.contacts.list.ContactsListActivity;
import com.jdp.hls.page.rosterdetail.detail.company.RosterCompanyDetailActivity;
import com.jdp.hls.page.rosterdetail.detail.personal.RosterPersonalDetailActivity;
import com.jdp.hls.page.setting.SettingActivity;
import com.jdp.hls.injector.annotation.PerActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.injector.module.ActivityModule;
import com.jdp.hls.page.login.LoginActivity;
import com.jdp.hls.page.map.MapFragment;
import com.jdp.hls.page.modify.ModifyAndUploadActivity;
import com.jdp.hls.page.modifyPassword.ModifyPasswordActivity;
import com.jdp.hls.page.personsearch.PersonSearchActivity;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.page.rosterlist.RosterListFragment;
import com.jdp.hls.page.statistics.StatisticsActivity;
import com.jdp.hls.page.suggest.SuggestActivity;
import com.jdp.hls.page.supervise.project.contrast.ProjectContrastActivity;
import com.jdp.hls.page.supervise.project.detail.ProjectDetailSuperviseActivity;
import com.jdp.hls.page.supervise.project.list.ProjectListSuperviseActivity;
import com.jdp.hls.page.supervise.statistics.progress.detail.head.StatisticsProgressDetailActivity;
import com.jdp.hls.page.supervise.statistics.progress.detail.linechart.StatisticsProgressLineFragment;
import com.jdp.hls.page.supervise.statistics.progress.progress.StatisticsProgressInfoFragment;
import com.jdp.hls.page.supervise.statistics.progress.report.buildinglist.ReportBuildingListActivity;
import com.jdp.hls.page.supervise.statistics.progress.report.daylist.ReportDayListActivity;
import com.jdp.hls.page.supervise.statistics.progress.report.hourlist.ReportHourListActivity;
import com.jdp.hls.page.supervise.statistics.table.TableListSuperviseActivity;
import com.jdp.hls.page.supervise.statistics.total.StatisticsTotalFragment;
import com.jdp.hls.page.supervise.statistics.total.pay.detaillist.StatisticsPayDetailListActivity;
import com.jdp.hls.page.supervise.statistics.total.pay.list.SupervisePayOwnerListActivity;
import com.jdp.hls.page.supervise.statistics.total.taotype.StatisticsTaotypeListActivity;
import com.jdp.hls.page.supervise.statistics.total.taotype.person.TaoTypePersonListActivity;
import com.jdp.hls.page.table.list.TableListActivity;
import com.jdp.hls.page.table.list.TableListFragment;
import com.jdp.hls.service.initialize.InitializeService;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface BaseCompnent {
    void inject(GeographyActivity target);

    void inject(RosterCompanyDetailActivity target);

    void inject(ContactsDetailActivity target);

    void inject(ContactsListActivity target);

    void inject(RosterPersonalDetailActivity target);

    void inject(AreaSuperviseAddActivity target);

    void inject(ReceiveAccountListActivity target);

    void inject(PaySchemeActivity target);

    void inject(ReminderDialog target);

    void inject(BankListActivity target);

    void inject(StatisticsPayDetailListActivity target);

    void inject(TaoTypePersonListActivity target);

    void inject(StatisticsTaotypeListActivity target);

    void inject(SupervisePayOwnerListActivity target);

    void inject(PayDetailActivity target);

    void inject(SuperviseProjectListActivity target);

    void inject(PayListActivity target);

    void inject(NodePersonalProtocolNewActivity target);

    void inject(ProjectContrastDetailActivity target);

    void inject(ProjectContrastActivity target);

    void inject(TableListSuperviseActivity target);

    void inject(ReportHourListActivity target);

    void inject(ReportBuildingListActivity target);

    void inject(StatisticsProgressLineFragment target);

    void inject(StatisticsTotalFragment target);

    void inject(StatisticsProgressInfoFragment target);

    void inject(ReportDayListActivity target);

    void inject(StatisticsProgressDetailActivity target);

    void inject(ProjectDetailSuperviseActivity target);

    void inject(ProjectListSuperviseActivity target);

    void inject(GroupListActivity target);

    void inject(MemberSelectActivity target);

    void inject(GroupDetailActivity target);

    void inject(QueryListFragment target);

    void inject(QueryListActivity target);

    void inject(ProjectSelectActivity target);

    void inject(CompanyListActivity target);

    void inject(ProjectConfigActivity target);

    void inject(ManagerListActivity target);

    void inject(ProjectDetailActivity target);

    void inject(ProjectListAdminActivity target);

    void inject(NotificationActivity target);

    void inject(EmployeeAddActivity target);

    void inject(EmployeeDetailActivity target);

    void inject(HomeFragment target);

    void inject(EmployeeListActivity target);

    void inject(PhotoPreviewActivity target);

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

    void inject(AirPhotoBuildingActivity target);

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

    void inject(DeedCompanyBusinessActivity target);

    void inject(NodePersonalMeasureActivity target);

    void inject(NodePersonalAgeActivity target);

    void inject(NodePersonalMappingActivity target);

    void inject(NodePersonalEvaluateActivity target);

    void inject(PublicityDetailActivity target);

    void inject(PublicityApplyActivity target);

    void inject(PublicityObjectActivity target);

    void inject(NodeCompanyMeasureActivity target);

    void inject(NodeCompanyMappingActivity target);

    void inject(NodeCompanyAgeActivity target);

    void inject(NodeCompanyEvaluateActivity target);

    void inject(NodeCompanyProtocolActivity target);

    void inject(NodePersonalProtocolActivity target);

    void inject(OtherAreaListActivity target);

    void inject(OtherAreaAddActivity target);

    void inject(OtherAreaDetailActivity target);

    void inject(AirPhotoDetailActivity target);

    void inject(AirPhotoApplyActivity target);

    void inject(UnrecordBuildingListActivity target);

    void inject(FamilyRelationActivity target);

    void inject(FamilyMememberDetailActivity target);

    void inject(DecorationListActivity target);

    void inject(DecorationDetailActivity target);

    void inject(StatisticsActivity target);

    void inject(SendDialog target);

    void inject(DeleteDialog target);

    void inject(ReviewDialog target);

    void inject(BackDialog target);

    void inject(RecoverDialog target);

    void inject(PublicityActivity target);

    void inject(DeedCompanyBankActivity target);

    void inject(DeedPersonalBankActivity target);

    void inject(NodeCompanyMoneyEvaluateActivity target);

    void inject(NodeCompanyHouseEvaluateActivity target);
}
