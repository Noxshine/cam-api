package com.example.CAM.repository;

import com.example.CAM.domain.core.User.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface EmailRepository extends JpaRepository<Email, Long> {
}
