package id.holigo.services.holigoholiclubservice.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import id.holigo.services.common.model.UserGroupEnum;
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
import id.holigo.services.holigoholiclubservice.repositories.UserGroupRepository;
import id.holigo.services.holigoholiclubservice.services.user.UserService;

@Service
public class HoliclubServiceImpl implements HoliclubService {

    @Autowired
    private UserClubRepository userClubRepository;

    @Autowired
    private UserClubHistoryRepository userClubHistoryRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public void incrementUserClubExp(IncrementUserClubDto incrementUserClubDto) {

        Optional<UserClub> fetchUserclub = userClubRepository.findById(incrementUserClubDto.getUserId());
        if (fetchUserclub.isPresent()) {

            UserClub userClub = fetchUserclub.get();
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
            if (updatedUserClub.getExp() >= 0 && updatedUserClub.getExp() < 200) {
                newGroup = UserGroupEnum.NETIZEN;
                updatedUserClub.setName("Netizen");
            }
            if (updatedUserClub.getExp() >= 200 && updatedUserClub.getExp() < 2000) {
                newGroup = UserGroupEnum.BOSSQIU;
                updatedUserClub.setName("BossQiu");
            }
            if (updatedUserClub.getExp() >= 2000 && updatedUserClub.getExp() < 20000) {
                newGroup = UserGroupEnum.SOELTAN;
                updatedUserClub.setName("Soeltan");
            }
            if (updatedUserClub.getExp() >= 20000) {
                newGroup = UserGroupEnum.CRAZY_RICH;
                updatedUserClub.setName("Crazy Rich");
            }

            if (currentGroup != newGroup) {
                userClubRepository.save(updatedUserClub);
                userService.updateUserGroup(UpdateUserGroupDto.builder().userGroup(newGroup)
                        .userId(updatedUserClub.getUserId()).build());
            }
        }
    }

    @Override
    public void createUserClub(UserClub userClub) {
        userClub.setName("Netizen");
        userClub.setExp(0);
        userClub.setFareAmount(new BigDecimal(0.00));
        userClub.setHasCheck(false);
        userClubRepository.save(userClub);
    }

    private Integer calculateExp(BigDecimal fareAmount) {
        Integer fare = fareAmount.intValue();
        Integer exp = fare / 10000;
        if (exp > 100) {
            exp = 100;
        }
        return exp;
    }

}
