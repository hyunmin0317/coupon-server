package com.coupon.api.controller.dto;

public record CouponIssueRequestDto(
        long userId,
        long couponId
) {
    
}
