package com.coupon.core.repository.mysql;

import com.coupon.core.model.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponIssueJpaRepository extends JpaRepository<CouponIssue, Long> {

}
