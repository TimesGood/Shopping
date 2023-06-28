package com.example.demo.ui.model;

import com.example.core.base.mvp.BaseModel;
import com.example.core.di.scope.ActivityScope;
import com.example.core.net.IRepositoryManager;
import com.example.demo.ui.contract.TestContract;

import javax.inject.Inject;

@ActivityScope
public class TestModel extends BaseModel implements TestContract.Model {

    @Inject
    public TestModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public String test(){
        System.out.println("测试:"+mRepositoryManager);
        return "测试";
    }

}
