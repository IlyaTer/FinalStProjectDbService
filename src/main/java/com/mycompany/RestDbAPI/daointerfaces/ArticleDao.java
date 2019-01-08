package com.mycompany.RestDbAPI.daointerfaces;

import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import java.util.List;


public interface ArticleDao {
    
    Article addUpdate(Article article);
    
    List<ArticleInfo> findAll();
    
    Article findById(Long id);
    
    Article findByIdWithDetail(Long id);
    
    void delete(Article article);
}
