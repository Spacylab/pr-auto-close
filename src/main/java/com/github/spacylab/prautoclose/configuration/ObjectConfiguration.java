package com.github.spacylab.prautoclose.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        System.out.println("ObjectConfiguration.objectMapperBuilder");
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        return builder;
    }
}
