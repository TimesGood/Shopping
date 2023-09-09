/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.core.permission;

import android.os.Build;

import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.ArrayList;
import java.util.List;



/**
 * ================================================
 * 权限请求工具类
 * ================================================
 */
public class PermissionUtil {
    public static final String TAG = "Permission";

    private PermissionUtil() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static void requestPermission(DefaultPermissionObserver observer, RxPermissions rxPermissions, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            return;
        }

        List<String> needRequest = new ArrayList<>();
        for (String permission : permissions) { //过滤调已经申请过的权限
            if (!rxPermissions.isGranted(permission)) {
                needRequest.add(permission);
            }
        }

        if (needRequest.isEmpty()) {//全部权限都已经申请过，直接执行操作
            observer.onRequestPermissionSuccess();
        } else {//没有申请过,则开始申请
            rxPermissions
                    .requestEach(needRequest.toArray(new String[0]))
                    .buffer(permissions.length)
                    .subscribe(observer);
        }
    }

    /**
     * 获取权限失败时得到相应权限的消息
     * @param permissions 权限组
     * @return 消息
     */
    public static String getPermissionHint(List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return "获取权限失败，请手动授予权限";
        }
        List<String> hints = new ArrayList<>();
        for (String permission : permissions) {
            switch (permission) {
                case Permissions.READ_EXTERNAL_STORAGE:
                case Permissions.WRITE_EXTERNAL_STORAGE:
                case Permissions.MANAGE_EXTERNAL_STORAGE: {
                    String hint = "存储权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.CAMERA: {
                    String hint = "相机权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.RECORD_AUDIO: {
                    String hint = "麦克风权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.ACCESS_FINE_LOCATION:
                case Permissions.ACCESS_COARSE_LOCATION:
                case Permissions.ACCESS_BACKGROUND_LOCATION: {
                    String hint;
                    if (!permissions.contains(Permissions.ACCESS_FINE_LOCATION) &&
                            !permissions.contains(Permissions.ACCESS_COARSE_LOCATION)) {
                        hint = "后台定位权限";
                    } else {
                        hint = "定位权限";
                    }
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.READ_PHONE_STATE:
                case Permissions.CALL_PHONE:
                case Permissions.ADD_VOICEMAIL:
                case Permissions.USE_SIP:
                case Permissions.READ_PHONE_NUMBERS:
                case Permissions.ANSWER_PHONE_CALLS: {
                    String hint = "电话权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.GET_ACCOUNTS:
                case Permissions.READ_CONTACTS:
                case Permissions.WRITE_CONTACTS: {
                    String hint = "通讯录权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.READ_CALENDAR:
                case Permissions.WRITE_CALENDAR: {
                    String hint = "日历权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.READ_CALL_LOG:
                case Permissions.WRITE_CALL_LOG:
                case Permissions.PROCESS_OUTGOING_CALLS: {
                    String hint = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ?
                            "通话记录权限" : "电话权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.BODY_SENSORS: {
                    String hint = "身体传感权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.ACTIVITY_RECOGNITION: {
                    String hint = "健身运动权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.SEND_SMS:
                case Permissions.RECEIVE_SMS:
                case Permissions.READ_SMS:
                case Permissions.RECEIVE_WAP_PUSH:
                case Permissions.RECEIVE_MMS: {
                    String hint = "短信权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.REQUEST_INSTALL_PACKAGES: {
                    String hint = "安装应用权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.NOTIFICATION_SERVICE: {
                    String hint = "通知栏权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.SYSTEM_ALERT_WINDOW: {
                    String hint = "悬浮窗权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                case Permissions.WRITE_SETTINGS: {
                    String hint = "系统设置权限";
                    if (!hints.contains(hint)) {
                        hints.add(hint);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        if (!hints.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String text : hints) {
                if (builder.length() == 0) {
                    builder.append(text);
                } else {
                    builder.append("、")
                            .append(text);
                }
            }
            builder.append(" ");
            return builder.toString();
        }
        return "";
    }

}

