package com.zlw.onlinebookshop.filter;

import com.zlw.onlinebookshop.entity.Admin;
import com.zlw.onlinebookshop.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zlw
 * @create 2019-03-29 16:32
 */
@WebFilter(filterName = "AdminFilter",urlPatterns = "/admin/login")
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request2=(HttpServletRequest)req;
        HttpServletResponse response2=(HttpServletResponse)resp;
        HttpSession session=request2.getSession();

        Admin admin=(Admin) session.getAttribute("thisAdmin");
        if(admin==null){
            response2.sendRedirect("/admin/index");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
