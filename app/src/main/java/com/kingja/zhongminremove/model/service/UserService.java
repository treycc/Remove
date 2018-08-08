package com.kingja.zhongminremove.model.service;


import com.kingja.zhongminremove.model.HttpResult;
import com.kingja.zhongminremove.page.login.Login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface UserService {


    /*登录*/
    @FormUrlEncoded
    @POST("/api/employee/login")
    Observable<HttpResult<Login>> login(@Field("accountName") String accountName, @Field("password") String password,
                                        @Field("accountType ") int accountType);


}
