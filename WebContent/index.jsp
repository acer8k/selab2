<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ include file="nav.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body bgcolor="#FFFF99">
<%
	String loggedIn = (String) session.getAttribute("loggedIn");
	User u = (User)session.getAttribute("user");
	String msg = (String) request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h2 style=color:green>"+msg+"</h2>"); 
	if(loggedIn == "true")
		out.write("<h3>Hello " + u.getFirstName()+" "+u.getLastName()+", your role is "+u.getRole()+".</h3><br>");
	    
	else{
		if(u == null)
			out.write("<h2 style=color:red>You are not logged in.</h2>");
		else
			out.write(" ");
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INDEX</title>
</head>
<body>

</body>
<% if(loggedIn!=null){
	
%>
<div id="footer">
	<%@ include file="logout.jsp" %>
</div>
<%} %>
</html>