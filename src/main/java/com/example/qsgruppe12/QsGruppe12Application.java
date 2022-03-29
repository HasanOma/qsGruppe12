package com.example.qsgruppe12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
public class QsGruppe12Application {

    public static void main(String[] args) {
        SpringApplication.run(QsGruppe12Application.class, args);
    }

}
