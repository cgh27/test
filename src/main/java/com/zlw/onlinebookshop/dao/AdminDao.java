package com.zlw.onlinebookshop.dao;

import com.zlw.onlinebookshop.entity.Admin;
import com.zlw.onlinebookshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zlw
 * @create 2019-03-29 11:49
 */
@Mapper
@Repository
public interface AdminDao {
    //登陆
    public Admin login(Admin admin);

}
