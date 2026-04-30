package me.sf_JMS_Messaging.listener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.sf_JMS_Messaging.config.JmsConfig;
import me.sf_JMS_Messaging.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;


    //String MY_QUEUE = "my-hello-world" from JmsConfig class
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message){

        System.out.println("I got a massage  Listener");
        System.out.println(helloWorldMessage);

    }

    // String MY_SEND_RCV_QUEUE = "receive-queue"
    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHELLO(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {

        HelloWorldMessage payloadMsg = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("WORLD Listener")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(),payloadMsg);

        System.out.println("I got a HELLO massage  Listener");
        System.out.println(helloWorldMessage);

    }


}
