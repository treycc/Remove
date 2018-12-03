package com.jdp.hls.model.service;


import com.jdp.hls.model.entiy.AirPhotoBuilding;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.AreaResult;
import com.jdp.hls.model.entiy.BaiscPersonal;
import com.jdp.hls.model.entiy.BasicCompany;
import com.jdp.hls.model.entiy.BusinessQuery;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.ConfigCompany;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.model.entiy.GroupDetail;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.LineChartItem;
import com.jdp.hls.model.entiy.ModuleDetail;
import com.jdp.hls.model.entiy.NodeCompanyHouseEvaluate;
import com.jdp.hls.model.entiy.NodeCompanyMoneyEvaluate;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedCompanyLicense;
import com.jdp.hls.model.entiy.DeedCompanyOpenAccountCert;
import com.jdp.hls.model.entiy.DeedCompanyProperty;
import com.jdp.hls.model.entiy.DeedPersonalBank;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;
import com.jdp.hls.model.entiy.DeedPersonalLand;
import com.jdp.hls.model.entiy.DeedPersonalProperty;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.model.entiy.DetailPersonal;
import com.jdp.hls.model.entiy.Dict;
import com.jdp.hls.model.entiy.FamilyRelation;
import com.jdp.hls.model.entiy.HttpResult;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.LevyInfo;
import com.jdp.hls.model.entiy.Login;
import com.jdp.hls.model.entiy.NodeCompanyAge;
import com.jdp.hls.model.entiy.NodeCompanyEvaluate;
import com.jdp.hls.model.entiy.NodeCompanyMapping;
import com.jdp.hls.model.entiy.NodeCompanyMeasure;
import com.jdp.hls.model.entiy.NodeCompanyProtocol;
import com.jdp.hls.model.entiy.NodePersonalAge;
import com.jdp.hls.model.entiy.NodePersonalEvaluate;
import com.jdp.hls.model.entiy.NodePersonalMapping;
import com.jdp.hls.model.entiy.NodePersonalMeasure;
import com.jdp.hls.model.entiy.NodePersonalProtocol;
import com.jdp.hls.model.entiy.Notification;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.model.entiy.ProjectListInfo;
import com.jdp.hls.model.entiy.ProjectSuperviseDetail;
import com.jdp.hls.model.entiy.ProjectSuperviseInfo;
import com.jdp.hls.model.entiy.PublicityDetail;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.model.entiy.PublicityObject;
import com.jdp.hls.model.entiy.ReceivePerson;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.model.entiy.StatisticsDetail;
import com.jdp.hls.model.entiy.StatisticsProgressDetail;
import com.jdp.hls.model.entiy.StatisticsProgressInfo;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.model.entiy.TaskInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface ApiService {


    /*登录*/
    @FormUrlEncoded
    @POST("api/user/login")
    Observable<HttpResult<Login>> login(@Field("accountName") String accountName, @Field("password") String password,
                                        @Field("accountType ") int accountType);

    /*修改密码*/
    @FormUrlEncoded
    @POST("api/user/login")
    Observable<HttpResult<Object>> modifyPassword(@Field("oldPasswrod") String accountName, @Field("newPassword")
            String newPassword);

    /*获取花名册*/
    @GET("api/person/GetRosterList")
    Observable<HttpResult<List<Roster>>> getRosterList(@Query("projectId") String projectId, @Query("employeeId")
            int employeeId);

    /*根据类型获取花名册*/
    @GET("api/person/GetRosterListByType")
    Observable<HttpResult<List<Roster>>> getRosterListByType(@Query("projectId") String projectId, @Query("employeeId")
            int employeeId, @Query("isEnterprise") int isEnterprise);

    /*获取花名册详情页*/
    @GET("api/person/GetRosterDetail")
    Observable<HttpResult<RosterDetail>> getRosterDetail(@Query("houseId") String houseId, @Query("employeeId")
            int employeeId, @Query("isEnterprise") int isEnterprise);

    /*修改别名*/
    @FormUrlEncoded
    @POST("api/user/ModifyAlias")
    Observable<HttpResult<Object>> modifyAlias(@Field("employeeId") int employeeId, @Field("aliasName") String
            aliasName);

    /*修改手机号*/
    @FormUrlEncoded
    @POST("api/user/ModifyMobile")
    Observable<HttpResult<Object>> modifyMobile(@Field("employeeId") int employeeId, @Field("mobile") String mobile);

    /*获取用户项目*/
    @GET("api/project/GetUserProject")
    Observable<HttpResult<List<Project>>> getProjects(@Query("userId") int userId);

    /*添加花名册*/
    @POST("api/person/AddRoster")
    Observable<HttpResult<String>> addRoster(@Body RequestBody rosterBody);

    /*获取人员列表*/
    @GET("api/person/GetPersonList")
    Observable<HttpResult<List<Person>>> getPersons(@Query("projectId") String projectId);

    /*修改花名册*/
    @POST("api/person/ModifyRoster")
    Observable<HttpResult<Object>> modifyRoster(@Body RequestBody rosterBody);

    /*意见和建议*/
    @POST("api/suggest/add")
    Observable<HttpResult<Object>> suggest(@Body RequestBody rosterBody);

    /*退出登录*/
    @POST("api/user/logout")
    Observable<HttpResult<Object>> logout();

    /*修改密码*/
    @FormUrlEncoded
    @POST("api/user/changePassword")
    Observable<HttpResult<Object>> modifyPassword(@Field("employeeId") int employeeId, @Field("oldPassword") String
            oldPassword, @Field("newPassword") String newPassword);

    /*上传异常*/
    @FormUrlEncoded
    @POST("api/systemSevice/postLog")
    Observable<HttpResult<Object>> uploadError(@Field("iP") String iP, @Field("applicationType") String
            applicationType, @Field("oSversion") String oSversion, @Field("exceptionType") String exceptionType,
                                               @Field("exceptionMsg") String exceptionMsg, @Field("employeeId")
                                                       String employeeId);

    /*上传头像*/
    @Multipart
    @POST("api/user/postUserPhoto")
    Observable<HttpResult<String>> uploadHeadImg(@Part MultipartBody.Part headImg);

    /*获取任务数*/
    @GET("api/workflow/getTaskCount")
    Observable<HttpResult<LevyInfo>> getTask(@Query("projectId") String projectId, @Query("buildingType") int
            buildingType);

    /*获取任务列表*/
    @GET("api/workflow/GetTaskList")
    Observable<HttpResult<TaskInfo>> getTaskList(@Query("projectId") String projectId, @Query("buildingType") int
            buildingType, @Query("taskType") int taskType);

    /*======================航拍复查======================*/
    /*航拍复查-列表*/
    @GET("api/AirCheck/GetList")
    Observable<HttpResult<List<AirPhotoItem>>> getAirPhotoList(@Query("buildingType") String buildingType, @Query
            ("taskType") String taskType);

    /*可航拍房产列表-获取*/
    @GET("api/AirCheck/GetListForAirCheck")
    Observable<HttpResult<List<AirPhotoBuilding>>> getAirPhotoBuildings(@Query("buildingType") String buildingType);

    /*航拍复查-详情*/
    @GET("api/AirCheck/GetDetail")
    Observable<HttpResult<AirPhotoItem>> getAirPhotoDetail(@Query("id") String id, @Query("checkType") String
            checkType);

    /*航拍-发起*/
    @POST("api/AirCheck/Add")
    Observable<HttpResult<AirPhotoItem>> applyAirPhoto(@Body RequestBody rosterBody);

    /*航拍-修改*/
    @POST("api/AirCheck/Update")
    Observable<HttpResult<AirPhotoItem>> modifyAirPhotoDetail(@Body RequestBody rosterBody);


    /*======================公示======================*/
    /*获取公示列表*/
    @GET("api/Publicity/GetPublicityList")
    Observable<HttpResult<List<PublicityItem>>> getPublicityList(@Query("projectId") String projectId, @Query
            ("publicityType")
            int publicityType);

    /*获取公示详情*/
    @GET("api/Publicity/GetPublicityDetail")
    Observable<HttpResult<PublicityDetail>> getPublicityDetail(@Query("pubId") int pubId);

    /*申请公示*/
    @POST("api/Publicity/AddPublicity")
    Observable<HttpResult<PublicityItem>> applyPublicity(@Body RequestBody rosterBody);

    /*修改公示*/
    @POST("api/Publicity/UpdatePublicity")
    Observable<HttpResult<PublicityItem>> modifyPublicity(@Body RequestBody rosterBody);

    /*获取公示对象列表*/
    @GET("api/publicity/GetNeedPublicityBuildingList")
    Observable<HttpResult<List<PublicityObject>>> getPublicityObject(@Query("buildingType") int buildingType, @Query
            ("publicityType") int publicityType);

    /*获取一览表列表*/
    @GET("api/workflow/GetLevyList")
    Observable<HttpResult<List<Table>>> getTables(@Query("projectId") String projectId, @Query("buildingType") int
            buildingType, @Query("StatisId") String StatisId);

    /*获取字典表*/
    @GET("api/SystemSevice/GetAllConfigList")
    Observable<HttpResult<List<Dict>>> getDicts();

    /*个人基本信息*/
    @GET("api/Workflow/GetHouseTaskBasicInfo")
    Observable<HttpResult<BaiscPersonal>> getPersonalBasic(@Query("buildingId") String buildingId);

    /*企业基本信息*/
    @GET("api/workflow/GetEnterpriseTaskBasicInfo")
    Observable<HttpResult<BasicCompany>> getCompanyBasic(@Query("buildingId") String buildingId);

    /*个人信息详情*/
    @GET("api/workflow/GetHouseTaskDetail")
    Observable<HttpResult<DetailPersonal>> getPersonalDetail(@Query("buildingId") String buildingId);

    /*个人信息详情-修改*/
    @POST("api/workflow/SaveHouseTask")
    Observable<HttpResult<Object>> modifyPersonalDetail(@Body RequestBody rosterBody);

    /*企业-信息详情*/
    @GET("api/workflow/GetEnterpriseTaskDetail")
    Observable<HttpResult<DetailCompany>> getCompanyDetail(@Query("buildingId") String buildingId);

    /*企业信息详情-修改*/
    @POST("api/workflow/SaveEnterpriseTask")
    Observable<HttpResult<Object>> modifyCompanyDetail(@Body RequestBody rosterBody);

    /*======================个人证件======================*/
    /*个人产权证-获取*/
    @GET("api/cert/GetHousePropertyCert")
    Observable<HttpResult<DeedPersonalProperty>> getDeedPersonalProperty(@Query("houseId") String houseId);

    /*个人产权证-修改*/
    @POST("api/cert/UpdateHousePropertyCert")
    Observable<HttpResult<Object>> modifyDeedPersonalProperty(@Body RequestBody rosterBody);

    /*个人产权证-创建*/
    @POST("api/cert/AddHousePropertyCert")
    Observable<HttpResult<Object>> addDeedPersonalProperty(@Body RequestBody rosterBody);

    /*个人土地证-获取*/
    @GET("api/cert/GetHouseLandCert")
    Observable<HttpResult<DeedPersonalLand>> getDeedPersonalLand(@Query("houseId") String houseId);

    /*个人土地证-修改*/
    @POST("api/cert/UpdateHouseLandCert")
    Observable<HttpResult<Object>> modifyDeedPersonalLand(@Body RequestBody rosterBody);

    /*个人土地证-创建*/
    @POST("api/cert/AddHouseLandCert")
    Observable<HttpResult<Object>> addDeedPersonalLand(@Body RequestBody rosterBody);


    /*个人不动产证-获取*/
    @GET("api/cert/GetHouseEstateCert")
    Observable<HttpResult<DeedPersonalImmovable>> getDeedPersonalImmovable(@Query("houseId") String houseId);

    /*个人不动产证-修改*/
    @POST("api/cert/UpdateHouseEstateCert")
    Observable<HttpResult<Object>> modifyDeedPersonalImmovable(@Body RequestBody rosterBody);

    /*个人不动产证-创建*/
    @POST("api/cert/AddHouseEstateCert")
    Observable<HttpResult<Object>> addDeedPersonalImmovable(@Body RequestBody rosterBody);


    /*个人银行信息-获取*/
    @GET("api/cert/GetHouseOpenAccountCert")
    Observable<HttpResult<DeedPersonalBank>> getDeedPersonalBank(@Query("houseId") String houseId);

    /*个人银行信息-修改*/
    @POST("api/cert/UpdateHouseOpenAccountCert")
    Observable<HttpResult<Object>> modifyDeedPersonalBank(@Body RequestBody rosterBody);

    /*个人银行信息-创建*/
    @POST("api/cert/AddHouseOpenAccountCert")
    Observable<HttpResult<Object>> addDeedPersonalBank(@Body RequestBody rosterBody);


    /*======================企业证件======================*/
    /*企业产权证-获取*/
    @GET("api/cert/GetEnterprisePropertyCert")
    Observable<HttpResult<DeedCompanyProperty>> getDeedCompanyProperty(@Query("enterpriseId") String enterpriseId);

    /*企业产权证-修改*/
    @POST("api/cert/UpdateEnterprisePropertyCert")
    Observable<HttpResult<Object>> modifyDeedCompanyProperty(@Body RequestBody rosterBody);

    /*企业产权证-创建*/
    @POST("api/cert/AddEnterprisePropertyCert")
    Observable<HttpResult<Object>> addDeedCompanyProperty(@Body RequestBody rosterBody);

    /*企业土地证-获取*/
    @GET("api/cert/GetEnterpriseLandCert")
    Observable<HttpResult<DeedCompanyLand>> getDeedCompanyLand(@Query("enterpriseId") String enterpriseId);

    /*企业土地证-修改*/
    @POST("api/cert/UpdateEnterpriseLandCert")
    Observable<HttpResult<Object>> modifyDeedCompanyLand(@Body RequestBody rosterBody);

    /*企业土地证-创建*/
    @POST("api/cert/AddEnterpriseLandCert")
    Observable<HttpResult<Object>> addDeedCompanyLand(@Body RequestBody rosterBody);


    /*企业不动产证-获取*/
    @GET("api/cert/GetEnterpriseEstateCert")
    Observable<HttpResult<DeedCompanyImmovable>> getDeedCompanyImmovable(@Query("enterpriseId") String enterpriseId);

    /*企业不动产证-修改*/
    @POST("api/cert/UpdateEnterpriseEstateCert")
    Observable<HttpResult<Object>> modifyDeedCompanyImmovable(@Body RequestBody rosterBody);

    /*企业不动产证-创建*/
    @POST("api/cert/AddEnterpriseEstateCert")
    Observable<HttpResult<Object>> addDeedCompanyImmovable(@Body RequestBody rosterBody);

    /*企业营业执照-获取*/
    @GET("api/cert/GetEnterpriseLicense")
    Observable<HttpResult<DeedCompanyLicense>> getDeedCompanyLicense(@Query("enterpriseId") String enterpriseId);

    /*企业营业执照-修改*/
    @POST("api/cert/UpdateEnterpriseLicense")
    Observable<HttpResult<Object>> modifyDeedCompanyLicense(@Body RequestBody rosterBody);

    /*企业营业执照-创建*/
    @POST("api/cert/AddEnterpriseLicense")
    Observable<HttpResult<Object>> addDeedCompanyLicense(@Body RequestBody rosterBody);


    /*企业开户许可证-获取*/
    @GET("api/cert/GetEnterpriseOpenAccountCert")
    Observable<HttpResult<DeedCompanyOpenAccountCert>> getDeedCompanyOpenAccountCert(@Query("enterpriseId") String
                                                                                             enterpriseId);

    /*企业开户许可证-修改*/
    @POST("api/cert/UpdateEnterpriseOpenAccountCert")
    Observable<HttpResult<Object>> modifyDeedCompanyOpenAccountCert(@Body RequestBody rosterBody);

    /*企业开户许可证-创建*/
    @POST("api/cert/AddEnterpriseOpenAccountCert")
    Observable<HttpResult<Object>> addDeedCompanyOpenAccountCert(@Body RequestBody rosterBody);

    /*======================个人节点======================*/

    /*个人-入户丈量-获取*/
    @GET("api/Workflow/GetHouseMeasurement")
    Observable<HttpResult<NodePersonalMeasure>> getPersonalMeasure(@Query("houseId") String houseId);

    /*个人-入户丈量-修改*/
    @POST("api/workflow/UpdateHouseMeasurement")
    Observable<HttpResult<Object>> modifyPersonalMeasure(@Body RequestBody rosterBody);

    /*个人-测绘出图-获取*/
    @GET("api/workflow/GetHouseMapOut")
    Observable<HttpResult<NodePersonalMapping>> getPersonalMapping(@Query("houseId") String houseId);

    /*个人-测绘出图-修改*/
    @POST("api/workflow/UpdateHouseMapOut")
    Observable<HttpResult<Object>> modifyPersonalMapping(@Body RequestBody rosterBody);

    /*个人-年限审核-获取*/
    @GET("api/workflow/getHouseAppraise")
    Observable<HttpResult<NodePersonalAge>> getPersonalAge(@Query("houseId") String houseId);

    /*个人-年限审核-修改*/
    @POST("api/workflow/UpdateHouseAppraise")
    Observable<HttpResult<Object>> modifyPersonalAge(@Body RequestBody rosterBody);

    /*个人-入户评估-获取*/
    @GET("api/workflow/GetHouseEvaluation")
    Observable<HttpResult<NodePersonalEvaluate>> getPersonalEvaluate(@Query("houseId") String houseId);

    /*个人-入户评估-修改*/
    @POST("api/workflow/UpdateHouseEvaluation")
    Observable<HttpResult<Object>> modifyPersonalEvaluate(@Body RequestBody rosterBody);

    /*个人-协议生成-获取*/
    @GET("api/workflow/GetHouseProtocolCheck")
    Observable<HttpResult<NodePersonalProtocol>> getPersonalProtocol(@Query("houseId") String houseId);

    /*个人-协议生成-修改*/
    @POST("api/workflow/UpdateHouseProtocolCheck")
    Observable<HttpResult<Object>> modifyPersonalProtocol(@Body RequestBody rosterBody);

    /*======================企业节点======================*/

    /*企业-入户丈量-获取*/
    @GET("api/Workflow/GetEnterpriseMeasurement")
    Observable<HttpResult<NodeCompanyMeasure>> getCompanyMeasure(@Query("enterpriseId") String enterpriseId);

    /*企业-入户丈量-修改*/
    @POST("api/workflow/UpdateEnterpriseMeasurement")
    Observable<HttpResult<Object>> modifyCompanyMeasure(@Body RequestBody rosterBody);

    /*企业-测绘出图-获取*/
    @GET("api/workflow/GetEnterpriseMapOut")
    Observable<HttpResult<NodeCompanyMapping>> getCompanyMapping(@Query("enterpriseId") String enterpriseId);

    /*企业-测绘出图-修改*/
    @POST("api/workflow/UpdateEnterpriseMapOut")
    Observable<HttpResult<Object>> modifyCompanyMapping(@Body RequestBody rosterBody);

    /*企业-年限审核-获取*/
    @GET("api/workflow/getEnterpriseAppraise")
    Observable<HttpResult<NodeCompanyAge>> getCompanyAge(@Query("enterpriseId") String enterpriseId);

    /*企业-年限审核-修改*/
    @POST("api/workflow/UpdateEnterpriseAppraise")
    Observable<HttpResult<Object>> modifyCompanyAge(@Body RequestBody rosterBody);

    /*企业-入户评估-获取*/
    @GET("api/workflow/GetEnterpriseEvaluation")
    Observable<HttpResult<NodeCompanyEvaluate>> getCompanyEvaluate(@Query("enterpriseId") String enterpriseId);

    /*企业-入户评估-修改*/
    @POST("api/workflow/UpdateEnterpriseEvaluation")
    Observable<HttpResult<Object>> modifyCompanyEvaluate(@Body RequestBody rosterBody);

    /*企业-协议生成-获取*/
    @GET("api/workflow/GetEnterpriseProtocolCheck")
    Observable<HttpResult<NodeCompanyProtocol>> getCompanyProtocol(@Query("enterpriseId") String enterpriseId);

    /*企业-协议生成-修改*/
    @POST("api/workflow/UpdateEnterpriseProtocolCheck")
    Observable<HttpResult<Object>> modifyCompanyProtocol(@Body RequestBody rosterBody);

    /*企业-资产评估-获取*/
    @GET("api/workflow/GetEnterpriseEvaluationAsset")
    Observable<HttpResult<NodeCompanyMoneyEvaluate>> getCompanyMoneyevaluate(@Query("enterpriseId") String
                                                                                     enterpriseId);

    /*企业-资产评估-修改*/
    @POST("api/workflow/UpdateEnterpriseEvaluationAsset")
    Observable<HttpResult<Object>> modifyCompanyMoneyevaluate(@Body RequestBody rosterBody);

    /*企业-房地产评估-获取*/
    @GET("api/workflow/GetEnterpriseEvaluationEstate")
    Observable<HttpResult<NodeCompanyHouseEvaluate>> getCompanyHouseEvaluate(@Query("enterpriseId") String
                                                                                     enterpriseId);

    /*企业-房地产评估-修改*/
    @POST("api/workflow/UpdateEnterpriseEvaluationEstate")
    Observable<HttpResult<Object>> modifyCompanyHouseEvaluate(@Body RequestBody rosterBody);

    /*======================协议生成-其它面积======================*/

    /*协议生成-其它面积-详情*/
    @GET("api/workflow/GetProtocolCheckItems")
    Observable<HttpResult<OtherArea>> getOtherAreaDetail(@Query("id") String id, @Query("buildingType")
            String buildingType);

    /*协议生成-其它面积-列表*/
    @GET("api/workflow/GetProtocolCheckItemsList")
    Observable<HttpResult<List<OtherArea>>> getOtherAreaList(@Query("pCId") String pCId, @Query("buildingType")
            String buildingType);

    /*协议生成-其它面积-新增*/
    @POST("api/workflow/AddProtocolCheckItems")
    Observable<HttpResult<Integer>> addOtherArea(@Body RequestBody requestBody);

    /*协议生成-其它面积-修改*/
    @POST("api/workflow/UpdateProtocolCheckItems")
    Observable<HttpResult<Object>> modifyOtherArea(@Body RequestBody requestBody);

    /*协议生成-其它面积-删除*/
    @POST("api/workflow/DeleteProtocolCheckItems")
    Observable<HttpResult<Object>> deleteOtherArea(@Body RequestBody requestBody);

    /*图片修改*/
    @POST("api/files/UpLoadFiles")
    Observable<HttpResult<List<ImgInfo>>> modifyPhotos(@Body RequestBody requestBody);


    /*家庭关系*/
    @GET("api/person/GetFamilyPersonList")
    Observable<HttpResult<FamilyRelation>> getFamilyRelation(@Query("houseId") String houseId);

    /*家庭关系-修改*/
    @POST("api/person/UpdateFamilyInfo")
    Observable<HttpResult<Object>> modifyFamilyRelation(@Body RequestBody requestBody);

    /*家庭成员-修改*/
    @POST("api/person/SavePersonForApp")
    Observable<HttpResult<String>> saveFamilyRemember(@Body RequestBody requestBody);

    /*家庭成员-删除*/
    @POST("api/person/DeletePerson")
    Observable<HttpResult<Object>> deleteFamilyRemember(@Body RequestBody requestBody);


    /*内装饰明细-详情*/
    @GET("api/workflow/GetEvaluationItems")
    Observable<HttpResult<FamilyRelation>> getDecorationDetail(@Query("id") String id, @Query("buildingType")
            String buildingType);

    /*内装饰明细-列表*/
    @GET("api/workflow/GetEvaluationItemsList")
    Observable<HttpResult<List<DecorationItem>>> getDecorationList(@Query("evalId") String evalId, @Query
            ("buildingType")
            String buildingType, @Query("itemType") String itemType);

    /*内装饰明细-增加*/
    @POST("api/workflow/AddEvaluationItems")
    Observable<HttpResult<DecorationItem>> addDecoration(@Body RequestBody requestBody);

    /*内装饰明细-修改*/
    @POST("api/workflow/UpdateEvaluationItems")
    Observable<HttpResult<DecorationItem>> modifyDecoration(@Body RequestBody requestBody);

    /*内装饰明细-删除*/
    @POST("api/workflow/DeleteEvaluationItems")
    Observable<HttpResult<Object>> deleteDecoration(@Body RequestBody requestBody);

    /*饼图*/
    @GET("api/Project/Get8Statis")
    Observable<HttpResult<StatisticsDetail>> getStatistics(@Query("ProjectId") String ProjectId, @Query("StatisType")
            String StatisType, @Query("BuildingType") String BuildingType);

    /*获取下个节点接收人*/
    @FormUrlEncoded
    @POST("api/workflow/PostReceiveEmployee")
    Observable<HttpResult<ReceivePerson>> getNextNodePersonName(@Field("buildingId") String buildingId, @Field
            ("buildingType") String buildingType);

    /*发送*/
    @POST("api/workflow/send")
    Observable<HttpResult<Object>> sendNode(@Body RequestBody requestBody);

    /*退回*/
    @POST("api/workflow/FlowBack")
    Observable<HttpResult<Object>> backNode(@Body RequestBody requestBody);

    /*作废*/
    @POST("api/workflow/Banned")
    Observable<HttpResult<Object>> deleteNode(@Body RequestBody requestBody);

    /*复查*/
    @POST("api/workflow/FlowReview")
    Observable<HttpResult<Object>> reviewNode(@Body RequestBody requestBody);

    /*恢复*/
    @POST("api/workflow/FlowRecover")
    Observable<HttpResult<Object>> recoverNode(@Body RequestBody requestBody);

    /*复查-获取接受节点列表*/
    @FormUrlEncoded
    @POST("api/workflow/PostGroupMemberForReview")
    Observable<HttpResult<List<ReceivePerson>>> getReviewReceiverList(@Field("buildingId") String buildingId, @Field
            ("buildingType") String buildingType);

    /*恢复-获取接受节点列表*/
    @FormUrlEncoded
    @POST("api/workflow/PostGroupMemberForRecover")
    Observable<HttpResult<List<ReceivePerson>>> getRecoverReceiverList(@Field("buildingId") String buildingId, @Field
            ("buildingType") String buildingType);

    /*获取经办人*/
    @FormUrlEncoded
    @POST("api/workflow/PostOperatorName")
    Observable<HttpResult<ReceivePerson>> getOperatePerson(@Field("GroupIDs") String groupIDs);

    /*航拍复查-完结*/
    @POST("api/AirCheck/CloseFinished")
    Observable<HttpResult<Object>> finishAirPhoto(@Body RequestBody requestBody);

    /*航拍复查-复查*/
    @POST("api/AirCheck/ReAdd")
    Observable<HttpResult<AirPhotoItem>> reviewAirPhoto(@Body RequestBody requestBody);

    /*航拍复查-年限上传图片*/
    @POST("api/AirCheck/UpdateByAppraise")
    Observable<HttpResult<Object>> updateAgePhotos(@Body RequestBody requestBody);


    /*获取系统里列表*/
    @GET("api/SystemSevice/GetSystemModuleList")
    Observable<HttpResult<ModuleDetail>> getModuleDetail(@Query("routeId") String routeId);

    /*员工-列表*/
    @GET("api/user/GetUserPageList")
    Observable<HttpResult<EmployeeDetail>> getEmployeeList(@Query("pageIndex") int pageIndex, @Query("pageSize") int
            pageSize);

    /*员工-增加*/
    @POST("api/user/AddNewUser")
    Observable<HttpResult<Employee>> addEmployee(@Body RequestBody requestBody);

    /*员工-修改*/
    @POST("api/user/UpdateNewUser")
    Observable<HttpResult<Employee>> modifyEmployee(@Body RequestBody requestBody);

    /*员工-详情*/
    @GET("api/user/GetUserDetail")
    Observable<HttpResult<Employee>> getEmployeeDetail(@Query("EmployeeId") String employeeId);

    /*通知-列表*/
    @GET("api/SystemSevice/GetMessageCount")
    Observable<HttpResult<List<Notification>>> getNotificationList();

    /*=========================================项目=========================================*/
    /*项目-列表*/
    @GET("api/project/GetProjectList_Admin")
    Observable<HttpResult<ProjectListInfo>> getProjectList();

    /*项目-查询列表*/
    @GET("api/project/QueryProjectList")
    Observable<HttpResult<List<ProjectItem>>> getQueryProjectList();

    /*业务-查询列表*/
    @GET("api/Building/GetList_Admin")
    Observable<HttpResult<List<BusinessQuery>>> getQueryList(@Query("projectId") String projectId, @Query
            ("buildingType") int buildingType);

    /*项目-详情*/
    @GET("api/project/GetProjectBaseInfo")
    Observable<HttpResult<ProjectItem>> getProjectDetail(@Query("projectId") String projectId);

    /*项目-创建*/
    @POST("api/project/AddUserProject")
    Observable<HttpResult<ProjectItem>> addProject(@Body RequestBody requestBody);

    /*项目-修改*/
    @POST("api/project/UpdateUserProject")
    Observable<HttpResult<ProjectItem>> modifyProject(@Body RequestBody requestBody);

    /*项目-保存*/
    @POST("api/project/SaveUserProject")
    Observable<HttpResult<ProjectItem>> saveProject(@Body RequestBody requestBody);

    /*项目配置-获取*/
    @GET("api/project/GetProjectCompanyList")
    Observable<HttpResult<List<ConfigCompany>>> getProjectConfig(@Query("projectId") String projectId);

    /*项目配置-修改*/
    @POST("api/project/SetProjectCompany")
    Observable<HttpResult<Object>> modifyProjectConfig(@Body RequestBody requestBody);

    /*公司-列表*/
    @GET("api/company/GetList")
    Observable<HttpResult<List<Company>>> getCompanyList(@Query("CompanyTypeId") int companyTypeId);

    /*省市区-列表*/
    @FormUrlEncoded
    @POST("api/SystemSevice/QueryAllRegionList")
    Observable<HttpResult<AreaResult>> getAreaData(@Field("updateTime") String updateTime);

    /*小组-列表*/
    @GET("api/Group/GetGroupList")
    Observable<HttpResult<List<Group>>> getGroupList(@Query("projectId") String projectId);

    /*小组-详情*/
    @GET("api/Group/GetGroupMemberList")
    Observable<HttpResult<GroupDetail>> getGroupDetail(@Query("projectId") String projectId, @Query("groupId") int
            groupId);

    /*根据公司查询员工*/
    @POST("api/user/QueryUserPageList")
    Observable<HttpResult<EmployeeDetail>> getEmployeeByCompany(@Body RequestBody requestBody);

    /*小组-添加/修改*/
    @POST("api/Group/SaveGroupMemberV2")
    Observable<HttpResult<Group>> saveGroupInfo(@Body RequestBody requestBody);

    /*项目-列表(监管系统)*/
    @GET("api/supervise/GetProjectPageList")
    Observable<HttpResult<ProjectSuperviseInfo>> getProjectSuperviseList(@Query("PageSize") int pageSize, @Query
            ("PageIndex") int pageIndex, @Query("OrderBy") int orderBy);

    /*项目-列表详情(监管系统)*/
    @GET("api/supervise/GetProjectDetail")
    Observable<HttpResult<ProjectSuperviseDetail>> getProjectSuperviseDetail(@Query("ProjectId") String projectId);

    /*汇总统计(监管系统)*/
    @GET("api/supervise/GetCollectViewBuilding")
    Observable<HttpResult<List<KeyValue>>> getStatisticsTotal(@Query("ProjectId") String projectId,
                                                              @Query("BuildingType") int buildingType);

    /*进度统计-主页(监管系统)*/
    @GET("api/supervise/GetProgressStatistics")
    Observable<HttpResult<StatisticsProgressInfo>> getStatisticsProgress(@Query("ProjectId") String projectId,
                                                                         @Query("BuildingType") int buildingType);

    /*进度统计-详情(监管系统)*/
    @GET("api/supervise/GetProgressDetail")
    Observable<HttpResult<StatisticsProgressDetail>> getStatisticsProgressDetail(@Query("ItemType") int itemType);

    /*进度统计-折线图(监管系统)*/
    @GET("api/supervise/GetStatisticChart")
    Observable<HttpResult<List<LineChartItem>>> getLineChart(@Query("ItemType") int itemType, @Query
            ("DateType") int dateType, @Query("BeginDate") String beginDate, @Query("EndDate") String endDate);

    /*一览表(监管系统)*/
    @GET("api/supervise/GetBuildingList")
    Observable<HttpResult<List<Table>>> getTableList(@Query("ProjectId") String projectId, @Query
            ("BuildingType") int buildingType);


}
