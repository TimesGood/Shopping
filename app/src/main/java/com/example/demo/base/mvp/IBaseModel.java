package com.example.demo.base.mvp;

import com.example.demo.net.APIService;
import com.example.demo.net.RetrofitClient;
import com.google.gson.Gson;

/**
 * M层接口
 * 主要用于网络请求，获取网络请求数据
 */
public interface IBaseModel {
    Gson gson = new Gson();
    /**
     * 获取网络请求Api实例
     * @return APIService实例对象
     */
    default APIService getApi() {
       return RetrofitClient.getInstance().getApi();
    }

    /**
     * Gson json解析
     * @return
     */
    default Gson getGson() {
        return new Gson();
    }

}