package com.zlw.onlinebookshop.service;

import com.zlw.onlinebookshop.dao.UserDao;
import com.zlw.onlinebookshop.entity.Books;
import com.zlw.onlinebookshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zlw
 * @create 2019-03-28 11:41
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User login(User user){


        user=this.userDao.login(user);
        return user;
    }
    public boolean register(User user){

        return userDao.register(user.getUName(),user.getUPassword(),user.getUEmail());
    }

    public boolean checkUser(String UName,User user){
        user=userDao.checkUser(UName);
        if (user != null) {
            return false;
        }else{
            return true;
        }

    }

    public List<Books> showBooklist(Map param) {
        return userDao.showBooklist(param);
    }

    public int booklistCount(Map param) {
          return userDao.booklistCount(param);
    }
}
