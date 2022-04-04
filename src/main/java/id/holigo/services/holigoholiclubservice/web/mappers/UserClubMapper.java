package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.web.model.UserClubDto;

@Mapper
public interface UserClubMapper {
    UserClubDto userClubToUserClubDto(UserClub userClub);
}
