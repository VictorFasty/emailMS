package com.ms.microservice.de.email.consumers;


import com.ms.microservice.de.email.Service.EmailService;
import com.ms.microservice.de.email.dtos.EmailRecordDTO;
import com.ms.microservice.de.email.mappers.EmailMapper;
import com.ms.microservice.de.email.model.EmailModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    private EmailMapper mapper;

    @Autowired
    private EmailService service;

    //Ouvinte que vai consumir as mensagens dessa fila
    @RabbitListener(queues = "${broker.queue.email.name}")
    public EmailModel sendEmail(@Payload EmailRecordDTO emailRecordDTO){
        return service.sendEmail(emailRecordDTO);
    }

}
