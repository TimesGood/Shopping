package com.example.demo.mvp.model;

import com.example.core.api.CommonResult;
import com.example.core.base.mvp.BaseModel;
import com.example.core.di.scope.ActivityScope;
import com.example.core.net.IRepositoryManager;

import com.example.demo.contract.ProductDetailContract;
import com.example.demo.mvp.model.api.APIService;
import com.example.demo.mvp.model.entity.PmsPortalProductDetail;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

@ActivityScope
public class ProductDetailModel extends BaseModel implements ProductDetailContract.Model {

    @Inject
    Gson gson;
    @Inject
    public ProductDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<CommonResult<PmsPortalProductDetail>> productDetail(int id) {
        return mRepositoryManager.obtainRetrofitService(APIService.class).productDetail(id);
    }
}
