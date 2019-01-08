package com.mycompany.RestDbAPI.repositories;

import com.mycompany.RestDbAPI.daointerfaces.CommentDao;
import com.mycompany.RestDbAPI.model.Article;
import com.mycompany.RestDbAPI.model.Comment;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("commentDao")
public class CommentDaoImpl implements CommentDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Comment addUpdate(Comment comment) {
        sessionFactory.getCurrentSession().saveOrUpdate(comment);
        return comment;
    }
/*CommentInfo(Long id, Long article, String author, String text)*/
    @Override
    public List<CommentInfo> findAll() {
        String sql = "Select new " + CommentInfo.class.getName() 
                + "(c.id, c.article.id, c.author.login, c.text) " 
                + " from " + Comment.class.getName() + " c ";
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery(sql, CommentInfo.class).getResultList();
    }

    @Override
    public Comment findById(Long id) {
        return (Comment) sessionFactory.getCurrentSession()
                .createQuery("from Comment c where id = :parId")
                .setParameter("parId", id).uniqueResult();
    }

    @Override
    public Comment findByIdWithDetail(Long id) {
        return null;
    }

    @Override
    public void delete(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }
    
}
