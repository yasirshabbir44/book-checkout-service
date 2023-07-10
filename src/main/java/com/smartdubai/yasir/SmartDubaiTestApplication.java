package com.smartdubai.yasir;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book Store", version = "1.0", description = "Assignment", termsOfService = "Please adhere to policies"))
public class SmartDubaiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartDubaiTestApplication.class, args);
    }

}
