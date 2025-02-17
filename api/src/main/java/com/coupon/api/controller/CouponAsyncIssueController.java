package com.coupon.api.controller;

import com.coupon.api.controller.dto.CouponIssueRequestDto;
import com.coupon.api.controller.dto.CouponIssueResponseDto;
import com.coupon.api.service.CouponAsyncIssueRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponAsyncIssueController {

    private final CouponAsyncIssueRequestService couponAsyncIssueRequestService;

    @PostMapping("/v1/issue/async")
    public CouponIssueResponseDto asyncIssueV1(@RequestBody CouponIssueRequestDto body) {
        couponAsyncIssueRequestService.issueRequestV1(body);
        return new CouponIssueResponseDto(true, null);
    }

    @PostMapping("/v2/issue/async")
    public CouponIssueResponseDto asyncIssueV2(@RequestBody CouponIssueRequestDto body) {
        couponAsyncIssueRequestService.issueRequestV2(body);
        return new CouponIssueResponseDto(true, null);
    }
}
