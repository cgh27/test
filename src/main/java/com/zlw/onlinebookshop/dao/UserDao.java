package com.zlw.onlinebookshop.dao;

import com.zlw.onlinebookshop.entity.Books;
import com.zlw.onlinebookshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zlw
 * @create 2019-03-28 11:34
 */
@Mapper
@Repository
public interface UserDao {
    //用户登陆
     public User login(User user);

    //用户注册
    boolean register(String UNamem,String UPassword,String UEmail);

    public User checkUser(String name);


    public List<Books> showBooklist(Map param);

    public int booklistCount(Map param);
}
