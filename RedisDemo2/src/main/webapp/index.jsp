<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
%>
<html>
<script src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js" ></script>
<body>
<h2>Hello World!</h2><p id="pp"><p>
<label>用户名</label>
<input type="text" id="userName">
<label>密 码</label>
<input type="text" id="password" >
<button id="submit" >提交</button>
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
<%   
//为了区分，第二个可以是222  
out.println("This is Tomcat Server 8080");   
%> 
<a href="<%=path%>/user" >进入个人中心</a>
<a href="<%=path%>/save.do" >保存张三对象</a>
<a href="<%=path%>/get" >获取张三对象</a>

</body>
<script type="text/javascript">
$(function() {
	$("#submit").click(function() {
		var userName=$("#userName").val();
		var password=$("#password").val();
		$.ajax({
	        type: "post",
	        url: "<%=path%>/login",
	        data: {
	        	"userName":userName,
	        	"password":password
	        },
	        success: function (data) {
	        	debugger
				if (data!=null) {
					$("#pp").html(data.msg)
				}        	 	
	        }
	 	});
	})
})


</script>

</html>
