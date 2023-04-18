package com.example.demo.ui.model;

import com.example.demo.base.mvp.IBaseModel;
import com.example.demo.ui.contract.TestContract;

public class TestModel implements TestContract.Model {

    public String test(){
        return "测试";
    }
}
