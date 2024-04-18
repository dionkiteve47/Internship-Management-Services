package com.dali.security;


import com.dali.security.Service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class SecurityApplication implements CommandLineRunner {
    @Autowired
    AuthenticationService s;




    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
       // s.register(new RegisterRequest("trabelsi","dali","dali.trabelsi@gmailc.com","dali1234"));
       // AuthenticationResponse a= s.authenticate(new AuthenticationRequest("dali.trabelsi@gmailc.com","dali1234"));
       // log.info(""+a);

    }
}
