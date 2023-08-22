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
package com.example.core.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.example.core.base.delegate.ConfigModule;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 用于解析 AndroidManifest 中的 Meta 属性
 * ================================================
 */
public final class ManifestParser {
    private static final String MODULE_VALUE = "ConfigModule";
    private final Context context;

    public ManifestParser(Context context) {
        this.context = context;
    }

    private static ConfigModule parseModule(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("找不到ConfigModule的实现类，请确认实现类是否被混淆", e);
        }

        Object module;
        try {
            module = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("无法实例化ConfigModule实现类->" + clazz, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("无法实例化ConfigModule实现类->" + clazz, e);
        }

        if (!(module instanceof ConfigModule)) {
            throw new RuntimeException("期望的ConfigModule的实例,但发现：" + module);
        }
        return (ConfigModule) module;
    }

    public List<ConfigModule> parse() {
        List<ConfigModule> modules = new ArrayList<>();
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                for (String key : appInfo.metaData.keySet()) {
                    if (MODULE_VALUE.equals(appInfo.metaData.get(key))) {
                        modules.add(parseModule(key));
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse ConfigModule", e);
        }

        return modules;
    }
}