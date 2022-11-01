package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.Holiclub;
import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.web.model.HoliclubDto;
import org.mapstruct.Mapping;

@DecoratedWith(HoliclubMapperDecorator.class)
@Mapper
public interface HoliclubMapper {
    @Mapping(target = "welcome", ignore = true)
    @Mapping(target = "caption", ignore = true)
    HoliclubDto holiclubToHoliclubDto(Holiclub holiclub, UserClub userClub);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "indexCaption", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Holiclub holiclubDtoToHoliclub(HoliclubDto holiclubDto);
}
