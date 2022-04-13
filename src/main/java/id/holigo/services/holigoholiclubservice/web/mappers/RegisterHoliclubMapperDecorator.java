package id.holigo.services.holigoholiclubservice.web.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigoholiclubservice.domain.RegisterHoliclub;
import id.holigo.services.holigoholiclubservice.web.model.RegisterHoliclubDto;

public abstract class RegisterHoliclubMapperDecorator implements RegisterHoliclubMapper {

    @Autowired
    private MessageSource messageSource;

    private RegisterHoliclubMapper registerHoliclubMapper;

    private RegisterBenefitMapper registerBenefitMapper;

    private RegisterOfficialAccountMapper registerOfficialAccountMapper;

    @Autowired
    public void setRegisterHoliclubMapper(RegisterHoliclubMapper registerHoliclubMapper) {
        this.registerHoliclubMapper = registerHoliclubMapper;
    }

    @Autowired
    public void setRegisterBenefitMapper(RegisterBenefitMapper registerBenefitMapper) {
        this.registerBenefitMapper = registerBenefitMapper;
    }

    @Autowired
    public void setRegisterOfficialAccountMapper(RegisterOfficialAccountMapper registerOfficialAccountMapper) {
        this.registerOfficialAccountMapper = registerOfficialAccountMapper;
    }

    public RegisterHoliclubDto registerHoliclubToRegisterHoliclubDto(RegisterHoliclub registerHoliclub) {
        RegisterHoliclubDto registerHoliclubDto = registerHoliclubMapper
                .registerHoliclubToRegisterHoliclubDto(registerHoliclub);
        registerHoliclubDto.setTitle(
                messageSource.getMessage(registerHoliclub.getIndexTitle(), null, LocaleContextHolder.getLocale()));
        registerHoliclubDto.setSubtitle(
                messageSource.getMessage(registerHoliclub.getIndexSubtitle(), null, LocaleContextHolder.getLocale()));
        registerHoliclubDto.setBenefit(registerHoliclub.getBenefit().stream()
                .map(registerBenefitMapper::registerBenefitToRegisterBenefitDto).collect(Collectors.toList()));
        registerHoliclubDto.setOfficialAccount(registerOfficialAccountMapper
                .registerOfficialAccountToRegisterOfficialAccountDto(registerHoliclub.getOfficialAccount()));
        return registerHoliclubDto;
    }
}
