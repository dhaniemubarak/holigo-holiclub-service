package id.holigo.services.holigoholiclubservice.services.coupon;

import id.holigo.services.common.model.CouponUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("holigo-coupon-service")
public interface CouponServiceFeignClient {
    String POST_COUPON_USER = "/api/v1/couponUser/hotel";

    @RequestMapping(method = RequestMethod.POST, value = POST_COUPON_USER)
    ResponseEntity<HttpStatus> createCoupon(@RequestBody CouponUserDto couponUserDto);
}
