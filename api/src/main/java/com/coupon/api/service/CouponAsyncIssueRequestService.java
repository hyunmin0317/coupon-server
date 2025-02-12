package com.coupon.api.service;

import com.coupon.api.controller.dto.CouponIssueRequestDto;
import com.coupon.core.service.CouponAsyncIssueServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponAsyncIssueRequestService {

    private final CouponAsyncIssueServiceV1 couponAsyncIssueServiceV1;

    public void issueRequestV1(CouponIssueRequestDto requestDto) {
        couponAsyncIssueServiceV1.issue(requestDto.couponId(), requestDto.userId());
    }
}
