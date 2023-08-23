package com.example.demo.mvp.model.api;



import com.example.core.api.CommonResult;
import com.example.demo.mvp.model.entity.TokenVo;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    //登录
    @POST("/sso/login")
    Observable<CommonResult<TokenVo>> login(@Query("username") String username,@Query("password") String password);

}
