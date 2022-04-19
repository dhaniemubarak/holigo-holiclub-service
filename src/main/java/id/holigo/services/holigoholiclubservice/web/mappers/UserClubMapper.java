package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.common.model.UserClubDto;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserClubMapper {
    UserClubDto userClubToUserClubDto(UserClub userClub);

    UserClub userClubDtoToUserClub(UserClubDto userClubDto);
}
