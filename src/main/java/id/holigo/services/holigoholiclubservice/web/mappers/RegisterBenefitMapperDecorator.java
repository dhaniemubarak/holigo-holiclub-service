package id.holigo.services.holigoholiclubservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigoholiclubservice.domain.RegisterBenefit;
import id.holigo.services.holigoholiclubservice.web.model.RegisterBenefitDto;

public abstract class RegisterBenefitMapperDecorator implements RegisterBenefitMapper {

    @Autowired
    private MessageSource messageSource;

    private RegisterBenefitMapper registerBenefitMapper;

    @Autowired
    public void setRegisterBenefitMapper(RegisterBenefitMapper registerBenefitMapper) {
        this.registerBenefitMapper = registerBenefitMapper;
    }

    public RegisterBenefitDto registerBenefitToRegisterBenefitDto(RegisterBenefit registerBenefit) {
        RegisterBenefitDto registerBenefitDto = registerBenefitMapper
                .registerBenefitToRegisterBenefitDto(registerBenefit);
        registerBenefitDto.setCaption(
                messageSource.getMessage(registerBenefit.getIndexCaption(), null, LocaleContextHolder.getLocale()));
        return registerBenefitDto;
    }
}
