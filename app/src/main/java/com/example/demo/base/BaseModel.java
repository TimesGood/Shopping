package com.example.demo.base;

import com.example.core.base.mvp.IBaseModel;
import com.example.core.net.APIService;
import com.example.core.net.RetrofitClient;

public interface BaseModel extends IBaseModel {
    @Override
    default APIService getApi(){return RetrofitClient.getInstance().getApi("",true);}
}
