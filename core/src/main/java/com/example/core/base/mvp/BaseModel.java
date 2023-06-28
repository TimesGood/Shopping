package com.example.core.base.mvp;

import com.example.core.net.IRepositoryManager;

public class BaseModel implements IModel{
    protected IRepositoryManager mRepositoryManager;//用于管理网络请求层, 以及数据缓存层
    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    /**
     * 在框架中 {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
