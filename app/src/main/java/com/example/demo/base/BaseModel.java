package com.example.demo.base;

import com.example.core.back.IBaseModel;
import com.example.core.net.APIService;
import com.example.core.back.RetrofitClient;

public interface BaseModel extends IBaseModel {
    @Override
    default APIService getApi(){return RetrofitClient.getInstance().getApi("",true);}
}
