package com.zlw.onlinebookshop.filter;

import com.zlw.onlinebookshop.entity.User;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zlw
 * @create 2019-03-29 16:24
 */
@WebFilter(filterName = "UserFilter",urlPatterns = "/user/booklist")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request2=(HttpServletRequest)req;
        HttpServletResponse response2=(HttpServletResponse)resp;
        HttpSession session=request2.getSession();

        User user=(User) session.getAttribute("thisUser");
        if(user==null){
            response2.sendRedirect("/user/index");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
