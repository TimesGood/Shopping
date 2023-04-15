package com.example.demo.net;



import com.example.demo.common.api.CommonResult;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.*;

public interface APIService {

    //登录
    @POST("/api/UserLogin")
    Observable<CommonResult<?>> login(@Body RequestBody body);

}
