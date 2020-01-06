/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service;

import de.siewares.vfl.entity.Category;
import de.siewares.vfl.entity.News;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Klaus
 */
public interface NewsService {

  void delete(int id);

  List<News> getByCategory(Category category);

  void save(News news);
  
  String saveFile(MultipartFile file) throws FileNotFoundException, IOException;
  
}
