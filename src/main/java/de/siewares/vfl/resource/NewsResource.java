/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Category;
import de.siewares.vfl.entity.News;
import de.siewares.vfl.entity.Team;
import de.siewares.vfl.entity.User;
import de.siewares.vfl.service.NewsService;
import de.siewares.vfl.service.TeamService;
import de.siewares.vfl.service.UserService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Klaus
 */
@RestController
public class NewsResource {

  @Autowired
  private NewsService service;

  @Autowired
  private TeamService teamService;
  
  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/news", method = RequestMethod.POST)
  void save(@RequestBody News news) {
    User user = userService.get();
    news.setEditor(user);
    service.save(news);
  }

  @RequestMapping(value = "/news/upload", method = RequestMethod.POST)
  Map<String, String> uploadFile(@RequestParam(value = "file", required = true) MultipartFile file) throws FileNotFoundException, IOException {
      return Collections.singletonMap("path", service.saveFile(file)) ;
  }

  @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
  void delete(@PathVariable("id") int id) {
    service.delete(id);
  }

  @RequestMapping(value = "/news/team/{shortName}", method = RequestMethod.GET)
  List<News> getNewsByTeam(@PathVariable("shortName") String shortName) {
    Team team = this.teamService.getByShortName(shortName);
    if (team != null) {
      return this.service.getByCategory(team.getCategory());
    } else {
      return Collections.<News>emptyList();
    }
  }
  
    @RequestMapping(value = "/news/category/{id}", method = RequestMethod.GET)
  List<News> getNewsByCategory(@PathVariable("id") Integer categoryId) {
    Category cat = new  Category(categoryId);
    return this.service.getByCategory(cat);
  }
}
