package me.sf_JMS_Messaging.sender;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.sf_JMS_Messaging.config.JmsConfig;
import me.sf_JMS_Messaging.model.HelloWorldMessage;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    //fixedRate = 2000 ml seconds   это 2 секунды
    @Scheduled(fixedRate = 2000)
    public void sendMessage(){

        //помеченные  @Scheduled подхватываются  TaskExecutor в TaskConfig


        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("World! World! World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE,message);

    }

//
//    @Scheduled(fixedRate = 2000)
//    public void sendAndReceiveMessage() throws JMSException{
//
//        //помеченные  @Scheduled подхватываются  TaskExecutor в TaskConfig
//
//        HelloWorldMessage message = HelloWorldMessage.builder()
//                .id(UUID.randomUUID())
//                .message("HELLO")
//                .build();
//
//        Message receviedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                Message helloMessage = null;
//                try{
//                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
//                    helloMessage.setStringProperty("_type", "me/sf_JMS_Messaging/model/HelloWorldMessage");
//                    System.out.println("Sending HELLO");
//
//                    return helloMessage;
//
//                } catch (Exception e){
//                    throw  new JMSException("boom");
//
//                }
//            }
//        });
//
//
//        System.out.println(receviedMsg.getBody(String.class));
//    }
}
