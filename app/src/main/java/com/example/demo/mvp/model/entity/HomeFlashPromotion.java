package com.example.demo.mvp.model.entity;

import java.util.Date;
import java.util.List;

/**
 * 首页秒杀场次信息封装
 */

public class HomeFlashPromotion {
    private Date startTime;
    private Date endTime;
    private Date nextStartTime;
    private Date nextEndTime;
    private List<FlashPromotionProduct> productList;
}