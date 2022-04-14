package id.holigo.services.holigoholiclubservice.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import id.holigo.services.common.events.UserGroupEvent;
import id.holigo.services.common.model.UpdateUserGroupDto;
import id.holigo.services.holigoholiclubservice.config.JmsConfig;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Override
    public void updateUserGroup(UpdateUserGroupDto updateUserGroupDto) {
        jmsTemplate.convertAndSend(JmsConfig.UPDATE_USER_GROUP_IN_USER_QUEUE, new UserGroupEvent(updateUserGroupDto));
    }

}
