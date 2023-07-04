package com.example.core.base.mvp;


public interface IModel {
    /**
     * 在框架中 {@link BasePresenter#onDetach()} 时会默认调用 {@link IModel#onDetach()}
     */
    void onDetach();
}
