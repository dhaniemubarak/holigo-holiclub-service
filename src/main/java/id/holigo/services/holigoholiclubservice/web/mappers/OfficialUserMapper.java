package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.OfficialUser;
import id.holigo.services.holigoholiclubservice.web.model.OfficialUserDto;

@DecoratedWith(OfficialUserMapperDecorator.class)
@Mapper
public interface OfficialUserMapper {

    OfficialUserDto officialUserToUfficialUserDto(OfficialUser officialUser);
}
