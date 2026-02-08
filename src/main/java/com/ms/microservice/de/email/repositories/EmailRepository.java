package com.ms.microservice.de.email.repositories;

import com.ms.microservice.de.email.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
