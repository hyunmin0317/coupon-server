package com.coupon.core.util;

public class CouponRedisUtils {

    public static String getIssueRequestKey(long couponId) {
        return "issue.request.couponId=%s".formatted(couponId);
    }
}
