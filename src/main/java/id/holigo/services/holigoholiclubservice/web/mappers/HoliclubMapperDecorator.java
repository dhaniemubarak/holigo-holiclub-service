package id.holigo.services.holigoholiclubservice.web.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import id.holigo.services.holigoholiclubservice.domain.Holiclub;
import id.holigo.services.holigoholiclubservice.domain.UserClub;
import id.holigo.services.holigoholiclubservice.web.model.HoliclubDto;
import id.holigo.services.holigoholiclubservice.web.model.UserGroupDto;
import id.holigo.services.holigoholiclubservice.web.model.WelcomeDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class HoliclubMapperDecorator implements HoliclubMapper {

    @Autowired
    private MessageSource messageSource;

    private HoliclubMapper holiclubMapper;

    private UserClubMapper userClubMapper;

    private UserGroupMapper userGroupMapper;

    WelcomeDto welcomeDto;

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

        // List<UserGroupDto> userGroups = new ArrayList<>();
        // holiclub.getUserGroups().stream().forEach(userGroup -> {
        // UserGroupDto userGroupDto =
        // userGroupMapper.userGroupToUserGroupDto(userGroup);
        // String caption = null;
        // if (userClub.getUserGroup().getCode() == userGroup.getUserGroup().getCode())
        // {
        // Integer remaining = userGroup.getMaxExp() - userClub.getExp();
        // LocalDateTime localDateTime = LocalDateTime.of(Calendar.YEAR, 12, 31, 23, 59,
        // 59);
        // Object[] args = new Object[] { remaining,
        // localDateTime.format(DateTimeFormatter.ofPattern("yyyy-mm-dd")) };
        // caption = messageSource.getMessage("user_group.eq", args,
        // LocaleContextHolder.getLocale());
        // } else if (userClub.getUserGroup().getCode() >
        // userGroup.getUserGroup().getCode()) {
        // caption = messageSource.getMessage("user_group.gt", null,
        // LocaleContextHolder.getLocale());
        // } else {
        // Integer remaining = userGroup.getMaxExp() - userClub.getExp();
        // caption = messageSource.getMessage("user_group.lt", new Object[] { remaining
        // },
        // LocaleContextHolder.getLocale());
        // }
        // userGroupDto.setCaption(caption);
        // userGroups.add(userGroupDto);
        // });

        List<UserGroupDto> userGroups = new ArrayList<>();
        holiclub.getUserGroups().stream().map(userGroupMapper::userGroupToUserGroupDto).forEach(userGroupDto -> {

            log.info("userGroupDto -> {}", userGroupDto);

            String caption = null;
            if (userClub.getUserGroup().getCode() == userGroupDto.getUserGroup().getCode()) {
                Integer remaining = userGroupDto.getMaxExp() - userClub.getExp();
                LocalDateTime localDateTime = LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), 12, 31, 23,
                        59,
                        59);
                Object[] args = new Object[] { remaining,
                        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd")) };
                caption = messageSource.getMessage("user_group.eq", args,
                        LocaleContextHolder.getLocale());
            } else if (userClub.getUserGroup().getCode() > userGroupDto.getUserGroup().getCode()) {
                caption = messageSource.getMessage("user_group.gt", null,
                        LocaleContextHolder.getLocale());
            } else {
                Integer remaining = userGroupDto.getMinExp() - userClub.getExp();
                caption = messageSource.getMessage("user_group.lt", new Object[] { remaining
                },
                        LocaleContextHolder.getLocale());
            }
            userGroupDto.setCaption(caption);
            userGroups.add(userGroupDto);
        });

        // holiclub.getUserGroups().forEach(userGroupDto -> {
        // String caption = null;
        // if (userClub.getUserGroup().getCode() ==
        // userGroupDto.getUserGroup().getCode()) {
        // Integer remaining = userGroupDto.getMaxExp() - userClub.getExp();
        // LocalDateTime localDateTime = LocalDateTime.of(Calendar.YEAR, 12, 31, 23, 59,
        // 59);
        // Object[] args = new Object[] { remaining,
        // localDateTime.format(DateTimeFormatter.ofPattern("yyyy-mm-dd")) };
        // caption = messageSource.getMessage("user_group.eq", args,
        // LocaleContextHolder.getLocale());
        // } else if (userClub.getUserGroup().getCode() >
        // userGroupDto.getUserGroup().getCode()) {
        // caption = messageSource.getMessage("user_group.gt", null,
        // LocaleContextHolder.getLocale());
        // } else {
        // Integer remaining = userGroupDto.getMaxExp() - userClub.getExp();
        // caption = messageSource.getMessage("user_group.lt", new Object[] { remaining
        // },
        // LocaleContextHolder.getLocale());
        // }

        // });

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
        if (userClub == null) {
            welcomeDto = WelcomeDto.builder()
                    .backgroundUrl(
                            "https://ik.imagekit.io/holigo/holiclub/background_without_tier__BCBjBFzW.png?ik-sdk-version=javascript-1.4.3")
                    .imageUrl(
                            "https://ik.imagekit.io/holigo/holiclub/holiclub_x3kX-mpyW.png?ik-sdk-version=javascript-1.4.3")
                    .title(messageSource.getMessage("welcome.1.title", null, LocaleContextHolder.getLocale()))
                    .subtitle(messageSource.getMessage("welcome.1.subtitle", null, LocaleContextHolder.getLocale()))
                    .build();
            // } else if (!Optional.ofNullable(userClub.getOpenAt()).isPresent()) {
        } else if (userClub.getOpenAt() == null) {
            String title = null;
            String subtitle = messageSource.getMessage("welcome.2.subtitle", null, LocaleContextHolder.getLocale());
            switch (userClub.getUserGroup()) {
                case NETIZEN:
                    title = messageSource.getMessage("welcome.2.title", new Object[] { "Netizen" },
                            LocaleContextHolder.getLocale());
                    break;
                case BOSSQIU:
                    title = messageSource.getMessage("welcome.2.title", new Object[] { "Bossqiu" },
                            LocaleContextHolder.getLocale());
                    break;
                case SOELTAN:
                    title = messageSource.getMessage("welcome.2.title", new Object[] { "Soeltan" },
                            LocaleContextHolder.getLocale());
                    break;
                case CRAZY_RICH:
                    title = messageSource.getMessage("welcome.2.title", new Object[] { "Crazy Rich" },
                            LocaleContextHolder.getLocale());
                    break;
                default:
                    title = messageSource.getMessage("welcome.2.title", new Object[] { "Member" },
                            LocaleContextHolder.getLocale());

                    break;
            }
            welcomeDto = WelcomeDto.builder()
                    .backgroundUrl(
                            "https://ik.imagekit.io/holigo/holiclub/background_without_tier__BCBjBFzW.png?ik-sdk-version=javascript-1.4.3")
                    .imageUrl(
                            "https://ik.imagekit.io/holigo/holiclub/holiclub_x3kX-mpyW.png?ik-sdk-version=javascript-1.4.3")
                    .title(title)
                    .subtitle(subtitle)
                    .build();

        } else {
            welcomeDto = null;
        }
        return welcomeDto;
    }

}
