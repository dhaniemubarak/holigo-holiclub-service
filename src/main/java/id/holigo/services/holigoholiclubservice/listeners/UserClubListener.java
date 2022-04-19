package id.holigo.services.holigoholiclubservice.listeners;

import id.holigo.services.common.events.CreateHoliclubEvent;
import id.holigo.services.holigoholiclubservice.web.mappers.UserClubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import id.holigo.services.common.events.HoliclubEvent;
import id.holigo.services.holigoholiclubservice.config.JmsConfig;
import id.holigo.services.holigoholiclubservice.services.HoliclubService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserClubListener {

    @Autowired
    private final HoliclubService holiclubService;

    @Autowired
    private final UserClubMapper userClubMapper;

    @JmsListener(destination = JmsConfig.INCREMENT_USERCLUB_BY_USER_ID_QUEUE)
    public void listenForIncrementUserClub(HoliclubEvent holiclubEvent) {
        holiclubService.incrementUserClubExp(holiclubEvent.getIncrementUserClubDto());
    }

    @JmsListener(destination = JmsConfig.CREATE_USER_GROUP)
    public void listenForCreateUserClub(CreateHoliclubEvent createHoliclubEvent) {
        holiclubService.createUserClub(userClubMapper.userClubDtoToUserClub(createHoliclubEvent.getUserClubDto()));
    }
}
