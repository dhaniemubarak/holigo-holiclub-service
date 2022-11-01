package id.holigo.services.holigoholiclubservice.services.coupon;

import id.holigo.services.common.model.CouponUserDto;
import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.repositories.UserClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements CouponService {

    private CouponServiceFeignClient couponServiceFeignClient;

    private UserClubRepository userClubRepository;

    @Autowired
    public void setCouponServiceFeignClient(CouponServiceFeignClient couponServiceFeignClient) {
        this.couponServiceFeignClient = couponServiceFeignClient;
    }

    @Autowired
    public void setUserClubRepository(UserClubRepository userClubRepository) {
        this.userClubRepository = userClubRepository;
    }

    @Override
    public void assignUserCoupon(UserClub userClub, CouponUserDto couponUser50, CouponUserDto couponUser100) {
        ResponseEntity<HttpStatus> result50K = couponServiceFeignClient.createCoupon(couponUser50);
        ResponseEntity<HttpStatus> result100K = couponServiceFeignClient.createCoupon(couponUser100);
        if (result50K.getStatusCode().equals(HttpStatus.CREATED) && result100K.getStatusCode().equals(HttpStatus.CREATED)) {
            userClub.setCouponHasAdded(true);
            userClubRepository.save(userClub);
        }
    }
}
