package id.holigo.services.holigoholiclubservice.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

            List<UserGroup> userGroups = userGroupRepository.findAll();

            for (UserGroup userGroup : userGroups) {
                if (updatedUserClub.getExp() > userGroup.getMinExp()
                        && updatedUserClub.getUserGroup() != userGroup.getUserGroup()) {
                    updatedUserClub.setUserGroup(userGroup.getUserGroup());
                    userClubRepository.save(updatedUserClub);
                    userService.updateUserGroup(UpdateUserGroupDto.builder().userGroup(userGroup.getUserGroup())
                            .userId(updatedUserClub.getUserId()).build());
                    break;
                }

            }
        }
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
