package com.example.demo.permission;


import android.content.Context;

import com.example.common.util.IntentUtils;
import com.example.core.permission.DefaultPermissionObserver;
import com.example.core.permission.PermissionUtil;
import com.example.ext.dialog.MessageDialog;

import java.util.List;


/**
 * 权限申请统一监听
 */
public abstract class PermissionObserver extends DefaultPermissionObserver {
    @Override
    public void onRequestPermissionFailure(List<String> permissions) {
                    new MessageDialog.Builder(getContext())
                    .setTitle("权限请求")
                    .setMessage("使用该功能需要" + PermissionUtil.getPermissionHint(permissions)+"，请点击\"确定\"继续授权")
                    .setConfirm("确定")
                    .setListener(dialog -> IntentUtils.gotoPermission(getContext()))
                    .show();
    }

    @Override
    public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
            new MessageDialog.Builder(getContext())
                    .setTitle("权限请求")
                    .setMessage("权限未开启，请手动授予" + PermissionUtil.getPermissionHint(permissions))
                    .setConfirm("去开启")
                    .setListener(dialog -> IntentUtils.gotoPermission(getContext()))
                    .show();
    }

    public abstract Context getContext();
}