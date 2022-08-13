package com.yzj.egov.foreign.servlet;

import com.yzj.egov.bean.Enterprise;
import com.yzj.egov.bean.User;
import com.yzj.egov.util.Const;
import com.yzj.egov.util.DBUtil;
import com.yzj.egov.util.DateUtil;
import com.yzj.egov.util.WebUtil;
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
import java.util.Date;

@WebServlet("/ForeignServlet/*")
public class ForeignServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        if ("/ForeignServlet/checkOrgcode".equals(uri)) {
            checkOrgcode(req, resp);
        } else if ("/ForeignServlet/save".equals(uri)) {
            save(req,resp);
        }

    }

    /**
     * ajax检查组织机构代码
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void checkOrgcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orgcode = req.getParameter("orgcode");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_enterprise where orgcode = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, orgcode);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        if(!result){
            req.getRequestDispatcher("/foreignExchange/inputOrgInfo.jsp").forward(req,resp);
        }else{
            req.setAttribute("errorMsg","组织机构已存在请重新输入！");
            req.getRequestDispatcher("/foreignExchange/newInputOrg.jsp").forward(req,resp);
        }

    }

    /**
     * 保存投资人信息和投资人企业关系信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //获取表单数据并封装
        Enterprise enterprise = new Enterprise();
        WebUtil.fieldForValue(req,enterprise);
        enterprise.setUsercode(((User)req.getSession().getAttribute("user")).getUsercode());
        enterprise.setRegdate(DateUtil.format(new Date(), Const.DATE_FORMAT_YMD));

        String[] invregnums = req.getParameterValues("invregnum");
        String[] regcapItem = req.getParameterValues("regcapItem");
        String[] scales = req.getParameterValues("scale");

        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;

        int count = 0;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "insert into t_enterprise (orgcode,regno,cnname,enname,contactman,contacttel,regcap,outregcap,regcry,usercode,regdate) values(?,?,?,?,?,?,?,?,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,enterprise.getOrgcode());
            ps.setString(2,enterprise.getRegno());
            ps.setString(3,enterprise.getCnname());
            ps.setString(4,enterprise.getEnname());
            ps.setString(5,enterprise.getContactman());
            ps.setString(6,enterprise.getContacttel());
            ps.setInt(7,Integer.parseInt(enterprise.getRegcap()));
            ps.setInt(8,Integer.parseInt(enterprise.getOutregcap()));
            ps.setString(9,enterprise.getRegcry());
            ps.setString(10,enterprise.getUsercode());
            ps.setString(11,enterprise.getRegdate());
            count = ps.executeUpdate();
            sql = "insert into t_en_inv (orgcode,invregnum,regcap,scale) values(?,?,?,?);";
            for(int i=0;i<invregnums.length;i++){
                ps = conn.prepareStatement(sql);
                ps.setString(1,enterprise.getOrgcode());
                ps.setInt(2,Integer.parseInt(invregnums[i]));
                ps.setInt(3,Integer.parseInt(regcapItem[i]));
                ps.setInt(4,Integer.parseInt(scales[i]));
                count += ps.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            if(conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.close(conn,ps,null);
        }
        //执行成功跳转到查询页面
        if(count==4){
            resp.sendRedirect("/foreignExchange/newInputOrg.jsp");
        }
    }
}
