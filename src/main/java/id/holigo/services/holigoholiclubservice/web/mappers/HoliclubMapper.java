package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.Holiclub;
import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.web.model.HoliclubDto;

@DecoratedWith(HoliclubMapperDecorator.class)
@Mapper
public interface HoliclubMapper {
    HoliclubDto holiclubToHoliclubDto(Holiclub holiclub, UserClub userClub);

    Holiclub holiclubDtoToHoliclub(HoliclubDto holiclubDto);
}
