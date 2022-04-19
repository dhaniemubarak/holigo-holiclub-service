package id.holigo.services.holigoholiclubservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {
    public static final String INCREMENT_USERCLUB_BY_USER_ID_QUEUE = "increment-userclub-by-user-id";

    public static final String UPDATE_USER_GROUP_IN_USER_QUEUE = "update-user-group-in-user";

    public static final String CREATE_USER_GROUP = "create-user-group";

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
