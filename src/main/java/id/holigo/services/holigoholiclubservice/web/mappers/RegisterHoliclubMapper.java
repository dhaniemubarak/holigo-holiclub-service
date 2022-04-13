package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.RegisterHoliclub;
import id.holigo.services.holigoholiclubservice.web.model.RegisterHoliclubDto;

@DecoratedWith(RegisterHoliclubMapperDecorator.class)
@Mapper
public interface RegisterHoliclubMapper {
    RegisterHoliclubDto registerHoliclubToRegisterHoliclubDto(RegisterHoliclub registerHoliclub);
}
