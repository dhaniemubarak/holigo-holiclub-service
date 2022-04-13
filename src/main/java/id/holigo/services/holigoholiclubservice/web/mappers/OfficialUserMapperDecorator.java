package id.holigo.services.holigoholiclubservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigoholiclubservice.domain.OfficialUser;
import id.holigo.services.holigoholiclubservice.web.model.OfficialUserDto;

public abstract class OfficialUserMapperDecorator implements OfficialUserMapper {

    @Autowired
    private MessageSource messageSource;

    private OfficialUserMapper officialUserMapper;

    @Autowired
    public void setOfficialUserMapper(OfficialUserMapper officialUserMapper) {
        this.officialUserMapper = officialUserMapper;
    }

    public OfficialUserDto officialUserToUfficialUserDto(OfficialUser officialUser) {
        OfficialUserDto officialUserDto = officialUserMapper.officialUserToUfficialUserDto(officialUser);
        officialUserDto.setCaption(messageSource.getMessage(officialUser.getIndexCaption(),
                new Object[] { officialUser.getValueCaption() }, LocaleContextHolder.getLocale()));
        return officialUserDto;
    }

}
