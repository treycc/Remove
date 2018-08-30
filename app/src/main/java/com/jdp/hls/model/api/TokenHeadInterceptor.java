package com.jdp.hls.model.api;

import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:TODO
 * Create Time:2018/4/17 17:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TokenHeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        long timeStamp = System.currentTimeMillis();
        String token = SpSir.getInstance().getToken();
        Request request = chain.request()
                .newBuilder()
                .addHeader("token", token)
                .addHeader("timeStamp", String.valueOf(timeStamp))
                .addHeader("signature", EncryptUtil.getSignature(timeStamp,token))
                .build();
        return chain.proceed(request);
    }

}
