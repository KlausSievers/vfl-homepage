/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.repository;

import de.siewares.vfl.entity.Category;
import de.siewares.vfl.entity.News;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Klaus
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
  List<News> findByCategoryOrderByInsertDateDesc(Category category);
}
