package com.example.demo.base.mvp;

import com.example.demo.base.mvp.IBaseModel;
import com.example.demo.base.mvp.IBasePresenter;
import com.example.demo.base.mvp.IBaseView;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;


/**
 * P层基类，需要持有V层、M层，主要对其解耦及绑定对应V层、M层
 * @param <V> 继承IBaseView的接口
 * @param <M> 继承IBaseModel的接口
 */
public abstract class BasePresenter<V extends IBaseView,M extends IBaseModel> implements IBasePresenter {
    protected V mView;
    protected M mModel;
    /**
     * Disposable管理容器
     */
    protected CompositeDisposable mDisposable;
    public BasePresenter(M m) {
        mModel = m;
        this.mDisposable = new CompositeDisposable();
    }
    /**
     * 绑定V层
     */
    public void bindView(V v){
        mView = v;
        if (mModel == null) {
            throw new NullPointerException("未绑定Model");
        }
    }

    /**
     * RxJava订阅
     */
    public void setDisposable(Disposable disposable) {
        mDisposable.add(disposable);
    }
    /**
     * RxJava解除订阅
     */
    public void dispose() {
        if(!mDisposable.isDisposed()) mDisposable.dispose();
    }
    /**
     * 检查View是否已经绑定
     */
    public boolean isViewAttached() {
        return mView != null;
    }
    /**
     * 检查View是否已经绑定，并抛出异常
     */
    public void checkViewAttached() {
        if (!isViewAttached()) throw new RuntimeException("未注册View");
    }
    /**
     * 解除绑定释放资源
     */
    @Override
    public void onDetach() {
        dispose();
        mView = null;
    }
}
