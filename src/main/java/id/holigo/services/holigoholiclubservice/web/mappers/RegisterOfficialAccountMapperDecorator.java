package id.holigo.services.holigoholiclubservice.web.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigoholiclubservice.domain.RegisterOfficialAccount;
import id.holigo.services.holigoholiclubservice.web.model.RegisterOfficialAccountDto;

public abstract class RegisterOfficialAccountMapperDecorator implements RegisterOfficialAccountMapper {

    @Autowired
    private MessageSource messageSource;

    private RegisterOfficialAccountMapper registerOfficialAccountMapper;

    private OfficialUserMapper officialUserMapper;

    @Autowired
    public void setRegisterOfficialAccountMapper(RegisterOfficialAccountMapper registerOfficialAccountMapper) {
        this.registerOfficialAccountMapper = registerOfficialAccountMapper;
    }

    @Autowired
    public void setOfficialUserMapper(OfficialUserMapper officialUserMapper) {
        this.officialUserMapper = officialUserMapper;
    }

    public RegisterOfficialAccountDto registerOfficialAccountToRegisterOfficialAccountDto(
            RegisterOfficialAccount registerOfficialAccount) {
        RegisterOfficialAccountDto registerOfficialAccountDto = registerOfficialAccountMapper
                .registerOfficialAccountToRegisterOfficialAccountDto(registerOfficialAccount);
        registerOfficialAccountDto.setTitle(messageSource.getMessage(registerOfficialAccount.getIndexTitle(), null,
                LocaleContextHolder.getLocale()));
        registerOfficialAccountDto
                .setSubtitle(messageSource.getMessage(registerOfficialAccount.getIndexSubtitle(), null,
                        LocaleContextHolder.getLocale()));
        registerOfficialAccountDto.setButtonLabel(messageSource.getMessage(registerOfficialAccount.getButtonLabel(),
                null, LocaleContextHolder.getLocale()));
        registerOfficialAccountDto.setOfficialUsers(registerOfficialAccount.getOfficialUsers().stream()
                .map(officialUserMapper::officialUserToUfficialUserDto).collect(Collectors.toList()));
        return registerOfficialAccountDto;
    }
}
