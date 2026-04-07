package me.sf_JMS_Messaging.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.JacksonJsonMessageConverter;
import org.springframework.util.MimeTypeUtils;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.json.JsonMapper;

import tools.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;


@Configuration
public class JmsConfig {

    public final static String MY_QUEUE = "my-hello-world";

    // 1. Создаем маппер
    JsonMapper mapper = JsonMapper.builder()
            .activateDefaultTypingAsProperty(
                    new DefaultBaseTypeLimitingValidator(),
                    DefaultTyping.NON_FINAL,
                    "_type" // ВОТ ОНО! Третий аргумент заменяет setTypeIdPropertyName
            )
            .build();




@Bean
    public JacksonJsonMessageConverter messageConverter(){

        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter(mapper);
        converter.setContentTypeResolver(message -> MimeTypeUtils.APPLICATION_JSON);
        return converter;

    }
}
