<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>恭喜  </h2><h1  style="color: red;">${user.useName}</h1><h2>登录成功</h2>
		<table align="centre" border="1">  
      <tr>  
        <td>Session ID</td>  
        <td><%= session.getId() %></td>  
      </tr>  
      <tr>  
        <td>Created on</td>  
        <td><%= session.getCreationTime() %></td>  
     </tr>  
    </table>  
  </body>  
</html>  
sessionID:<%=session.getId()%>   
<br>   
SessionIP:<%=request.getServerName()%>   
<br>   
SessionPort:<%=request.getServerPort()%>  
<p>8080端口</p>

<button id="b" >使session失效</button>

</body>
<script type="text/javascript">
$(function() {
	$("#b").click(function() {
	  $.ajax({
   		   url:"<%=path%>/session",
   	   });
		alert("失效了！")
	})
})
</script>
</html>