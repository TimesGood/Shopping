package com.example.core.base.mvp;

import autodispose2.AutoDisposeConverter;

public interface IView {
    //显示加载
    void showLoading();
    //隐藏加载
    void hideLoading();
    //请求错误
    default void onError(String message) {}
    //绑定生命周期
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
