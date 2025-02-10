package com.coupon.api.controller;

import com.coupon.api.controller.dto.CouponIssueRequestDto;
import com.coupon.api.controller.dto.CouponIssueResponseDto;
import com.coupon.api.service.CouponIssueRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponIssueController {

    private final CouponIssueRequestService couponIssueRequestService;

    @PostMapping("/v1/issue")
    public CouponIssueResponseDto issueV1(@RequestBody CouponIssueRequestDto body) {
        couponIssueRequestService.issueRequestV1(body);
        return new CouponIssueResponseDto(true, null);
    }

    @PostMapping("/v2/issue")
    public CouponIssueResponseDto issueV2(@RequestBody CouponIssueRequestDto body) {
        couponIssueRequestService.issueRequestV2(body);
        return new CouponIssueResponseDto(true, null);
    }

    @PostMapping("/v3/issue")
    public CouponIssueResponseDto issueV3(@RequestBody CouponIssueRequestDto body) {
        couponIssueRequestService.issueRequestV3(body);
        return new CouponIssueResponseDto(true, null);
    }

    @PostMapping("/v4/issue")
    public CouponIssueResponseDto issueV4(@RequestBody CouponIssueRequestDto body) {
        couponIssueRequestService.issueRequestV4(body);
        return new CouponIssueResponseDto(true, null);
    }
}
