<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学分展示</title>

     <%
        ArrayList Arr=(ArrayList)session.getAttribute("gradeArr");
        /*int number=usersArr.size();
        int pageSize=5;
        int totalPage=0;
        int currentPage=1;
        if((double)number/(double)pageSize>number/pageSize)
        {
            totalPage=(number/pageSize)+1;
        }
        else
        {
            totalPage=number/pageSize;
        }
        String n=request.getParameter("numPerPage");
        if(n!=null)
        {
            pageSize=Integer.parseInt(n);
        }*/
     %>  

<style type="text/css">
<!--
.STYLE1 {font-size: 12px}
.STYLE3 {color: #707070; font-size: 12px; }
.STYLE5 {color: #0a6e0c; font-size: 12px; }
body {
	margin-top: 0px;
	margin-bottom: 0px;
}
.STYLE7 {font-size: 12}
-->
</style>

   

</head>

<body >

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
        <td style="padding-right:10px;"><div align="right">
          <table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center">
                        <input type="checkbox" name="checkbox62" value="checkbox" />
                    </div></td>
                    <td class="STYLE1"><div align="center">全选</div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="images/001.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="addUser.html">新增</a></div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="images/114.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="alterUser.jsp">修改</div></td>
                  </tr>
              </table></td>
              <td width="52"><table width="88%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="images/083.gif" width="14" height="14" /></div></td>
                    <td class="STYLE1"><div align="center"><a href="deleteUsers.jsp">删除</div></td>
                  </tr>
              </table></td>
            </tr>
          </table>
        </div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="idData" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#c9c9c9">
      <tr>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">courseNo</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">courseName</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">courseType</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">courseCredit</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">firstMark</span></strong></div></td>
        <td height="22" bgcolor="#FFFFFF"><div align="center"><strong><span class="STYLE1">secondMark</span></strong></div></td>
      </tr>
       <% 
         for(int i=0; i <Arr.size();i++){
           Grade g=(Grade)Arr.get(i);    
      %>         
          <tr>
              <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=g.getCourseNo()%></span></strong></div></td>
              <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=g.getCourseName() %></span></strong></div></td>
              <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=g.getCourseType() %></span></strong></div></td>
              <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=g.getCourseCredit() %></span></strong></div></td>
              <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=g.getFirstMark() %></span></strong></div></td>
              <td height="22" bgcolor="#FFFFFF"><div align="center"><span class="STYLE3"><%=g.getSecondMark() %></span></strong></div></td>
          </tr>  
      <%
        }
     %>
    </table></td>
  </tr>
  
  
  <tr>
    <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="25%" height="29" nowrap="nowrap"><table width="342" border="0" cellspacing="0" cellpadding="0">
          <tr>
          <form action="/prj04/show.jsp" action="post">
            <td width="44%" class="STYLE1">当前页：<span id="currentPageShow">1</span>页 每页
            
            <input  type="text" id="pageSet" name="pageSet" class="STYLE1" style="height:14px; width:25px;"  size="5" />            </td>
            <td width="14%" class="STYLE1"><img  src="images/sz.gif" width="43" height="20" onClick="goPageSet()"  /></td>
            
            </form> 
            
            <td width="42%" class="STYLE1"><span class="STYLE7">数据总 </span></td>
            <td width="42%" class="STYLE1"><span class="STYLE7"><a href="web/main.html" target="right">返回</a></span></td>
          </tr>
        </table></td>
        <td width="75%" valign="top" class="STYLE1"><div align="right">
            <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62" height="22" valign="middle"><div align="right"><a href="#" onClick="goPage1()"><img src="images/page_first_1.gif" width="48" height="20" /></a></div></td>
                <td width="50" height="22" valign="middle"><div align="right"><a href="#" onClick="goPage2()"><img src="images/page_back_1.gif" width="55" height="20" /></a></div></td>
                <td width="54" height="22" valign="middle"><div align="right"><a href="#" onClick="goPage3()"><img src="images/page_next.gif" width="58" height="20" /></a></div></td>
                <td width="49" height="22" valign="middle"><div align="right"><a href="#" onClick="goPage4()"><img src="images/page_last.gif" width="52" height="20" /></div></td>
                <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                <td width="25" height="22" valign="middle"><span class="STYLE7">
                  <input name="pageSet2" id="pageSet2" type="text" class="STYLE1" style="height:10px; width:25px;" size="5" />
                </span></td>
                <td width="23" height="22" valign="middle">页</td>
                <td width="30" height="22" valign="middle"><img src="images/go.gif" width="26" height="20" onClick="goPageSet2()" /></td>
              </tr>
            </table>
        </div></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>



