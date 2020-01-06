/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.siewares.vfl.jackson.JacksonObjectMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Sievers
 */
@Configuration
public class JacksonConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    return new JacksonObjectMapperFactory().createObjectMapper();
  }
}
