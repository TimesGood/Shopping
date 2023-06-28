package com.example.core.base.mvp;

import com.example.core.base.mvp.IPresenter;
import com.example.core.util.Preconditions;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * P层基类，需要持有V层、M层，主要对其解耦及绑定对应V层、M层
 * @param <V> 继承IBaseView的接口
 * @param <M> 继承IBaseModel的接口
 */
public abstract class BasePresenter<V extends IView,M extends IModel> implements IPresenter {
    protected V mView;
    protected M mModel;
    /**
     * Disposable管理容器
     */
    protected CompositeDisposable mDisposable;
    public BasePresenter(M m, V v) {
        Preconditions.checkNotNull(m, "%s cannot be null", IModel.class.getName());
        Preconditions.checkNotNull(v, "%s cannot be null", IView.class.getName());
        this.mView = v;
        this.mModel = m;
    }
    public BasePresenter(V v) {
        Preconditions.checkNotNull(v, "%s cannot be null", IView.class.getName());
        this.mView = v;
    }

    /**
     * RxJava添加订阅
     */
    public void addDisposable(Disposable disposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(disposable);
    }
    /**
     * RxJava解除订阅
     */
    public void dispose() {
        if(mDisposable != null && !mDisposable.isDisposed()) mDisposable.dispose();
    }
    /**
     * 解除绑定释放资源
     */
    @Override
    public void onDetach() {
        mDisposable = null;
        mView = null;
    }
}
