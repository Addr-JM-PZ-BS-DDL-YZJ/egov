package com.yzj.egov.bean;

import com.yzj.egov.util.StringUtil;

/**
 * 用户表
 */
public class User {

   private String usercode;
   private String username;
   private String userpwd ;
   private String orgtype ;

   private String regdate ;
    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getOrgtype() {
        return StringUtil.getOrgTypeString(orgtype);
    }

    public void setOrgtype(String orgtype) {
        this.orgtype = orgtype;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", orgtype='" + orgtype + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
