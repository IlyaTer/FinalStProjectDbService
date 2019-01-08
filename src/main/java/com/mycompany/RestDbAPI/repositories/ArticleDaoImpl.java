package com.mycompany.RestDbAPI.repositories;

import com.mycompany.RestDbAPI.daointerfaces.ArticleDao;
import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.User;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import com.mycompany.RestDbAPI.model.info.UserInfo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Article addUpdate(Article article) {
        sessionFactory.getCurrentSession().saveOrUpdate(article);
        return article;
    }
/*ArticleInfo(Long id, String name, String topic, String text, String author)*/
    @Override
    @Transactional(readOnly=true)
    public List<ArticleInfo> findAll() {
        String sql = "Select new " + ArticleInfo.class.getName() 
                + "(a.id, a.name, a.topic, a.text, a.author.login) " 
                + " from " + Article.class.getName() + " a ";
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery(sql, ArticleInfo.class).getResultList();
    }

    @Override
    @Transactional(readOnly=true)
    public Article findById(Long id) {
        return (Article) sessionFactory.getCurrentSession()
                .createQuery("from Article a where id = :parId")
                .setParameter("parId", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public Article findByIdWithDetail(Long id) {
        return null;
    }

    @Override
    public void delete(Article article) {
        sessionFactory.getCurrentSession().delete(article);
    }
    
}
