package com.example.demo.mvp.model.entity;

import java.util.List;

/**
 * 前台商品详情
 */
public class PmsPortalProductDetail{
    private PmsProduct product;
    private PmsBrand brand;
    private List<PmsProductAttribute> productAttributeList;
    private List<PmsProductAttributeValue> productAttributeValueList;
    private List<PmsSkuStock> skuStockList;
    private List<PmsProductLadder> productLadderList;
    private List<PmsProductFullReduction> productFullReductionList;
    private List<SmsCoupon> couponList;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }

    public PmsBrand getBrand() {
        return brand;
    }

    public void setBrand(PmsBrand brand) {
        this.brand = brand;
    }

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    public List<PmsProductAttributeValue> getProductAttributeValueList() {
        return productAttributeValueList;
    }

    public void setProductAttributeValueList(List<PmsProductAttributeValue> productAttributeValueList) {
        this.productAttributeValueList = productAttributeValueList;
    }

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }

    public List<PmsProductLadder> getProductLadderList() {
        return productLadderList;
    }

    public void setProductLadderList(List<PmsProductLadder> productLadderList) {
        this.productLadderList = productLadderList;
    }

    public List<PmsProductFullReduction> getProductFullReductionList() {
        return productFullReductionList;
    }

    public void setProductFullReductionList(List<PmsProductFullReduction> productFullReductionList) {
        this.productFullReductionList = productFullReductionList;
    }

    public List<SmsCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<SmsCoupon> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "PmsPortalProductDetail{" +
                "product=" + product +
                ", brand=" + brand +
                ", productAttributeList=" + productAttributeList +
                ", productAttributeValueList=" + productAttributeValueList +
                ", skuStockList=" + skuStockList +
                ", productLadderList=" + productLadderList +
                ", productFullReductionList=" + productFullReductionList +
                ", couponList=" + couponList +
                '}';
    }
}