package com.example.airlines.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Bean
    public Docket swaggerSpringfoxDocket() {
     //   log.debug("Starting Swagger");

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .pathMapping("/")
            .apiInfo(ApiInfo.DEFAULT)
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(Pageable.class)
            .ignoredParameterTypes(java.sql.Date.class)
            .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
            .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
            .useDefaultResponseMessages(false)
            .globalOperationParameters(
                    Arrays.asList(new ParameterBuilder()
                        .name("Accept-Language")
                        .description("Accept-Language")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build(),new ParameterBuilder()
                        .name("Content-Type")
                        .description("Content-Type")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build()));

        docket = docket.select()
            .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
            .build();
        return docket;
    }

}

