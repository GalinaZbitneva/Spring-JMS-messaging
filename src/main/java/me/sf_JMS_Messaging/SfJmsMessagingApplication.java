package me.sf_JMS_Messaging;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfJmsMessagingApplication {

	public static void main(String[] args) throws Exception {
		//настройка хранилища Artemis
		Configuration config = new ConfigurationImpl()
				.setPersistenceEnabled(false)
				.setSecurityEnabled(false)
				.setJournalDirectory("target/data/journal")
				.addAcceptorConfiguration("invm", "vm://0");
		//Если ваш клиент и сервер находятся внутри одной Java-машины (JVM),
		// вместо tcp:// часто используют vm://0. Это работает быстрее, так как данные передаются
		// через память, минуя сетевой стек операционной системы.


		//дополнительные настройки
		config.setJournalFileSize(10 * 1024 * 1024); // 10 MB
		config.setJournalMinFiles(2);


		//определяем сервер
		ActiveMQServer server = ActiveMQServers.newActiveMQServer(config);

		server.start();


		SpringApplication.run(SfJmsMessagingApplication.class, args);
	}

}
