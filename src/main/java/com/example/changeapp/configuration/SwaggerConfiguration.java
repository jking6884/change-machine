package com.example.changeapp.configuration;

import com.example.changeapp.ChangeappApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.text.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    Environment environment;

    private boolean enableSwagger = true;

    @Bean
    public Docket initializeSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Change")
                .apiInfo(apiInfo())
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.changeapp"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Change API")
                .description("A RESTful web service to convert money to change.")
                .contact("Version:  " + ChangeappApplication.class.getPackage().getImplementationVersion())
                .version(ChangeappApplication.class.getPackage().getImplementationVersion())
                .build();
    }
}
