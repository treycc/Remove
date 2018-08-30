package com.jdp.hls.model.service;


import com.jdp.hls.model.entiy.HttpResult;
import com.jdp.hls.model.entiy.Login;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.RosterDetail;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /*增加花名册*/
    @POST("api/person/AddRoster")
    Observable<HttpResult<Object>> addRoster(@Body RequestBody rosterBody);

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
}
