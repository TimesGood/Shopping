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
package com.example.core.test.component;

import android.app.Application;
import com.example.core.test.AppDelegate;
import com.example.core.test.cache.Cache;
import com.example.core.test.module.AppModule;
import com.example.core.test.module.ClientModule;
import com.example.core.test.module.GlobalConfigModule;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Singleton;

/**
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class})
public interface AppComponent {
    void inject(AppDelegate delegate);
    Cache.Factory cacheFactory();
    @Component.Builder
    interface Builder {
        /**
         * 初始化时提供Application
         * @param application
         * @return
         */
        @BindsInstance
        Builder application(Application application);

        Builder globalConfigModule(GlobalConfigModule globalConfigModule);

        AppComponent build();
    }
}
