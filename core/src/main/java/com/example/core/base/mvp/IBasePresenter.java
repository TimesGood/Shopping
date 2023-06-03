package com.example.core.base.mvp;

/**
 * P层接口
 * 主要用于View的绑定与解绑
 */
public interface IBasePresenter{
    //解绑
    void onDetach();
    
}