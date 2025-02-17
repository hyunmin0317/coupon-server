INSERT INTO coupon.coupons (title, coupon_type, date_issue_start, date_issue_end, date_created, date_updated,
                            discount_amount, min_available_amount, issued_quantity, total_quantity)
VALUES ('네고왕 선착순 쿠폰', 'FIRST_COME_FIRST_SERVED', '2025-02-17 21:40:33.000000', '2025-02-24 21:40:27.000000', NOW(),
        NOW(), 100000, 110000, 0, 500);
COMMIT;
