<%@ page pageEncoding="gb2312" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
        //�����
        function addNewLine(invregnum, invnam, cty) {
            //��ȡtable����
            var invTable = document.getElementById("inv-table");
            //��ȡtable������
            var indexLength = invTable.rows.length;
            //�½���
            var newRow = invTable.insertRow(indexLength);
            //�����е�id
            newRow.id = invregnum;
            //�����еı���ɫ
            newRow.style.background = "white";
            //�½���
            var newTb0 = newRow.insertCell(0);
            var newTb1 = newRow.insertCell(1);
            var newTb2 = newRow.insertCell(2);
            var newTb3 = newRow.insertCell(3);
            var newTb4 = newRow.insertCell(4);
            //�����е�Ԫ��html
            newTb0.innerHTML = '<div align="center" style="padding:5px" class="STYLE2 STYLE1"><input type="hidden" name="invregnum" value="' + invregnum + '"/> ' + invnam + '</div>';
            newTb1.innerHTML = '  <div align="center" style="padding:2px" class="STYLE2"><input type="hidden" name="cty" value="' + cty + '"/>' + cty + '</div>';
            newTb2.innerHTML = ' <div align="center" style="padding:5px" class="STYLE2 STYLE1"><input type="text" name="regcapItem" onblur="computeCap()" style="width:90px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div>';
            newTb3.innerHTML = '<div align="center" style="padding:2px" class="STYLE2"><input type="text" name="scale" style="width:90px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div>';
            newTb4.innerHTML = ' <div align="center" style="padding:2px" class="STYLE2"><img src="../images/delete.jpg" onclick="delRow(' + invregnum + ')"/></div>';
        }

        //ɾ����
        function delRow(rowid) {
            //��ȡ���
            var invTable = document.getElementById("inv-table");
            //��ȡ��
            var tableRow = document.getElementById(rowid);
            //ɾ����
            invTable.deleteRow(tableRow.rowIndex);
        }

        //�����ʽ�
        function computeCap() {
            //��ע���ʽ�
            var ragcap = document.getElementById("regcap");
            //����ע���ʽ�
            var outregcap = document.getElementById("outregcap");

            var totalRagcap = 0;
            var totalOutregcap = 0;

            //�ʽ�򼯺�
            var ragcapItem = document.getElementsByName("regcapItem");

            //���𼯺�
            var outragcapItem = document.getElementsByName("cty");

            for (var i = 0; i < ragcapItem.length; i++) {
                if (ragcapItem[i].value != "") {
                    totalRagcap += parseInt(ragcapItem[i].value);
                    if (outragcapItem[i].value != "�й�") {
                        totalOutregcap += parseInt(ragcapItem[i].value);
                    }
                }
            }
            //��ֵ
            ragcap.value = totalRagcap;
            outregcap.value = totalOutregcap;
            //�ⷽ���ʱ���
            var tipscale = document.getElementById("tipscale");
            tipscale.innerHTML = (totalOutregcap / totalRagcap * 100).toFixed(2);
        }

        //���ύ
        function doSave() {
            document.forms["enForm"].submit();
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
                                                                            height="16"/> <span class="STYLE4">����������ҵ�Ǽ�-¼��</span>
                    </td>
                    <td width="281" background="../images/tab_05.gif">
                        <table border="0" align="right" cellpadding="0" cellspacing="0">
                        </table>
                    </td>
                    <td width="14"><img src="../images/tab_07.gif" width="14" height="30"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <form name="enForm" action="/ForeignServlet/save" method="post">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68">
                            <tr>
                                <td width="100" height="26" class="STYLE1" colspan="4">
                                    <div align="center" style="padding:5px" class="STYLE2 STYLE1"><font color="#FFFFFF"><B>��ҵ������Ϣ</B></font>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">��֯��������:</div>
                                </td>
                                <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px"
                                         class="STYLE2">${pageContext.request.getParameter("orgcode")}
                                        <input type="hidden" name="orgcode"
                                               value="${pageContext.request.getParameter("orgcode")}"/>
                                    </div>
                                </td>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">���Ǽ�֤��:</div>
                                </td>
                                <td bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text" name="regno"
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                            color="red">*</font></div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">��ҵ��������:</div>
                                </td>
                                <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                name="cnname"
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                            color="red">*</font></div>
                                </td>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">��ҵӢ������:</div>
                                </td>
                                <td bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                name="enname"
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">��ϵ��:</div>
                                </td>
                                <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                name="contactman"
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000">
                                    </div>
                                </td>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">��ϵ�绰:</div>
                                </td>
                                <td bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                name="contacttel"
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" height="26" class="STYLE1" colspan="4">
                                    <div align="center" width="100%" style="padding:5px" class="STYLE2 STYLE1"><font
                                            color="#FFFFFF"><B>��ҵ�ʽ������Ϣ</B></font></div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">ע���ʱ�:</div>
                                </td>
                                <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                name="regcap"
                                                                                                id="regcap" readonly
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                            color="red">*</font></div>
                                </td>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">ע�����:</div>
                                </td>
                                <td bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2">
                                        <select name="regcry" style="WIDTH:100px">
                                            <option value="000">�����</option>
                                            <option value="001">��Ԫ</option>
                                            <option value="002">Ӣ��</option>
                                            <option value="003">�ձ�</option>
                                        </select> <font color="red">*</font></div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">�ⷽע���ʱ�:</div>
                                </td>
                                <td width="250" bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><input type="text"
                                                                                                name="outregcap"
                                                                                                id="outregcap" readonly
                                                                                                style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font
                                            color="red">*</font></div>
                                </td>
                                <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1">
                                    <div align="right" style="padding:5px" class="STYLE2 STYLE1">�ⷽ���ʱ���:</div>
                                </td>
                                <td bgcolor="#FFFFFF" class="STYLE1">
                                    <div align="left" style="padding:2px" class="STYLE2"><span id="tipscale">0</span>%
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100" height="26" class="STYLE1" colspan="4">
                                    <div align="center" style="padding:5px" class="STYLE2 STYLE1"><font color="#FFFFFF"><B>Ͷ�����ʽ��������</B></font>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%" bgcolor="#FFFFFF" class="STYLE1" colspan="4">
                                    <table id="inv-table" border="0" width="100%" height="100%" cellpadding="0"
                                           cellspacing="1"
                                           bgcolor="#0e6f68">
                                        <tr>
                                            <td width="20%" bgcolor="#CCCCCC" height="20" class="STYLE1">
                                                <div align="center" style="padding:5px" class="STYLE2 STYLE1">Ͷ��������
                                                </div>
                                            </td>
                                            <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                <div align="center" style="padding:2px" class="STYLE2">����</div>
                                            </td>
                                            <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                <div align="center" style="padding:5px" class="STYLE2 STYLE1">ע���ʱ����ʶ�
                                                </div>
                                            </td>
                                            <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                <div align="center" style="padding:2px" class="STYLE2">����������</div>
                                            </td>
                                            <td width="20%" bgcolor="#CCCCCC" class="STYLE1">
                                                <div align="center" style="padding:2px" class="STYLE2"><img
                                                        src="../images/query.jpg"
                                                        onclick="window.open('/foreignExchange/orgcodeSelect.jsp', '��ҳ��ѯ��֯����', 'width=720, height=400, scrollbars=no')"/>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        </td>
                        <td width="9" background="../images/tab_16.gif">&nbsp;</td>

                    </tr>
                </table>
            </form>
        </td>

    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="9" background="../images/tab_12.gif">&nbsp;</td>
                    <td bgcolor="#f3ffe3">
                        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68">
                            <tr height="30">
                                <td bgcolor="#FFFFFF" height="30" class="STYLE1" colspan="2" align="center"><img
                                        src="../images/ok.jpg" onclick="doSave()"/>&nbsp;&nbsp;<img
                                        src="../images/back.jpg" onclick="window.history.back()"/></td>
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
