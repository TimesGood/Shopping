package com.example.demo.base.mvp;

import autodispose2.AutoDisposeConverter;

/**
 * V层接口
 * 主要用于请求网络前加载与加载完毕的操作
 */
public interface IBaseView {

    //显示加载
    void showLoading();
    //隐藏加载
    void hideLoading();
    //请求错误
    default void onError(String message) {}
    //解绑
    <T> AutoDisposeConverter<T> bindAutoDispose();

}