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

    // Lock X - 동시성 이슈 발생
    public void issueRequestV1(CouponIssueRequestDto requestDto) {
        couponIssueService.issueV1(requestDto.couponId(), requestDto.userId());
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }

    // Java Lock - 단일 서버에서만 동작, 분산 서버에서 동시성 문제 해결 X
    public void issueRequestV2(CouponIssueRequestDto requestDto) {
        synchronized (this) {
            couponIssueService.issueV1(requestDto.couponId(), requestDto.userId());
        }
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }

    // Redis Lock - 여러 서버에서 동일한 ID에 대해 한 번만 실행하여 동시성 문제 해결
    public void issueRequestV3(CouponIssueRequestDto requestDto) {
        distributeLockExecutor.execute("lock_%s".formatted(requestDto.couponId()), 10000, 10000, () -> {
            couponIssueService.issueV1(requestDto.couponId(), requestDto.userId());
        });
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }

    // MySQL Lock - DB 트랜잭션을 제어하여 동시성 문제 해결
    public void issueRequestV4(CouponIssueRequestDto requestDto) {
        couponIssueService.issueV2(requestDto.couponId(), requestDto.userId());
        log.info("쿠폰 발급 완료. couponId: %s, userId: %s".formatted(requestDto.couponId(), requestDto.userId()));
    }
}
