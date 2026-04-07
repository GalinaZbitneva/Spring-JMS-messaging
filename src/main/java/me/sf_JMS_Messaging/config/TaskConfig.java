package me.sf_JMS_Messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//Аннотация @EnableScheduling в Spring Framework используется для включения механизма планирования задач
// (scheduling) в вашем приложении. Она заставляет Spring искать методы, отмеченные аннотацией @Scheduled
// , и выполнять их в фоновом режиме

@EnableScheduling
@EnableAsync
@Configuration
public class TaskConfig {

    @Bean
    TaskExecutor taskExecutor(){
        //SimpleAsyncTaskExecutor — это простейшая реализация интерфейса TaskExecutor
        // в Spring Framework, которая создает новый поток для каждой задачи, не используя пул потоков.
        return new SimpleAsyncTaskExecutor();
    }
}
