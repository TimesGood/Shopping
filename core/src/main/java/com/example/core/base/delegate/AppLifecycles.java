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
package com.example.core.base.delegate;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

/**
 * ================================================
 * 用于代理 {@link Application} 的生命周期
 */
public interface AppLifecycles {
    void attachBaseContext(@NonNull Context base);

    /**
     * 程序创建时触发
     * @param application
     */
    void onCreate(@NonNull Application application);

    /**
     * 在模拟环境中程序被终止时调用
     * 注意真机环境永远不会被调用
     * @param application
     */
    void onTerminate(@NonNull Application application);
    /**
     * 内存不够时触发
     */
//    void  onLowMemory();
    /**
     * 内存清理时调用
     */
//    void onTrimMemory(int level);
    /**
     * 配置被改变时调用
     * 例：
     *      屏幕方向改变时
     *      设备语言修改
     *      屏幕尺寸更改
     * 总之，该函数可以监控设备的变化来对应用进行一定的调整
     */
//    void  onConfigurationChanged(Configuration newConfig);

}
