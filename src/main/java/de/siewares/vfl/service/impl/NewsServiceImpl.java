/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Category;
import de.siewares.vfl.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import de.siewares.vfl.repository.NewsRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import de.siewares.vfl.service.NewsService;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Klaus
 */
@Service
public class NewsServiceImpl implements NewsService {

  @Autowired
  private NewsRepository newsRepository;

  @Override
  public void save(News news) {
    newsRepository.save(news);
  }

  @Override
  public void delete(int id) {
    newsRepository.delete(id);
  }

  @Override
  public List<News> getByCategory(Category category) {
    return newsRepository.findByCategoryOrderByInsertDateDesc(category);
  }

  @Override
  public String saveFile(MultipartFile file) throws FileNotFoundException, IOException {
    String path = "container/" + file.getOriginalFilename();
    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("public/"+path));
    stream.write(file.getBytes());
    stream.close();

    return path;
  }

}
