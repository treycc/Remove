package com.jdp.hls.model.service;


import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.AirPhotoPerson;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.BaiscCompany;
import com.jdp.hls.model.entiy.BaiscPersonal;
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedCompanyLicense;
import com.jdp.hls.model.entiy.DeedCompanyProperty;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;
import com.jdp.hls.model.entiy.DeedPersonalLand;
import com.jdp.hls.model.entiy.DeedPersonalProperty;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.model.entiy.DetailPersonal;
import com.jdp.hls.model.entiy.Dict;
import com.jdp.hls.model.entiy.HttpResult;
import com.jdp.hls.model.entiy.Login;
import com.jdp.hls.model.entiy.NodePersonalMeasure;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.model.entiy.Task;

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
    Observable<HttpResult<List<Task>>> getTask(@Query("projectId") String projectId, @Query("buildingType") int
            buildingType);

    /*获取任务列表*/
    @GET("api/workflow/GetTaskList")
    Observable<HttpResult<List<Business>>> getTaskList(@Query("projectId") String projectId, @Query("buildingType") int
            buildingType, @Query("taskType") int taskType);

    /*获取航拍复查列表*/
    @GET("api/house/getAirPhotoList")
    Observable<HttpResult<List<AirPhotoItem>>> getAirPhotoList(@Query("projectId") String projectId, @Query
            ("airCurrentNodeType") int airCurrentNodeType);

    /*获取复查对象*/
    @GET("api/house/getAirPhotoList")
    Observable<HttpResult<List<AirPhotoPerson>>> getAirPhotoPersons(@Query("projectId") String projectId);

    /*获取公示列表*/
    @GET("api/house/getPublicityList")
    Observable<HttpResult<List<PublicityItem>>> getPublicityList(@Query("projectId") String projectId, @Query("type")
            int type);

    /*获取一览表列表*/
    @GET("api/workflow/GetLevyList")
    Observable<HttpResult<List<Table>>> getTables(@Query("projectId") String projectId, @Query("buildingType") int
            buildingType);

    /*获取字典表*/
    @GET("api/SystemSevice/GetAllConfigList")
    Observable<HttpResult<List<Dict>>> getDicts();

    /*个人基本信息*/
    @GET("api/Workflow/GetHouseTaskBasicInfo")
    Observable<HttpResult<BaiscPersonal>> getPersonalBasic(@Query("buildingId") String buildingId);

    /*企业基本信息*/
    @GET("api/workflow/GetEnterpriseTaskBasicInfo")
    Observable<HttpResult<BaiscCompany>> getCompanyBasic(@Query("buildingId") String buildingId);

    /*个人信息详情*/
    @GET("api/workflow/GetHouseTaskDetail")
    Observable<HttpResult<DetailPersonal>> getPersonalDetail(@Query("buildingId") String buildingId);

    /*企业-信息详情*/
    @GET("api/workflow/GetEnterpriseTaskDetail")
    Observable<HttpResult<DetailCompany>> getCompanyDetail(@Query("buildingId") String buildingId);

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

    /*个人-入户丈量-获取*/
    @GET("api/Workflow/GetHouseMeasurement")
    Observable<HttpResult<NodePersonalMeasure>> getPersonalMeasure(@Query("houseId") String houseId);

    /*个人-入户丈量-修改*/
    @POST("api/workflow/UpdateHouseMeasurement")
    Observable<HttpResult<Object>> modifyPersonalMeasure(@Body RequestBody rosterBody);


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



}
