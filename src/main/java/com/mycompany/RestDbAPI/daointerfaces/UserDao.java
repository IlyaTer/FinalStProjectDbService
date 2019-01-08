package com.mycompany.RestDbAPI.daointerfaces;

import com.mycompany.RestDbAPI.model.User;
import com.mycompany.RestDbAPI.model.info.UserInfo;
import java.util.List;

public interface UserDao {
    
    User addUpdate(User user);
    
    List<UserInfo> findAll();
    
    User findById(Long id);
    
    User findByIdWithDetail(Long id);
    
    void delete(User user);
}
