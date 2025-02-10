package com.coupon.core.repository.mysql;

import com.coupon.core.model.CouponIssue;
import com.coupon.core.model.QCouponIssue;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponIssueRepository {

    private final JPAQueryFactory queryFactory;

    public CouponIssue findFirstCouponIssue(long couponId, long userId) {
        return queryFactory.selectFrom(QCouponIssue.couponIssue)
                .where(QCouponIssue.couponIssue.couponId.eq(couponId))
                .where(QCouponIssue.couponIssue.userId.eq(userId))
                .fetchFirst();
    }
}
