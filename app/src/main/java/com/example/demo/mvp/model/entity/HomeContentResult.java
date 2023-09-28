package com.example.demo.mvp.model.entity;


import java.util.List;

/**
 * 首页内容返回信息封装
 */

public class HomeContentResult {
    private List<SmsHomeAdvertise> advertiseList;
    private List<PmsBrand> brandList;
    private HomeFlashPromotion homeFlashPromotion;
    private List<PmsProduct> newProductList;
    private List<PmsProduct> hotProductList;
    private List<CmsSubject> subjectList;

    public List<SmsHomeAdvertise> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(List<SmsHomeAdvertise> advertiseList) {
        this.advertiseList = advertiseList;
    }

    public List<PmsBrand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<PmsBrand> brandList) {
        this.brandList = brandList;
    }

    public HomeFlashPromotion getHomeFlashPromotion() {
        return homeFlashPromotion;
    }

    public void setHomeFlashPromotion(HomeFlashPromotion homeFlashPromotion) {
        this.homeFlashPromotion = homeFlashPromotion;
    }

    public List<PmsProduct> getNewProductList() {
        return newProductList;
    }

    public void setNewProductList(List<PmsProduct> newProductList) {
        this.newProductList = newProductList;
    }

    public List<PmsProduct> getHotProductList() {
        return hotProductList;
    }

    public void setHotProductList(List<PmsProduct> hotProductList) {
        this.hotProductList = hotProductList;
    }

    public List<CmsSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<CmsSubject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "HomeContentResult{" +
                "advertiseList=" + advertiseList +
                ", brandList=" + brandList +
                ", homeFlashPromotion=" + homeFlashPromotion +
                ", newProductList=" + newProductList +
                ", hotProductList=" + hotProductList +
                ", subjectList=" + subjectList +
                '}';
    }
}
