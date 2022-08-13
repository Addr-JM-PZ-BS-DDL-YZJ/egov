package com.yzj.egov.user.servlet;

import com.yzj.egov.bean.User;
import com.yzj.egov.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 登录业务
 */
@WebServlet("/LoginServlet/*")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if("/LoginServlet/login".equals(uri)){
            login(req,resp);
        }else if("/LoginServlet/exit".equals(uri)){
            exit(req,resp);
        }

    }

    /**
     * 登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usercode = req.getParameter("usercode");
        String userpwd = req.getParameter("userpwd");
        String orgtype = req.getParameter("orgtype");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean hasNext = false;
        HttpSession session = req.getSession();

        try {
            conn = DBUtil.getConnection();
            String sql = "select username from t_user where usercode = ? and userpwd = ? and orgtype = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,usercode);
            ps.setString(2,userpwd);
            ps.setString(3,orgtype);
            rs = ps.executeQuery();
            hasNext = rs.next();
            if(hasNext){
                User user = new User();
                user.setUsercode(usercode);
                user.setUsername(rs.getString("username"));
                user.setOrgtype(orgtype);
                session.setAttribute("user",user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        if(hasNext){
            //跳转到main,html
            resp.sendRedirect("/main.html");

        }else{
            String errorMsg = "登录失败，请重新登录！";
            //封装登录失败信息
            session.setAttribute("errorMsg",errorMsg);
            //返回login.jsp
            req.getRequestDispatcher("/").forward(req,resp);
        }

    }

    /**
     * 退出登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        HttpSession session = req.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        resp.sendRedirect("/");
    }
}
