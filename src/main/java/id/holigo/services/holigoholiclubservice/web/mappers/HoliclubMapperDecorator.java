package id.holigo.services.holigoholiclubservice.web.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.common.model.UserGroupEnum;
import id.holigo.services.holigoholiclubservice.domain.Holiclub;
import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.web.model.HoliclubDto;
import id.holigo.services.holigoholiclubservice.web.model.UserGroupDto;
import id.holigo.services.holigoholiclubservice.web.model.WelcomeDto;

public abstract class HoliclubMapperDecorator implements HoliclubMapper {

    @Autowired
    private MessageSource messageSource;

    private HoliclubMapper holiclubMapper;

    private UserClubMapper userClubMapper;

    private UserGroupMapper userGroupMapper;

    @Autowired
    public void setHoliclubMapper(HoliclubMapper holiclubMapper) {
        this.holiclubMapper = holiclubMapper;
    }

    @Autowired
    public void setUserClubMapper(UserClubMapper userClubMapper) {
        this.userClubMapper = userClubMapper;
    }

    @Autowired
    public void setUserGroupMapper(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    public HoliclubDto holiclubToHoliclubDto(Holiclub holiclub, UserClub userClub) {
        HoliclubDto holiclubDto = holiclubMapper.holiclubToHoliclubDto(holiclub, userClub);
        if (userClub != null) {
            holiclubDto.setUserClub(userClubMapper.userClubToUserClubDto(userClub));
        }

        List<UserGroupDto> userGroups = new ArrayList<>();
        holiclub.getUserGroups().stream().map(userGroupMapper::userGroupToUserGroupDto).forEach(userGroupDto -> {
            String caption = messageSource.getMessage("user_group.lt", null, LocaleContextHolder.getLocale());
            if (userClub != null) {
                if (Objects.equals(userClub.getUserGroup().getCode(), userGroupDto.getUserGroup().getCode())) {
                    String next = "";
                    switch (userClub.getUserGroup().getCode()) {
                        case 200 -> next = "BossQiu";
                        case 300 -> next = "Soeltan";
                        case 400 -> next = "Crazy Rich";
                    }
                    int remaining = userGroupDto.getMaxExp() - userClub.getExp();
                    LocalDateTime localDateTime = LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), 12, 31,
                            23,
                            59,
                            59);
                    Object[] args = new Object[]{remaining,
                            localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), next};
                    caption = messageSource.getMessage("user_group.eq", args,
                            LocaleContextHolder.getLocale());
                } else if (userClub.getUserGroup().getCode() > userGroupDto.getUserGroup().getCode()) {
                    caption = messageSource.getMessage("user_group.gt", null,
                            LocaleContextHolder.getLocale());
                } else {
                    int remaining = userGroupDto.getMaxExp() - userClub.getExp();
                    caption = messageSource.getMessage("user_group.lt", new Object[]{remaining
                            },
                            LocaleContextHolder.getLocale());
                }
            } else {
                if (userGroupDto.getUserGroup() == UserGroupEnum.MEMBER) {
                    Integer remaining = userGroupDto.getMaxExp();
                    LocalDateTime localDateTime = LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), 12, 31,
                            23,
                            59,
                            59);
                    Object[] args = new Object[]{remaining,
                            localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))};
                    caption = messageSource.getMessage("user_group.eq", args,
                            LocaleContextHolder.getLocale());
                } else {
                    Integer remaining = userGroupDto.getMinExp();
                    caption = messageSource.getMessage("user_group.lt", new Object[]{remaining},
                            LocaleContextHolder.getLocale());
                }

            }
            userGroupDto.setCaption(caption);
            userGroups.add(userGroupDto);
        });
        holiclubDto.setUserGroups(userGroups);
        holiclubDto.setWelcome(welcomeBuilder(userClub));
        holiclubDto.setCaption(
                messageSource.getMessage(holiclub.getIndexCaption(), null, LocaleContextHolder.getLocale()));
        return holiclubDto;
    }

    @Override
    public Holiclub holiclubDtoToHoliclub(HoliclubDto holiclubDto) {
        return holiclubMapper.holiclubDtoToHoliclub(holiclubDto);
    }

    private WelcomeDto welcomeBuilder(UserClub userClub) {
        WelcomeDto welcomeDto = null;
        if (userClub == null) {
            welcomeDto = WelcomeDto.builder()
                    .backgroundUrl(
                            "https://ik.imagekit.io/holigo/holiclub/background_without_tier__BCBjBFzW.png?ik-sdk-version=javascript-1.4.3")
                    .imageUrl(
                            "https://ik.imagekit.io/holigo/holiclub/holiclub_x3kX-mpyW.png?ik-sdk-version=javascript-1.4.3")
                    .title(messageSource.getMessage("welcome.1.title", null, LocaleContextHolder.getLocale()))
                    .subtitle(messageSource.getMessage("welcome.1.subtitle", null, LocaleContextHolder.getLocale()))
                    .build();
        } else if (userClub.getOpenAt() == null) {
            String title;
            String subtitle = messageSource.getMessage("welcome.2.subtitle", null, LocaleContextHolder.getLocale());
            title = switch (userClub.getUserGroup()) {
                case NETIZEN -> messageSource.getMessage("welcome.2.title", new Object[]{"Netizen"},
                        LocaleContextHolder.getLocale());
                case BOSSQIU -> messageSource.getMessage("welcome.2.title", new Object[]{"BossQiu"},
                        LocaleContextHolder.getLocale());
                case SOELTAN -> messageSource.getMessage("welcome.2.title", new Object[]{"Soeltan"},
                        LocaleContextHolder.getLocale());
                case CRAZY_RICH -> messageSource.getMessage("welcome.2.title", new Object[]{"Crazy Rich"},
                        LocaleContextHolder.getLocale());
                default -> messageSource.getMessage("welcome.2.title", new Object[]{"Member"},
                        LocaleContextHolder.getLocale());
            };
            welcomeDto = WelcomeDto.builder()
                    .backgroundUrl(
                            "https://ik.imagekit.io/holigo/holiclub/background_without_tier__BCBjBFzW.png?ik-sdk-version=javascript-1.4.3")
                    .imageUrl(
                            "https://ik.imagekit.io/holigo/holiclub/holiclub_x3kX-mpyW.png?ik-sdk-version=javascript-1.4.3")
                    .title(title)
                    .subtitle(subtitle)
                    .build();

        }
        return welcomeDto;
    }

}
