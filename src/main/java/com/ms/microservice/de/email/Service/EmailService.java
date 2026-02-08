package com.ms.microservice.de.email.Service;

import com.ms.microservice.de.email.dtos.EmailRecordDTO;
import com.ms.microservice.de.email.enums.StatusEmail;
import com.ms.microservice.de.email.mappers.EmailMapper;
import com.ms.microservice.de.email.model.EmailModel;
import com.ms.microservice.de.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Data
public class EmailService {
    private final EmailModel model;
    private final EmailMapper mapper;
    private final EmailRepository emailRepository;
    private String emailFrom;
    private final JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")

    @Transactional
    public EmailModel sendEmail(EmailRecordDTO dto) {
        EmailModel emailModel = mapper.ToModel(dto);

        try {

            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            emailSender.send(createMessage(emailModel));

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);

        }

        return emailRepository.save(emailModel);
    }

    // Método auxiliar para isolar o "mapeamento à mão" do e-mail
    private SimpleMailMessage createMessage(EmailModel model) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(model.getEmailTo());
        message.setSubject(model.getSubject());
        message.setText(model.getText());
        message.setFrom(model.getEmailFrom()); // Boa prática definir o From na mensagem também
        return message;
    }
}
