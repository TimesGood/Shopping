package com.example.demo.mvp.model;

import com.example.core.api.CommonResult;
import com.example.core.base.mvp.BaseModel;
import com.example.core.di.scope.ActivityScope;
import com.example.core.net.IRepositoryManager;
import com.example.demo.contract.TestContract;
import com.example.demo.mvp.model.api.APIService;
import com.example.demo.mvp.model.entity.HomeContentResult;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;
import com.example.demo.mvp.model.entity.TokenVo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@ActivityScope
public class TestModel extends BaseModel implements TestContract.Model {

    @Inject
    Gson gson;
    @Inject
    public TestModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public Observable<CommonResult<TokenVo>> test(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","test");
        map.put("password","123456");
        RequestBody body = RequestBody.Companion.create(gson.toJson(map), MediaType.parse("application/json;charset=utf-8"));
        return mRepositoryManager.obtainRetrofitService(APIService.class)
                .login("test","123456");
    }

    public Observable<CommonResult<HomeContentResult>> content(){
        return mRepositoryManager.obtainRetrofitService(APIService.class).content();
    }

    @Override
    public Observable<CommonResult<PmsPortalProductDetail>> productDetail(int id) {
        return mRepositoryManager.obtainRetrofitService(APIService.class).productDetail(id);
    }
}
