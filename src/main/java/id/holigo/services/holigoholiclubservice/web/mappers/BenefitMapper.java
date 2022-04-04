package id.holigo.services.holigoholiclubservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import id.holigo.services.holigoholiclubservice.domain.Benefit;
import id.holigo.services.holigoholiclubservice.web.model.BenefitDto;

@DecoratedWith(BenefitMapperDecorator.class)
@Mapper
public interface BenefitMapper {
    BenefitDto benefitToBenefitDto(Benefit benefit);
}
