package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import id.holigo.services.holigoholiclubservice.domain.UserGroup;
import id.holigo.services.holigoholiclubservice.web.model.UserGroupDto;

@DecoratedWith(UserGroupMapperDecorator.class)
@Mapper
public interface UserGroupMapper {
    UserGroupDto userGroupToUserGroupDto(UserGroup userGroup);
}
