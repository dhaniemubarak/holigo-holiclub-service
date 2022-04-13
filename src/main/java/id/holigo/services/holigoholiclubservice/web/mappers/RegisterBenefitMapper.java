package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.RegisterBenefit;
import id.holigo.services.holigoholiclubservice.web.model.RegisterBenefitDto;

@DecoratedWith(RegisterBenefitMapperDecorator.class)
@Mapper
public interface RegisterBenefitMapper {
    RegisterBenefitDto registerBenefitToRegisterBenefitDto(RegisterBenefit registerBenefit);
}
