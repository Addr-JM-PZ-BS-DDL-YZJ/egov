package com.yzj.egov.inv.servlet;

import com.yzj.egov.bean.Invest;
import com.yzj.egov.bean.User;
import com.yzj.egov.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 投资人业务
 */
@WebServlet("/InvServlet/*")
public class InvServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("gb18030");
        String uri = req.getRequestURI();
        if ("/InvServlet/saveInv".equals(uri)) {
            //保存投资人信息
            saveInv(req, resp);
        }else if("/InvServlet/selectInv".equals(uri)){
            //查询投资人信息
            selectInv(req,resp);
        }
        else if("/InvServlet/invInfo".equals(uri)){
            //查询投资人信息
            invInfo(req,resp);
        }
    }

    /**
     * 保存投资人信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void saveInv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Invest inv = new Invest();
        WebUtil.fieldForValue(req,inv);
        inv.setUsercode (((User) req.getSession().getAttribute("user")).getUsercode());
        inv.setRegdate(DateUtil.format(new Date(), Const.DATE_FORMAT_YMD));
        String cty = req.getParameter("cty");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = -1;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into t_invest (invname,cty,orgcode,contactman,contacttel,email,remark,usercode,regdate) " +
                    "values(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, inv.getInvname());
            ps.setString(2, cty);
            ps.setString(3, inv.getOrgcode());
            ps.setString(4, inv.getContactman());
            ps.setString(5, inv.getContacttel());
            ps.setString(6, inv.getEmail());
            ps.setString(7, inv.getRemark());
            ps.setString(8, inv.getUsercode());
            ps.setString(9, inv.getRegdate());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        if(result==1) {
            //保存成功返回  exoticOrgList.jsp页面
            resp.sendRedirect("/basicinfo/exoticOrgList.jsp");
        }
    }

    /**
     * 查询投资人信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void selectInv(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("gb18030");

        /*
            获取表单提交数据
         */
        int invregnum = "".equals(req.getParameter("invregnum"))?-1:Integer.parseInt(req.getParameter("invregnum"));
        String invname = req.getParameter("invname");
        String startdate = req.getParameter("startdate");
        String enddate = req.getParameter("enddate");

        /*
            分页处理
         */
        int pageno = req.getParameter("pageno")==null?1:Integer.parseInt(req.getParameter("pageno"));
        int pagesize = 3;

        List invList = new ArrayList<>();
        PageUtil pageUtil = new PageUtil(pageno,invList,pagesize);

         /*
            字符串处理
        */
        List<String> bufferList = new ArrayList();
        StringBuffer select_buffer = new StringBuffer();
        StringBuffer count_buffer = new StringBuffer();
        //查询sql
        String select_sql = "select i.invregnum,i.invname,i.regdate,i.cty,u.username from t_invest i join t_user u on i.usercode =  u.usercode where 1 =1 ";
        select_buffer.append(select_sql);

        //统计sql
        String count_sql = "select count(*) as totalCount from t_invest i join t_user u on i.usercode =  u.usercode where 1 =1 ";
        count_buffer.append(count_sql);
        //拼接sql
        if(invregnum!=-1){
            select_buffer.append(" and i.invregnum = ?");
            count_buffer.append(" and i.invregnum = ?");
            bufferList.add(String.valueOf(invregnum));
        }
        if(!"".equals(invname)){
            select_buffer.append(" and i.invname like ?");
            count_buffer.append(" and i.invname like ?");
            bufferList.add("%"+invname+"%");
        }
        if(!"".equals(startdate)){
            select_buffer.append(" and i.regdate >= ?");
            count_buffer.append(" and i.regdate >= ?");
            bufferList.add(startdate);
        }
        if(!"".equals(enddate)){
            select_buffer.append(" and i.regdate <= ?");
            count_buffer.append(" and i.regdate <= ?");
            bufferList.add(enddate);
        }
        select_buffer.append(" order by i.regdate,i.invregnum");
        /*
        *   Strubg orgStr = "Hello World!";
        *
        *   System.out.println(orgStr);
        *
        *   public class Test{
        *
        *       public static void main(String [] args){
        *
        *           System.out.println("This is main!");
        *
        *           doSome();
        *
        *           doOther();
        *
        *       }
        *
        *       private static void doSome(){
        *
        *           System.out.println("This is doSome!");
        *
        *       }
        *
        *       private static void doOther(){
        *
        *           System.out.println("This is doOther!");
        *
        *       }
        *
        *   }
        */
        String page_sql = pageUtil.getPageSql(select_buffer.toString());
        System.out.println("page_sql = " + page_sql);

        /*
            连接数据库
         */
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(page_sql);
            for(int i=0;i<bufferList.size();i++){
                ps.setString(i+1,bufferList.get(i));
            }
            rs = ps.executeQuery();
            while(rs.next()){
                Invest inv = new Invest();
                inv.setInvregnum(rs.getInt("invregnum"));
                inv.setInvname(rs.getString("invname"));
                inv.setRegdate(rs.getString("regdate"));
                inv.setUsername(rs.getString("username"));
                inv.setCty(rs.getString("cty"));
                invList.add(inv);
            }

            //统计总记录条数
            ps = conn.prepareStatement(count_buffer.toString());
            for(int i=0;i<bufferList.size();i++){
                ps.setString(i+1,bufferList.get(i));
            }
            rs = ps.executeQuery();
            if(rs.next()){
                pageUtil.setTotalCount(Integer.parseInt(rs.getString("totalCount")));
            }else{
                pageUtil.setTotalCount(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        //pageUtil封装
        req.setAttribute("pageUtil",pageUtil);
        req.getRequestDispatcher(req.getParameter("pageCode")).forward(req,resp);
    }

    /**
     * 跳转详细页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void invInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String invregnum = req.getParameter("invregnum");
        System.out.println(invregnum);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Invest inv = null;

        try {
            conn = DBUtil.getConnection();
            String sql  = "select i.invname,i.cty,i.orgcode,i.contactman,i.contacttel,i.email,i.remark,i.usercode,i.regdate from t_invest i where i.invregnum = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,invregnum);
            rs = ps.executeQuery();
            if(rs.next()){
                inv = new Invest();
                inv.setInvname(rs.getString("invname"));
                inv.setInvregnum(Integer.parseInt(invregnum));
                inv.setCty(rs.getString("cty"));
                inv.setEmail(rs.getString("email"));
                inv.setRegdate(rs.getString("regdate"));
                inv.setContactman(rs.getString("contactman"));
                inv.setContacttel(rs.getString("contacttel"));
                inv.setRemark(rs.getString("remark"));
                inv.setUsercode(rs.getString("usercode"));
                inv.setOrgcode(rs.getString("orgcode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        req.setAttribute("inv",inv);
        req.getRequestDispatcher("/basicinfo/exoticOrgView.jsp").forward(req,resp);

    }

}
