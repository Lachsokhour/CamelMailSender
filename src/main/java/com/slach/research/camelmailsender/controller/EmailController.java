package com.slach.research.camelmailsender.controller;

import com.slach.research.camelmailsender.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

  private final EmailService emailService;

  public EmailController(EmailService emailService) {
    this.emailService = emailService;
  }

  @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> sendEmail(
    @RequestParam String to,
    @RequestParam(required = false) String subject,
    @RequestParam String body,
    @RequestParam MultipartFile file) {
    try {
      emailService.sendEmailWithInMemoryAttachment(
        to, subject, body, file.getBytes(), file.getOriginalFilename());
      return ResponseEntity.ok("Email sent successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to send email: " + e.getMessage());
    }
  }
}