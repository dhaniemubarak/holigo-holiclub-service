package id.holigo.services.holigoholiclubservice.web.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import id.holigo.services.holigoholiclubservice.domain.UserGroup;
import id.holigo.services.holigoholiclubservice.web.model.UserGroupDto;

public abstract class UserGroupMapperDecorator implements UserGroupMapper {

    private UserGroupMapper userGroupMapper;

    private BenefitMapper benefitMapper;

    @Autowired
    public void setUserGroupMapper(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    @Autowired
    public void setBenefitMapper(BenefitMapper benefitMapper) {
        this.benefitMapper = benefitMapper;
    }

    @Override
    public UserGroupDto userGroupToUserGroupDto(UserGroup userGroup) {
        UserGroupDto userGroupDto = userGroupMapper.userGroupToUserGroupDto(userGroup);
        userGroupDto.setBenefit(
                userGroup.getBenefit().stream().map(benefitMapper::benefitToBenefitDto).collect(Collectors.toList()));
        return userGroupDto;
    }

}
