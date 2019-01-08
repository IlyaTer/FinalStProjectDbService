package com.mycompany.RestDbAPI.daointerfaces;

import com.mycompany.RestDbAPI.model.Comment;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import java.util.List;


public interface CommentDao {
    
    Comment addUpdate(Comment comment);
    
    List<CommentInfo> findAll();
    
    Comment findById(Long id);
    
    Comment findByIdWithDetail(Long id);
    
    void delete(Comment comment);
}
