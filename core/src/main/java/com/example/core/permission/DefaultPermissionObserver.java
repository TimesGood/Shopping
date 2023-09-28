package com.example.core.permission;


import com.tbruyelle.rxpermissions3.Permission;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 权限申请统一监听
 */
public abstract class DefaultPermissionObserver implements Observer<List<Permission>> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull List<Permission> permissions) {
        List<String> failurePermissions = new ArrayList<>();
        List<String> askNeverAgainPermissions = new ArrayList<>();
        for (Permission permission : permissions) {
            if(permission.granted){
                //用户同意授权
                onRequestPermissionSuccess();

            }else if(permission.shouldShowRequestPermissionRationale){
                //用户拒绝授权但未勾选了”不再询问“
                failurePermissions.add(permission.name);
            }else {
                //用户拒绝授权并勾选了”不再询问“
                askNeverAgainPermissions.add(permission.name);
            }
        }
        if (failurePermissions.size() > 0) {
            onRequestPermissionFailure(failurePermissions);
        }

        if (askNeverAgainPermissions.size() > 0) {
            onRequestPermissionFailureWithAskNeverAgain(askNeverAgainPermissions);
        }

        if (failurePermissions.size() == 0 && askNeverAgainPermissions.size() == 0) {
            onRequestPermissionSuccess();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    //获取权限成功，处理业务
    public abstract void onRequestPermissionSuccess();
    public abstract void onRequestPermissionFailure(List<String> permissions);
    public abstract void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions);
}