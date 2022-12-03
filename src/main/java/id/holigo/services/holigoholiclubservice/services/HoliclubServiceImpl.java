package id.holigo.services.holigoholiclubservice.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import id.holigo.services.common.model.CouponUserDto;
import id.holigo.services.common.model.UserGroupEnum;
import id.holigo.services.holigoholiclubservice.services.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.holigo.services.common.model.IncrementUserClubDto;
import id.holigo.services.common.model.UpdateUserGroupDto;
import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.domain.UserClubHistory;
import id.holigo.services.holigoholiclubservice.domain.UserGroup;
import id.holigo.services.holigoholiclubservice.repositories.UserClubHistoryRepository;
import id.holigo.services.holigoholiclubservice.repositories.UserClubRepository;
import id.holigo.services.holigoholiclubservice.services.user.UserService;

@Service
public class HoliclubServiceImpl implements HoliclubService {

    private UserClubRepository userClubRepository;

    private UserClubHistoryRepository userClubHistoryRepository;

    private UserService userService;

    private CouponService couponService;

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    @Autowired
    public void setUserClubRepository(UserClubRepository userClubRepository) {
        this.userClubRepository = userClubRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserClubHistoryRepository(UserClubHistoryRepository userClubHistoryRepository) {
        this.userClubHistoryRepository = userClubHistoryRepository;
    }

    @Transactional
    @Override
    public void incrementUserClubExp(IncrementUserClubDto incrementUserClubDto) {

        Optional<UserClub> fetchUserClub = userClubRepository.findById(incrementUserClubDto.getUserId());
        if (fetchUserClub.isPresent()) {

            UserClub userClub = fetchUserClub.get();
            Integer currentExp = userClub.getExp();
            Integer newExp = calculateExp(incrementUserClubDto.getFareAmount());
            Integer finalExp = currentExp + newExp;
            BigDecimal currentFareAmount = userClub.getFareAmount();
            currentFareAmount = currentFareAmount.add(incrementUserClubDto.getFareAmount());
            userClub.setExp(finalExp);
            userClub.setFareAmount(currentFareAmount);
            UserClub updatedUserClub = userClubRepository.save(userClub);

            UserClubHistory userClubHistory = UserClubHistory.builder().userId(incrementUserClubDto.getUserId())
                    .invoiceNumber(incrementUserClubDto.getInvoiceNumber())
                    .fareAmount(incrementUserClubDto.getFareAmount())
                    .exp(newExp)
                    .currentExp(currentExp)
                    .finalExp(finalExp)
                    .build();
            userClubHistoryRepository.save(userClubHistory);
            UserGroupEnum currentGroup = updatedUserClub.getUserGroup();
            UserGroupEnum newGroup = updatedUserClub.getUserGroup();
            UUID coupon50Id = null;
            UUID coupon100Id = null;
            int coupon50Quantity = 0;
            int coupon100Quantity = 0;
            if (updatedUserClub.getExp() >= 0 && updatedUserClub.getExp() < 200) {
                newGroup = UserGroupEnum.NETIZEN;
                updatedUserClub.setName("Netizen");
                updatedUserClub.setUserGroup(newGroup);
                coupon50Id = UUID.fromString("1f8003ad-26dc-4760-838c-6a1bf65514c8");
                coupon100Id = UUID.fromString("3f9b54d6-6756-4888-82c5-2d1acd803f03");
                coupon50Quantity = 20;
                coupon100Quantity = 10;
            }
            if (updatedUserClub.getExp() >= 200 && updatedUserClub.getExp() < 2000) {
                newGroup = UserGroupEnum.BOSSQIU;
                updatedUserClub.setName("BossQiu");
                updatedUserClub.setUserGroup(newGroup);
                coupon50Id = UUID.fromString("a20425f2-ab2d-4852-b469-db74fcd2dfe3");
                coupon100Id = UUID.fromString("dda94986-2646-418f-9ae4-f3be24caa503");
                coupon50Quantity = 40;
                coupon100Quantity = 20;
            }
            if (updatedUserClub.getExp() >= 2000 && updatedUserClub.getExp() < 20000) {
                newGroup = UserGroupEnum.SOELTAN;
                updatedUserClub.setName("Soeltan");
                updatedUserClub.setUserGroup(newGroup);
                coupon50Id = UUID.fromString("e42d6426-3d1a-4919-8c1b-f5412237f904");
                coupon100Id = UUID.fromString("a3d37f38-b1c7-4490-bb14-32a14a00abf4");
                coupon50Quantity = 60;
                coupon100Quantity = 30;
            }
            if (updatedUserClub.getExp() >= 20000) {
                newGroup = UserGroupEnum.CRAZY_RICH;
                updatedUserClub.setName("Crazy Rich");
                updatedUserClub.setUserGroup(newGroup);
                coupon50Id = UUID.fromString("3307a306-4d52-41ad-818b-b71917bc842a");
                coupon100Id = UUID.fromString("cb5c5edb-4b43-4733-b041-b31057369a27");
                coupon50Quantity = 60;
                coupon100Quantity = 30;
            }

            if (currentGroup != newGroup) {
                updatedUserClub.setCouponHasAdded(false);
                userClubRepository.save(updatedUserClub);
                userService.updateUserGroup(UpdateUserGroupDto.builder().userGroup(newGroup)
                        .userId(updatedUserClub.getUserId()).build());
                CouponUserDto couponUser50 = null;
                if (coupon50Id != null) {
                    couponUser50 = CouponUserDto.builder().couponId(coupon50Id).userId(updatedUserClub.getUserId())
                            .quantity(coupon50Quantity).build();
                }

                CouponUserDto couponUser100 = null;
                if (coupon100Id != null) {
                    couponUser100 = CouponUserDto.builder().couponId(coupon100Id).userId(updatedUserClub.getUserId())
                            .quantity(coupon100Quantity).build();
                }
                couponService.assignUserCoupon(updatedUserClub, couponUser50, couponUser100);
            }
        }
    }

    @Override
    public void createUserClub(UserClub userClub) {
        userClub.setName("Netizen");
        userClub.setExp(0);
        userClub.setFareAmount(BigDecimal.ZERO);
        userClub.setHasCheck(false);
        userClub.setCouponHasAdded(false);
        userClubRepository.save(userClub);
        // create coupon
        CouponUserDto couponUser50K = CouponUserDto.builder()
                .couponId(UUID.fromString("1f8003ad-26dc-4760-838c-6a1bf65514c8"))
                .userId(userClub.getUserId())
                .quantity(20).build();
        CouponUserDto couponUser100K = CouponUserDto.builder()
                .couponId(UUID.fromString("3f9b54d6-6756-4888-82c5-2d1acd803f03"))
                .userId(userClub.getUserId())
                .quantity(10).build();
        couponService.assignUserCoupon(userClub, couponUser50K, couponUser100K);

    }

    private Integer calculateExp(BigDecimal fareAmount) {
        int fare = fareAmount.intValue();
        int exp = fare / 1000;
        if (exp > 100) {
            exp = 100;
        }
        return exp;
    }

}
