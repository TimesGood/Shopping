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
package com.example.demo.di.module;


import com.example.demo.contract.ProductDetailContract;
import com.example.demo.mvp.model.ProductDetailModel;
import com.example.demo.permission.Permission;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * ================================================
 */
@Module
public abstract class ProductDetailModule {
    @Provides
    static Permission providePermissions() {
        return new Permission();
    }
    @Binds
    abstract ProductDetailContract.Model bindUserModel(ProductDetailModel model);

}
