package com.mycompany.RestDbAPI.repositories;

import com.mycompany.RestDbAPI.daointerfaces.UserDao;
import com.mycompany.RestDbAPI.model.User;
import com.mycompany.RestDbAPI.model.info.UserInfo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public User addUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional(readOnly=true)
    public List<UserInfo> findAll() {
        String sql = "Select new " + UserInfo.class.getName() 
                + "(u.id, u.login, u.password) " 
                + " from " + User.class.getName() + " u ";
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery(sql, UserInfo.class).getResultList();
    }

    @Override
    @Transactional(readOnly=true)
    public User findById(Long id) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User u where id = :parId")
                .setParameter("parId", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public User findByIdWithDetail(Long id) {
        return (User) sessionFactory.getCurrentSession()
                .getNamedQuery("User.findByIdWithDetail")
                .setParameter("idres", id).uniqueResult();
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
