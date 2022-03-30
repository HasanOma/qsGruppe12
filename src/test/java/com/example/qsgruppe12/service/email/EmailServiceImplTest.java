//package com.example.qsgruppe12.service.email;
//
//import com.example.qsgruppe12.model.Email;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class EmailServiceImplTest {
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
//    public void setUp() throws Exception{
//        email = Email.builder()
//                .from("Ola@Nordmann.no")
//                .to("Medel@Svensson")
//                .message("Hei\nDu er nå registrert som bruker i QS!")
//                .subject("Registrert bruker")
//                .build();
//        Mockito.doNothing().when(javaMailSender).send(any(MimeMessage.class));
//    }
//
//    @Test
//    void sendEmail() throws MessagingException{
//        emailService.sendEmail(email);
//        verify(javaMailSender,times(1)).send(any(MimeMessage.class));
//    }
//}
