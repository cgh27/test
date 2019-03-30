package com.zlw.onlinebookshop.controller;

import com.zlw.onlinebookshop.entity.Books;
import com.zlw.onlinebookshop.entity.User;
import com.zlw.onlinebookshop.service.UserService;
import com.zlw.onlinebookshop.utils.LayerData;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zlw
 * @create 2019-03-28 11:33
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;
    //跳转
        @GetMapping("index")
        public String userindex(){
            return "userlogin";
        }


    //登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {


            ModelAndView mav = new ModelAndView();
            String UName = request.getParameter("name");
            String UPassword = request.getParameter("password");
            User user2=new User();

            user.setUPassword(UPassword);
            user.setUName(UName);
            user2=userService.login(user);
            if (user2!= null) {

                String remember=request.getParameter("remember");
                session.setAttribute("thisUser",user);


                if(("yes").equals(remember)){

                    session.setAttribute("user", user);

                }
                mav.setViewName("index");
                return mav;
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("登陆失败");
            }
            mav.setViewName("userlogin");
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


    //注册
    @RequestMapping(value = "/register")
    public void addUser(User user,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UName = request.getParameter("name");
        String UPassword = request.getParameter("password");
        String UEmail=request.getParameter("email");
        boolean b=userService.checkUser(UName,user);
        if(b){
            user.setUName(UName);
            user.setUPassword(UPassword);
            user.setUEmail(UEmail);
            userService.register(user);
            response.sendRedirect("/templates/index.ftl");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册成功请重新登陆");


        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册失败");
        }


    }

    @GetMapping("booklist")
    public String booklist(){
        return "user/booklist";
    }

    @PostMapping("showBooklist")
    @ResponseBody
    public LayerData<Books> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 ServletRequest request) {
        Map param = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Books> booksData = new LayerData<>();
        int std = (page - 1) * limit ;
        param.put("std", std);
        param.put("limit",limit);
        List list = userService.showBooklist(param);
        booksData.setCount(userService.booklistCount(param));
        booksData.setData(list);
        return booksData;
    }



}
