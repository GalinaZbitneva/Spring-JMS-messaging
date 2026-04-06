package me.sf_JMS_Messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable {
    //Serializable в Java — это интерфейс-маркер (java.io.Serializable),
    // который сообщает виртуальной машине (JVM), что объекты данного класса
    // могут быть превращены в последовательность байтов.

    //из документации Serializable был 42 потом сделала randomy change подсказка лампочки
    static final long serialVersionUID = 1546803509604006656L;

    private UUID id;
    private String message;
}
