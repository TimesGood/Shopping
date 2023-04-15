package com.example.demo.base;

/**
 * P层接口
 * 主要用于View的绑定与解绑
 */
public interface IBasePresenter<V extends IBaseView> {

    //绑定
    void onAttach(V v);

    //解绑
    void onDetach();
    
}