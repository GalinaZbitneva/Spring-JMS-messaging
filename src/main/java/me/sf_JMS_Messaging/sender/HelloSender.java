package me.sf_JMS_Messaging.sender;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.sf_JMS_Messaging.config.JmsConfig;
import me.sf_JMS_Messaging.model.HelloWorldMessage;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    //fixedRate = 2000 ml seconds   это 2 секунды


    @Scheduled(fixedRate = 2000)
    public void sendMessage(){

        //помеченные  @Scheduled подхватываются  TaskExecutor в TaskConfig
        System.out.println("I'm sending a message");

        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("World! World! World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE,message);

        System.out.println("Message sent!!!");



    }

}
