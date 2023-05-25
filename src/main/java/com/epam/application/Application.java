package com.epam.application;

import com.epam.application.dto.InternDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Practise Application",
        version = "1.0",
        contact = @Contact(name = "Shasank",email = "shasank_gadipilli@epam.com")))
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    @Bean
    public TypeToken<List<InternDto>> getTypeTokenForInternDTO() {
        return new TypeToken<>() {
        };
    }

}
