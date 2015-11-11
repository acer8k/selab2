<%@page import="dao.AuthDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="nav.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css_styles/register_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup Page</title>
</head>
<%-- <% 
	String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h3>"+msg+"</h3>");
	
	String loggedIn = (String) session.getAttribute("loggedIn");
	if(loggedIn == null){
%>
 --%>
<body>
   <div class="login-card">
    <h2>Lab #2 - Signup Page</h2>
    <% 
	String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h3 style=color:red>"+msg+"</h3>");
	
	String loggedIn = (String) session.getAttribute("loggedIn");
	if(loggedIn == null){
%>
   
    
   <form action="SignupServlet" name ="signup" method="post">
    <br><input id="username" type="text" name="username" placeholder="Username">
    <br><input type="submit" value="Check Username Availability" name="check">
    <br><input id="firstname" type="text" name="firstname" placeholder="First Name">
    <br><input id="lastname" type="text" name="lastname" placeholder="Last Name">
    
    <br><input id="password" type="password" name="password" placeholder="Password">
    <br><input id="password" type="password" name="cpassword" placeholder="Confirm Password">

      <td>Select your role:</td><BR>
   <table border="0" style="width:50%">
  <tr>
    <td><INPUT TYPE="radio" NAME="role" VALUE="supplier">Supplier<BR></td>
    <td><INPUT TYPE="radio" NAME="role" VALUE="retailer">Retailer<BR></td>
  </tr>
  <tr>
    <td><INPUT TYPE="radio" NAME="role" VALUE="wholesaler">Wholesaler<BR></td>
    <td><INPUT TYPE="radio" NAME="role" VALUE="customer">Customer<BR></td> 	
  </tr>
</table>
    <input type="submit" name="submit" class="login login-register" value="SUBMIT">
   <!--  <input type="reset" name="reset" class="login login-register" value="Reset" > -->

</div> 
<%-- <%
	String login_msg=(String)request.getAttribute("error");  
	if(login_msg!=null)
	out.println("<font color=orange size=10px>"+login_msg+"</font>");
	%> --%>
</body>
<% }
	
	if(loggedIn!=null)
		out.write("<h3>You are already a member!</h3>");%>


 <div id="footer">
	<%@ include file="logout.jsp" %>
</div> 
</html>






