/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.siewares.vfl.jackson.JacksonObjectMapperFactory;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Sievers
 */
@Configuration
public class RestConfig {
  @Bean
  @ConditionalOnMissingBean(RestTemplate.class)
  public RestTemplate restTemplate() {
    ObjectMapper mapper = new JacksonObjectMapperFactory().createObjectMapper();
    final RestTemplate restTemplate = new RestTemplate();

    final List<HttpMessageConverter<?>> converters = new ArrayList<>();
    final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    jsonConverter.setObjectMapper(mapper);
    converters.add(jsonConverter);

    final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    converters.add(stringConverter);
    restTemplate.setMessageConverters(converters);
    return restTemplate;
  }
}
