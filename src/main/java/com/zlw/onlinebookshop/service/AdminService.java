package com.zlw.onlinebookshop.service;

import com.zlw.onlinebookshop.dao.AdminDao;
import com.zlw.onlinebookshop.dao.UserDao;
import com.zlw.onlinebookshop.entity.Admin;
import com.zlw.onlinebookshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zlw
 * @create 2019-03-29 11:48
 */
@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;

    public Admin login(Admin admin){

        admin=this.adminDao.login(admin);
        return admin;
    }

}
