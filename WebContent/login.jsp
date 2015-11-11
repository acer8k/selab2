<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String loggedIn = (String) session.getAttribute("loggedIn"); 
if(loggedIn!=null){
	
%> 
<div id="header">
	<%@ include file="nav.jsp" %>
</div>
<% }%>	   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<body bgcolor="#85E0FF">
<link rel="stylesheet" href="css_styles/main.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LAB 2 CS 518</title>
<%-- <% String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h3 >"+msg+"</h3>");
	
%> --%>
</head>
<%	loggedIn = (String) session.getAttribute("loggedIn"); 
if(loggedIn==null){
	
%>
<body>    
 
</div>
    </br>
     <div class="login-card">
        <h3 align="center" style="color:#006b9f;">Lab #2 - Login Page</h3>
        <div align="right">  
</div>
<% String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h3 style=color:red>"+msg+"</h3>");
	
%>
   
   <form action="LoginServlet" method="post">
    <input type="text" name="user" placeholder="Username">
    <input type="password" name="pass" placeholder="Password">
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
    <input type="submit" name="login" class="login login-submit" value="Login">
  </form>
   <form action=signup.jsp>
    <input type="submit" name="register" class="login login-register" value="Signup here for new account">
  </form>

</body>
<% }%>
<%  loggedIn = (String) session.getAttribute("loggedIn"); 
if(loggedIn!=null){	
%>
<div id="footer">
	<%@ include file="logout.jsp" %>
</div>
<%} %>
</html>