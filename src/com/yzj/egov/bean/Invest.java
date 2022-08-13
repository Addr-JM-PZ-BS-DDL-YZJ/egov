package com.yzj.egov.bean;

import com.yzj.egov.util.StringUtil;

/**
 * 经办人
 */
public class Invest {

    private int invregnum;
    private String invname;
    private String cty;
    private String orgcode;
    private String contactman;
    private String contacttel;
    private String email;
    private String remark;
    private String usercode;
    private String regdate;
    private String username;

    public int getInvregnum() {
        return invregnum;
    }

    public void setInvregnum(int invregnum) {
        this.invregnum = invregnum;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getCty() {
        if(cty!=null){
            return StringUtil.getCtyString(this.cty);
        }else{
            return null;
        }

    }

    public void setCty(String cty) {
        this.cty = cty;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    @Override
    public String toString() {
        return "Invest{" +
                "invregnum=" + invregnum +
                ", invname='" + invname + '\'' +
                ", cty='" + cty + '\'' +
                ", orgcode='" + orgcode + '\'' +
                ", contactman='" + contactman + '\'' +
                ", contacttel='" + contacttel + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", usercode='" + usercode + '\'' +
                ", regdate='" + regdate + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getContactman() {
        return contactman;
    }

    public void setContactman(String contactman) {
        this.contactman = contactman;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
