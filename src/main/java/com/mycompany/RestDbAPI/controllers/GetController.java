package com.mycompany.RestDbAPI.controllers;

import com.mycompany.RestDbAPI.daointerfaces.ArticleDao;
import com.mycompany.RestDbAPI.daointerfaces.CommentDao;
import com.mycompany.RestDbAPI.daointerfaces.UserDao;
import com.mycompany.RestDbAPI.model.info.ArticleInfo;
import com.mycompany.RestDbAPI.model.info.CommentInfo;
import com.mycompany.RestDbAPI.model.info.UserInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/get")
public class GetController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfo> getUsers() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleInfo> getArticles() {
        return articleDao.findAll();
    }

    @RequestMapping(value = "/comment", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentInfo> getComments() {
        return commentDao.findAll();
    }
    
    //to add other methods
}
