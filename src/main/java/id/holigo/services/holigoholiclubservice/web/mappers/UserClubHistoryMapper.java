package id.holigo.services.holigoholiclubservice.web.mappers;

import id.holigo.services.holigoholiclubservice.domain.UserClubHistory;
import id.holigo.services.holigoholiclubservice.web.model.UserClubHistoryDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserClubHistoryMapper {
    UserClubHistoryDto userClubHistoryToUserClubHistoryDto(UserClubHistory userClubHistory);
}
