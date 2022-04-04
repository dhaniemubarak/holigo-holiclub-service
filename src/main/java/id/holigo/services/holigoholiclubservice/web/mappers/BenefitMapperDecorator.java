package id.holigo.services.holigoholiclubservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigoholiclubservice.domain.Benefit;
import id.holigo.services.holigoholiclubservice.web.model.BenefitDto;

public abstract class BenefitMapperDecorator implements BenefitMapper {

    @Autowired
    MessageSource messageSource;

    private BenefitMapper benefitMapper;

    @Autowired
    public void setBenefitMapper(BenefitMapper benefitMapper) {
        this.benefitMapper = benefitMapper;
    }

    @Override
    public BenefitDto benefitToBenefitDto(Benefit benefit) {
        BenefitDto benefitDto = benefitMapper.benefitToBenefitDto(benefit);
        benefitDto.setCaption(
                messageSource.getMessage(benefit.getIndexCaption(), null, LocaleContextHolder.getLocale()));
        return benefitDto;
    }
}
