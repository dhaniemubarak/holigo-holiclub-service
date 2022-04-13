package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.RegisterOfficialAccount;
import id.holigo.services.holigoholiclubservice.web.model.RegisterOfficialAccountDto;

@DecoratedWith(RegisterOfficialAccountMapperDecorator.class)
@Mapper
public interface RegisterOfficialAccountMapper {

    RegisterOfficialAccountDto registerOfficialAccountToRegisterOfficialAccountDto(
            RegisterOfficialAccount registerOfficialAccount);
}
