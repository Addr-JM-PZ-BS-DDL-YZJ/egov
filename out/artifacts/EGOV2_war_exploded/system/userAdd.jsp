<%@ page pageEncoding="GB2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
  <title>�ޱ����ĵ�</title>
  <style type="text/css">
    <!--
    body {
      margin-left: 0px;
      margin-top: 0px;
      margin-right: 0px;
      margin-bottom: 0px;
    }

    .STYLE1 {
      font-size: 12px
    }

    .STYLE4 {
      font-size: 12px;
      color: #1F4A65;
      font-weight: bold;
    }

    a:link {
      font-size: 12px;
      color: #06482a;
      text-decoration: none;

    }

    a:visited {
      font-size: 12px;
      color: #06482a;
      text-decoration: none;
    }

    a:hover {
      font-size: 12px;
      color: #FF0000;
      text-decoration: underline;
    }

    a:active {
      font-size: 12px;
      color: #FF0000;
      text-decoration: none;
    }

    .STYLE7 {
      font-size: 12px;
    }

    -->
  </style>

  <script type="text/javascript">

    //���ύ
    function doSave() {

      //��֤��
      var ok = checkForm();
      //�ύ��
      if (ok) {
        alert("success");
        document.forms[0].submit();
      }else{
        alert("error");
      }
    }

    //����֤
    function checkForm() {
      var result = true;
      //�û����벻Ϊ��
      var code = document.getElementById("usercode");
      if (code.value == null || "" == code.value){
        code.focus();
        result = false;
      }
      //�û�����Ϊ��
      var name = document.getElementById("username");
      if (name.value == null || " " == name.value) {
        name.focus();
        result = false;
      }
      //���벻Ϊ��
      var pwd = document.getElementById("userpwd");
      if (pwd.value == null || " " == pwd.value) {
        pwd.focus();
        result = false;
      }
      //��������һ��
      var checkPwd = document.getElementById("checkpwd");
      if (checkPwd.value == null || pwd.value != checkPwd.value) {
        checkPwd.value = "";
        checkPwd.focus();
        result = false;
      }
      //�������Ͳ�Ϊ��
      var org = document.getElementById("orgtype");
      if (org.value == null || "" == org.value) {
        org.focus();
        result = false;
      }
      return result;
    }

    //ajax��֤�û������Ƿ����
    function checkUsercode(usercode){
      //ʵ����xhlHttpRequest����
      var xhr = new XMLHttpRequest();
      //�󶨻ص�����
      xhr.onreadystatechange = function(){
        if(xhr.readyState==4&&xhr.status==200){
            document.getElementById("showInfo").innerHTML  = xhr.responseText;
        }
      }
      //��ʼ������
      xhr.open("get","/UserServlet/checkUsercode?_="+new Date().getTime()+"&usercode="+usercode,true);
      //��������
      xhr.send();
    }

  </script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30"/></td>
          <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16"
                                                                  height="16"/> <span
                  class="STYLE4">����ϵͳ�û�</span></td>
          <td width="281" background="../images/tab_05.gif">
            <table border="0" align="right" cellpadding="0" cellspacing="0">
            </table>
          </td>
          <td width="14"><img src="../images/tab_07.gif" width="14" height="30"/></td>
        </tr>
      </table>
    </td>
  </tr>
  <!--  ���ύ-->
  <form action="/UserServlet/saveUser" method="post">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="9" background="../images/tab_12.gif">&nbsp;</td>
            <td bgcolor="#f3ffe3">
              <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1"
                     bgcolor="#0e6f68">
                <tr height="26"></tr>
                <tr>
                  <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">�û�����</div>
                  </td>
                  <td bgcolor="#FFFFFF" class="STYLE1">
                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                id="usercode"
                                                                                name="usercode"
                                                                                style="width:100px; height:20px; border:solid 1px #035551; color:#000000" onblur="checkUsercode(this.value)">&nbsp;<font
                            color='red'>*</font><span id="showInfo"></span></div>
                  </td>
                </tr>
                <tr>
                  <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">�û�����</div>
                  </td>
                  <td bgcolor="#FFFFFF" class="STYLE1">
                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                id="username"
                                                                                name="username"
                                                                                style="width:100px; height:20px; border:solid 1px #035551; color:#000000">&nbsp;<font
                            color='red'>*</font></div>
                  </td>
                </tr>
                <tr>
                  <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">�û�����</div>
                  </td>
                  <td bgcolor="#FFFFFF" class="STYLE1">
                    <div align="left" style="padding:2px" class="STYLE2"><input type="password"
                                                                                id="userpwd"
                                                                                name="userpwd"
                                                                                style="width:100px; height:20px; border:solid 1px #035551; color:#000000">&nbsp;<font
                            color='red'>*</font></div>
                  </td>
                </tr>
                <tr>
                  <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">ȷ������</div>
                  </td>
                  <td bgcolor="#FFFFFF" class="STYLE1">
                    <div align="left" style="padding:2px" class="STYLE2"><input type="password"
                                                                                id="checkpwd"
                                                                                name="checkpwd"
                                                                                style="width:100px; height:20px; border:solid 1px #035551; color:#000000">&nbsp;<font
                            color='red'>*</font></div>
                  </td>
                </tr>
                <tr>
                  <td width="200" bgcolor="#FFFFFF" height="26" class="STYLE1">
                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">��������</div>
                  </td>
                  <td bgcolor="#FFFFFF" class="STYLE1">
                    <div align="left" style="padding:2px" class="STYLE2">
                      <select name="orgtype" id="orgtype"
                              style="width:105px; height:20px; border:solid 1px #035551; color:#000000">
                        <option value=""></option>
                        <option value="0">�������</option>
                        <option value="1">����</option>
                      </select>&nbsp;<font color='red'>*</font></div>
                  </td>
                </tr>
              </table>
            </td>
            <td width="9" background="../images/tab_16.gif">&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </form>
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="9" background="../images/tab_12.gif">&nbsp;</td>
          <td bgcolor="#f3ffe3">
            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68">
              <tr height="26">
                <td bgcolor="#FFFFFF" height="26" class="STYLE1" colspan="2"
                    style="padding-top:5px;padding-left:200px"><img src="../images/save.jpg" id="save"
                                                                    onclick="doSave()"
                                                                    style="cursor:hand"
                />&nbsp;&nbsp;<img
                        src="../images/clear.jpg" style="cursor:hand"/></td>
              </tr>
            </table>
          </td>
          <td width="9" background="../images/tab_16.gif">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td height="29">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29"/></td>
          <td background="../images/tab_21.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="75%" valign="top" class="STYLE1">
                  <div align="left">
                    <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="62" height="22" valign="middle"></td>
                        <td width="50" height="22" valign="middle"></td>
                      </tr>
                    </table>
                  </div>
                </td>
              </tr>
            </table>
          </td>
          <td width="14"><img src="../images/tab_22.gif" width="14" height="29"/></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
