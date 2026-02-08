package com.ms.microservice.de.email.mappers;

import com.ms.microservice.de.email.dtos.EmailRecordDTO;
import com.ms.microservice.de.email.model.EmailModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailModel ToModel(EmailRecordDTO dto);
    EmailRecordDTO ToDto(EmailModel model);
}
