package com.yzj.egov.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 登录过滤器
 */
@WebFilter({"/servlet/*","*.jsp","*.html"})
public class Filter implements jakarta.servlet.Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("gb18030");

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession(false);

        boolean isFilter = true;

        String[] requestUri = {"/LoginServlet","/login.jsp"};

        String servletUri  = request.getServletPath();

        for(String str : requestUri){
            if(str.equals(servletUri)){
                isFilter = false;
            }
        }
        if(!isFilter||(session!=null&&session.getAttribute("user")!=null)){
            filterChain.doFilter(request,response);
        }else{
            response.sendRedirect("/");
        }

    }
}
