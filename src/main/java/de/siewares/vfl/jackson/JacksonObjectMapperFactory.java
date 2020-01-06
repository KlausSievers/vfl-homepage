/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sievers
 */
public class JacksonObjectMapperFactory {
  
  public ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new DateTimeModule());
    
    return mapper;
  }
  
}
