package com.coupon.api.service;

import com.coupon.api.controller.dto.CouponIssueRequestDto;
import com.coupon.core.component.DistributeLockExecutor;
import com.coupon.core.service.CouponIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponIssueRequestService {

    private final CouponIssueService couponIssueService;
    private final DistributeLockExecutor distributeLockExecutor;

    public void issueRequestV1(CouponIssueRequestDto requestDto) {
        couponIssueService.issueV1(requestDto.couponId(), requestDto.userId());
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }

    public void issueRequestV2(CouponIssueRequestDto requestDto) {
        synchronized (this) {
            couponIssueService.issueV1(requestDto.couponId(), requestDto.userId());
        }
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }

    public void issueRequestV3(CouponIssueRequestDto requestDto) {
        distributeLockExecutor.execute("lock_%s".formatted(requestDto.couponId()), 10000, 10000, () -> {
            couponIssueService.issueV1(requestDto.couponId(), requestDto.userId());
        });
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }

    public void issueRequestV4(CouponIssueRequestDto requestDto) {
        couponIssueService.issueV2(requestDto.couponId(), requestDto.userId());
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }
}
