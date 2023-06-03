package com.example.core.net;


import com.example.core.common.api.CommonResult;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    //登录
    @POST("/api/UserLogin")
    Observable<CommonResult<?>> login(@Body RequestBody body);

}
