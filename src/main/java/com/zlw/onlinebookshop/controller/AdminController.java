package com.zlw.onlinebookshop.controller;

import com.zlw.onlinebookshop.entity.Admin;
import com.zlw.onlinebookshop.entity.User;
import com.zlw.onlinebookshop.service.AdminService;
import com.zlw.onlinebookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zlw
 * @create 2019-03-29 11:22
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private AdminService adminService;

    //跳转
    @GetMapping("index")
    public String adminindex() {
        return "admin/adminLogin";
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(Admin admin, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ModelAndView mav=new ModelAndView();

        String AName = request.getParameter("account");
        String APassword = request.getParameter("password");
        Admin admin2=new Admin();
        admin.setAPassword(APassword);
        admin.setAName(AName);
        admin2=adminService.login(admin);
        if (admin2!= null) {
            session.setAttribute("thisAdmin", admin);

            mav.setViewName("index");
            return mav;
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登陆失败");
        }
        mav.setViewName("admin/adminLogin");
        return mav;

//           List list =  jdbcTemplate.queryForList("SELECT * FROM user WHERE UName=? AND UPassword=?",UName,UPassword);
//
//
//            if (list.size()>0) {
//
//                response.sendRedirect("/templates/show.html");
//
//
//            } else {
//                System.out.println("error!");
//            }


    }
}

