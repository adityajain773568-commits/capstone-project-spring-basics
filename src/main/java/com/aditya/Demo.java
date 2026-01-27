package com.aditya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@SpringBootApplication
@PropertySource(value="classpath:messages.properties")
public class CapstoneProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext container = SpringApplication.run(CapstoneProjectApplication.class, args);
        Service service = container.getBean(Service.class);


    }

}
