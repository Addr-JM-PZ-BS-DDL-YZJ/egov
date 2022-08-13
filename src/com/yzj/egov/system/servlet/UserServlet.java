package com.yzj.egov.system.servlet;

import com.yzj.egov.bean.User;
import com.yzj.egov.util.Const;
import com.yzj.egov.util.DBUtil;
import com.yzj.egov.util.DateUtil;
import com.yzj.egov.util.PageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户业务
 */
@WebServlet("/UserServlet/*")
public class UserServlet extends HttpServlet {

    /**
     * 模板方法设计模式
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求url
        String uri = req.getRequestURI();
        if ("/UserServlet/saveUser".equals(uri)) {
            //保存用户信息
            saveUser(req, resp);
        }else if("/UserServlet/selectUser".equals(uri)) {
            //用户信息分页查询
            selectUser(req, resp);
        }else if("/UserServlet/toUpdate".equals(uri)){
            //跳转修改页面
            toUpdate(req,resp);
        }else if("/UserServlet/updateUser".equals(uri)){
            //修改用户信息
            updateUser(req,resp);
        }else if("/UserServlet/deleteUser".equals(uri)){
            //删除用户信息

        }else if("UserServlet/checkUsercode".equals(uri)){
            //ajax检查用户代码
            checkUsercode(req,resp);
        }
//        }else if("/UserServlet/toUpdate".equals(uri)){
//            //跳转修改页面
//            toUpdate(req,resp);
//        }else if("/UserServlet/updateUser".equals(uri)){
//            //修改用户信息
//            updateUser(req,resp);
//        }else if("/UserServlet/deleteUser".equals(uri)){
//            //删除用户信息
//            deleteUser(req,resp);
//        }else if("/UserServlet/checkUsercode".equals(uri)){
//            //ajax检测用户代码
//            checkUsercode(req,resp);
//        }
    }

    /**
     * 保存用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usercode = req.getParameter("usercode");
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        String orgtype = req.getParameter("orgtype");
        //当前系统时间
        String regdate = DateUtil.format(new Date(), Const.DATE_FORMAT_ALL);

        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //记录结果
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into t_user (usercode,username,userpwd,orgtype,regdate) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usercode);
            ps.setString(2, username);
            ps.setString(3, userpwd);
            ps.setString(4, orgtype);
            ps.setString(5, regdate);

            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        //如果插入成功，则跳转页面
        if (result == 1) {
            selectUser(req,resp);
        }


    }

    /**
     * 用户信息分页查询
     * (逻辑分页)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
//    private void selectUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        System.out.println("selectUser");
//        //获取页码
//        int pageno = req.getParameter("pageno")==null?1:Integer.parseInt(req.getParameter("pageno"));
//
//        //获取session
//        HttpSession session = req.getSession();
//
//        //获取bigList集合
//        List<User> bigList = (List<User>)session.getAttribute("bigList");
//
//        //判断bigList是否为空
//        if(null==bigList){
//
//            //如果为空，则新建bigList集合
//            bigList = new ArrayList<>();
//
//            //连接数据库
//            Connection conn = null;
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//
//            try {
//                conn = DBUtil.getConnection();
//                String sql = "select usercode,username,orgtype from t_user order by regdate desc";
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                while(rs.next()){
//
//                    //新建用户对象
//                    User user = new User();
//                    user.setUsercode(rs.getString("usercode"));
//                    user.setUsername(rs.getString("username"));
//                    user.setOrgtype(rs.getString("orgtype"));
//
//                    //将user存储到bigList
//                    bigList.add(user);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }finally {
//                DBUtil.close(conn,ps,rs);
//            }
//        }
//
//        //根据页码从bigList中回去userList集合
//        List<User> userList = new ArrayList<>();
//        //每页显示三条记录
//        int pagesize = 3;
//        //头
//        int beginIndex = (pageno-1)*pagesize;
//        //尾 判断下标是否超出范围
//        int endIndex =pageno*pagesize > bigList.size()?bigList.size():pageno*pagesize;
//        //循环取值
//        for(int i=beginIndex;i<endIndex;i++){
//                userList.add(bigList.get(i));
//        }
//
//        //将userList存储到session
//        session.setAttribute("userList",userList);
//
//        //转发请求
//        req.getRequestDispatcher("/system/user.jsp").forward(req,resp);
//
//    }

    /**
     * 分页查询用户列表
     * （物理分页）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
//    private void selectUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        //获取页码
//        int pageno = req.getParameter("pageno")==null?1:Integer.parseInt(req.getParameter("pageno"));
//        System.out.println(pageno);
//        //获取session
//        HttpSession session = req.getSession();
//
//        //创建用户集合
//        List<User> userList = new ArrayList<>();
//
//        //每页展示数量
//        int pagesize = 3;
//        //页头
//        int beginIndex = (pageno-1)*pagesize;
//        //页尾
//        int endIndex = pageno*pagesize;
//
//        //分页查询语句
//        String sql  = "select usercode,username,orgtype from t_user order by regdate desc limit ?,?";
//
//        //连接数据库
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = DBUtil.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,beginIndex);
//            ps.setInt(2,endIndex);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                User user = new User();
//                user.setUsercode(rs.getString("usercode"));
//                user.setUsername(rs.getString("username"));
//                user.setOrgtype(rs.getString("orgtype"));
//                System.out.println(user);
//                //向集合中添加数据
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            DBUtil.close(conn,ps,rs);
//        }
//
//        for(User user : userList){
//            System.out.println(user.toString());
//        }
//
//        //向session中存储userList
//        session.setAttribute("userList",userList);
//
//        //转发
//        req.getRequestDispatcher("/system/user.jsp").forward(req,resp);
//
//    }

    /**
     * 用户信息分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void selectUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取当前页
        int pageno = req.getParameter("pageno")==null?1:Integer.parseInt(req.getParameter("pageno"));

        //创建userList集合
        List<User> userList = new ArrayList<>();

        //分页查询
        int pagesize = 3;
        int beginIndex = (pageno-1)*pagesize;
        int endIndex = pageno*pagesize;
        int totalCount = 0;

        String sql = "select usercode,username,orgtype from t_user order by regdate desc limit ?,?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
              conn = DBUtil.getConnection();
              ps = conn.prepareStatement(sql);
              ps.setInt(1,beginIndex);
              ps.setInt(2,endIndex);
              rs = ps.executeQuery();
              while(rs.next()){
                User user = new User();
                user.setUsercode(rs.getString("usercode"));
                user.setUsername(rs.getString("username"));
                user.setOrgtype(rs.getString("orgtype"));

                userList.add(user);
              }

              //总条数查询
              String totalSQL = "select count(*) as totalCount from t_user";
              ps = conn.prepareStatement(totalSQL);
              rs= ps.executeQuery();
              while(rs.next()){
                totalCount = rs.getInt("totalCount");
              }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        //封装分页、集合信息
        PageUtil pageUtil= new PageUtil(pageno,userList,pagesize);
        pageUtil.setTotalCount(totalCount);

        //转发
        req.setAttribute("pageUtil",pageUtil);
        req.getRequestDispatcher("/system/user.jsp").forward(req,resp);


    }


    /**
     * 跳转修改页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取用户代码
        String usercode = req.getParameter("checkbox");
        int pageno = Integer.parseInt(req.getParameter("pageno"));
        //创建User实例
        User user = null;
        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select username,userpwd,orgtype from t_user where usercode = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,usercode);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setUsercode(usercode);
                user.setUsername(rs.getString("username"));
                user.setUserpwd(rs.getString("userpwd"));
                user.setOrgtype(rs.getString("orgtype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        //存储User实例
        req.setAttribute("user",user);
        req.setAttribute("pageno",pageno);
        //转发
        req.getRequestDispatcher("/system/userUpdate.jsp").forward(req,resp);
    }

    /**
     * 修改用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //获取表单提交信息
        String usercode = req.getParameter("usercode");
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpswd");
        String orgtype = req.getParameter("orgtype");

        //当前页面
        int pageno = Integer.parseInt(req.getParameter("pageno"));

        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = -1;
        try {
            conn = DBUtil.getConnection();
            String sql = "update t_user set username=?,userpwd=?,orgtype=? where usercode = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,userpwd);
            ps.setString(3,orgtype);
            ps.setString(4,usercode);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        if(result==1){
            //返回列表页面
            resp.sendRedirect("/UserServlet/selectUser?pageno="+pageno);
        }
    }

    /**
     * 删除选中用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //获取需要删除的用户代码
        String [] usercodeArray = req.getParameterValues("checkbox");
        for(String code : usercodeArray){
            System.out.println(code);
        }
        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

//        int count = 0;
        boolean deleteResult = true;

        try {
            conn = DBUtil.getConnection();
            String sql  = "delete from t_user where usercode = ?";
            //开启事务
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            for(String code : usercodeArray){
                ps.setString(1,code);
//                count += ps.executeUpdate();
                //批处理
                ps.addBatch();
            }
            //批处理
            ps.executeBatch();
            //提交事务
            conn.commit();
        } catch (SQLException e) {
            //回滚事务
            if(conn!=null){
                try {
                    conn.rollback();
                    deleteResult = false;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            //恢复事务
            if(conn!=null){
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.close(conn,ps,rs);
        }

//        if(count==usercodeArray.length){
////            //重定向
////            resp.sendRedirect("/UserServlet/selectUser");
////        }
            if(deleteResult){
                resp.sendRedirect("/UserServlet/selectUser");
            }

    }

    /**
     * 检查用户代码是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void checkUsercode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String usercode = req.getParameter("usercode");
        boolean hasNext = true;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where usercode = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,usercode);
            rs = ps.executeQuery();
            if(!rs.next()){
                hasNext = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        resp.setContentType("text/html;charset=gb18030");
        PrintWriter out = resp.getWriter();
        if(hasNext){
            out.print("<font color='red'>用户代码已存在</font>");
        }else{
            out.print("<font color='green'>用户代码可用</font>");
        }
    }
}
