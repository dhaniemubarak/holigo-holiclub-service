package id.holigo.services.holigoholiclubservice.services.coupon;

import id.holigo.services.common.model.CouponUserDto;
import id.holigo.services.holigoholiclubservice.domain.UserClub;

public interface CouponService {
    void assignUserCoupon(UserClub userClub, CouponUserDto couponUser50, CouponUserDto couponUser100);
}
