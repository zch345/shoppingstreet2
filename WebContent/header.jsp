<%@ page language="java" pageEncoding="UTF-8"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.user==null}">
		<%
			String loginName = null;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("loginName"))
					{
						loginName = cookie.getValue();
						break;
					}
				}
			}
			session.setAttribute("loginName",loginName);
		%>
		 
		 <c:if test="${empty sessionScope.loginName }">
		 	<span class="fl">你好，请<a href="login.jsp"  style="color:#ff4e00;">登录</a>&nbsp;<a href="Regist.html" style="color:#ff4e00;">免费注册</a>&nbsp;&nbsp;</span>
		 </c:if>
          <c:if test="${! empty sessionScope.loginName }">
          	<span class="fl"><a href="user/member.jsp">${loginName}</a>&nbsp;|&nbsp;<a href="user/UserOrderServlet">我的订单</a>&nbsp;</span>
          	<span class="fl">|&nbsp;<a href="#" >注销</a></span>
          </c:if>
     </c:if>    
          <c:if test="${sessionScope.user!=null}">
            <span class="fl"><a href="user/member.jsp">${sessionScope.user.loginName}</a>&nbsp;|&nbsp;<a href="user/UserOrderServlet">我的订单</a>&nbsp;</span>
          	<span class="fl">|&nbsp;<a href="#" >注销</a></span>
          </c:if>

</body>
</html>