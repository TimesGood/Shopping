package com.example.demo.mvp.model.entity;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 */
public class FlashPromotionProduct extends PmsProduct {
    private BigDecimal flashPromotionPrice;
    private Integer flashPromotionCount;
    private Integer flashPromotionLimit;
}