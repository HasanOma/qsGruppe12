package com.example.qsgruppe12.service.email;

import com.example.qsgruppe12.model.Email;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class EmailServiceTest {
//
//    @SpyBean
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    @InjectMocks
//    private EmailServiceImpl emailService;
//
//    private Email email;
//
//    @BeforeEach
//    public void setUp() {
//        email = Email.builder()
//                .from("ola@nordmann.no")
//                .to("medel@svensson.se")
//                .message("Hei\nDu er nå registrert som bruker i QS!")
//                .subject("Registrert bruker")
//                .build();
//        //if you comment out this line you will see
//        //that a connection is established with google's gmail servers
//        //but as we dont have a gmail user for this project we are getting a bad credentials error when we reach them
//        //with a user this would work
////        Mockito.doNothing().when(javaMailSender).send(any(MimeMessage.class));
//    }
//
//    @Test
//    void sendEmail() throws MessagingException {
////        emailService.sendEmail(email);
////        verify(javaMailSender,times(1)).send(any(MimeMessage.class));
//    }
//
//    @Test
//    void sendMessageEmail() throws MessagingException {
////        String from= email.getFrom();
////        String to = email.getTo();
////        String subject = "Registrert bruker";
////        String message= "Hei\nDu er nå registrert som bruker i QS!";
////        emailService.sendEmail(from, to,subject,message);
////        verify(javaMailSender,times(1)).send(any(MimeMessage.class));
//    }
//}