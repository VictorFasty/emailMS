package com.ms.microservice.de.email.dtos;



public record EmailRecordDTO(
        Long userid,
        String emailTo,
        String subject,
        String text
) {
}
