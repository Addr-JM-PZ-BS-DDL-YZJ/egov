<%@ page pageEncoding="GB2312" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="/system/">
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
                            class="STYLE4">ϵͳ�û��б�</span></td>
                    <td width="281" background="../images/tab_05.gif">
                        <table border="0" align="right" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="60">
                                    <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td class="STYLE1">
                                                <div align="center"><img src="../images/add.jpg" style="cursor:hand"
                                                                         onclick="document.location='userAdd.jsp'"/>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="60">
                                    <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td class="STYLE1">
                                                <div align="center"><img id="updateImg"
                                                                         src="../images/update_disabled.jpg"
                                                                         style="cursor:hand"
                                                                         onclick="toUpdate()"/>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="60">
                                    <table width="90%" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td class="STYLE1">
                                                <div align="center"><img id="deleteImg"
                                                                         src="../images/delete_disabled.jpg"
                                                                         style="cursor:hand"
                                                                         onclick="deleteUser()"/></div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="14"><img src="../images/tab_07.gif" width="14" height="30"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                    <td bgcolor="#f3ffe3">
                        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68">
                            <tr>
                                <td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1">
                                    <div align="center" class="STYLE2 STYLE1">
                                        <input type="checkbox" id="checkboxAll" name="checkbox62"
                                               onclick="checkboxAll()"/></div>
                                </td>
                                <td width="8%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                    <div align="center" class="STYLE2 STYLE1">���</div>
                                </td>
                                <td width="12%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                    <div align="center" class="STYLE2 STYLE1">�û�����</div>
                                </td>
                                <td width="24%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                    <div align="center" class="STYLE2">�û�����</div>
                                </td>
                                <td width="38%" height="18" background="../images/tab_14.gif" class="STYLE1">
                                    <div align="center" class="STYLE2 STYLE1">��������</div>
                                </td>
                            </tr>
                            <form name="userUpdate" id="userUpdate" method="post" action="/UserServlet/toUpdate">
                                <input type="hidden" name="pageno" value="${pageUtil.pagenow}"/>
                            <c:forEach items="${pageUtil.list}" var="user">
                                <tr>
                                    <td height="18" bgcolor="#FFFFFF">
                                        <div align="center" class="STYLE1">
                                            <input name="checkbox" id="checkbox" type="checkbox" class="STYLE2"
                                               value="${user.usercode}" onclick="changeUpdateAndDelImg()"/>
                                        </div>
                                    </td>
                                    <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                                        <div align="center" class="STYLE2 STYLE1">1</div>
                                    </td>
                                    <td height="18" bgcolor="#FFFFFF">
                                        <div align="center" class="STYLE2 STYLE1">${user.usercode}</div>
                                    </td>
                                    <td height="18" bgcolor="#FFFFFF">
                                        <div align="center" class="STYLE2 STYLE1">${user.username}</div>
                                    </td>
                                    <td height="18" bgcolor="#FFFFFF">
                                        <div align="center"><a href="#">${user.orgtype}</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </form>
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
                                <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">��${pageUtil.totalCount}����¼����ǰ��${pageUtil.pagenow}/${pageUtil.totalPage}}ҳ��ÿҳ${pageUtil.pagesize}����¼</span>
                                </td>
                                <td width="75%" valign="top" class="STYLE1">
                                    <div align="right">
                                        <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td width="30" height="22" valign="middle">
                                                    <div align="right">
                                                        <c:if test="${pageUtil.pagenow==1}">
                                                            <img src="../images/firstPageDisabled.gif"/>
                                                        </c:if>
                                                        <c:if test="${pageUtil.pagenow!=1}">
                                                            <img src="../images/firstPage.gif" onclick="changePage(1)"/>
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td width="30" height="22" valign="middle">
                                                    <div align="right">
                                                        <c:if test="${pageUtil.pagenow==1}">
                                                            <img src="../images/prevPageDisabled.gif"/>
                                                        </c:if>
                                                        <c:if test="${pageUtil.pagenow!=1}">
                                                            <img src="../images/prevPage.gif"
                                                                 onclick="changePage(${pageUtil.prev})"/>
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td width="30" height="22" valign="middle">
                                                    <div align="right">
                                                        <c:if test="${pageUtil.pagenow==pageUtil.last}">
                                                            <img src="../images/nextPageDisabled.gif"/>
                                                        </c:if>
                                                        <c:if test="${pageUtil.pagenow!=pageUtil.last}">
                                                            <img src="../images/nextPage.gif"
                                                                 onclick="changePage(${pageUtil.next})"/>
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td width="30" height="22" valign="middle">
                                                    <div align="right">
                                                        <c:if test="${pageUtil.pagenow==pageUtil.last}">
                                                            <img src="../images/lastPageDisabled.gif"/>
                                                        </c:if>
                                                        <c:if test="${pageUtil.pagenow!=pageUtil.last}">
                                                            <img src="../images/lastPage.gif"
                                                                 onclick="changePage(${pageUtil.last})"/>
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td width="59" height="22" valign="middle">
                                                    <div align="right">ת����</div>
                                                </td>
                                                <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="textfield" id="go" type="text" class="STYLE1"
                           style="height:14px; width:25px;text-align:right" size="5"/>
                  </span></td>
                                                <td width="23" height="22" valign="middle">ҳ</td>
                                                <td width="30" height="22" valign="middle"><img src="../images/go.gif"
                                                                                                width="37" height="15"
                                                                                                style="cursor: pointer"
                                                                                                onclick="toPage()"/>
                                                </td>
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
<script type="text/javascript">
    //��ҳǰ��ť
    function changePage(pageno) {
        document.location.href = "/UserServlet/selectUser?pageno=" + pageno;
    }

    //��ҳ��ת��ť
    function toPage() {
        var pageno = document.getElementById("go").value.trim();
        var ok = true;
        //�ǿ��ж�
        if ("" == pageno) {
            alert("��תҳ�治��Ϊ��");
            ok = false;
        }
        if (isNaN(pageno)) {
            alert("��ת����������");
            ok = false;
        }
        if (pageno < 1 || pageno > ${pageUtil.last==null?10:pageUtil.last}) {
            alert("��תҳ�������[1,${pageUtil.last==null?10:pageUtil.last}]��Χ��");
            ok = false;
        }
        if (ok) {
            document.location.href = "/UserServlet/selectUser?pageno=" + pageno;
        }
    }

    //��ѡ�����ɾ�����޸İ�ť����
    function changeUpdateAndDelImg() {
        //��ȡ���и�ѡ��
        var checkbox_All = document.getElementsByName("checkbox");
        //ɾ����ť���޸İ�ťͼƬ����
        var delete_img = document.getElementById("deleteImg");
        var update_img = document.getElementById("updateImg");
        //ͳ�Ʊ�ѡ�еĸ�ѡ�������
        var countChecked = 0;
        for (var i = 0; i < checkbox_All.length; i++) {
            if (checkbox_All[i].checked == true) {
                countChecked++;
            }
        }
        //ѡ������Ϊ0�����޸ĺ�ɾ����ť�����ɿɵ��
        if (countChecked == 0) {
            //../images/update_disabled.jpg
            delete_img.disabled = true;
            update_img.disabled = true;
            delete_img.src = "../images/delete_disabled.jpg";
            update_img.src = "../images/update_disabled.jpg"
        } else if (countChecked == 1) {
            //ѡ������Ϊ1�����޸ĺ�ɾ����ť���ɵ��
            delete_img.disabled = false;
            update_img.disabled = false;
            delete_img.src = "../images/delete.jpg";
            update_img.src = "../images/update.jpg"

        } else {//ѡ����������1�����޸İ�ť���ɵ����ɾ����ť�ɵ��
            delete_img.disabled = false;
            update_img.disabled = true;
            delete_img.src = "../images/delete.jpg";
            update_img.src = "../images/update_disabled.jpg"
        }
    }

    //checkboxAllȫѡ��ť
    function checkboxAll() {
        var check_all = document.getElementById("checkboxAll");
        var checkboxs = document.getElementsByName("checkbox");
        if (check_all.checked) {
            for (var i = 0; i < checkboxs.length; i++) {
                checkboxs[i].checked = true;
            }
        } else {
            for (var i = 0; i < checkboxs.length; i++) {
                checkboxs[i].checked = false;
            }
        }
        changeUpdateAndDelImg();
    }

    //��ת�޸�ҳ��
    function toUpdate(){
        // alert("hellow");
        document.forms["userUpdate"].submit();
    }

    //ɾ���û���Ϣ
    function deleteUser(){
        if(window.confirm("��ȷ��Ҫɾ���û���Ϣ��")){
          document.forms["userUpdate"].action = "/UserServlet/deleteUser";
          document.forms["userUpdate"].submit();
        }
    }

    //ҳ����غ�ִ��
    window.onload = changeUpdateAndDelImg();

</script>
</html>
