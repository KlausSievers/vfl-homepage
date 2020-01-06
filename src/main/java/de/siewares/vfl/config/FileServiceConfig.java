/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.config;

import de.siewares.vfl.repository.FileSystemDocumentDao;
import de.siewares.vfl.service.ArchiveService;
import de.siewares.vfl.service.impl.ArchiveServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Sievers
 */
@Configuration
public class FileServiceConfig {

  @Bean
  ArchiveService archivService() {
    return new ArchiveServiceImpl(new FileSystemDocumentDao("archive"));
  }

  @Bean
  ArchiveService magazineService() {
    return new ArchiveServiceImpl(new FileSystemDocumentDao("magazine"));
  }
}
