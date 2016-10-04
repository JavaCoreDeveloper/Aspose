<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aspose.com.Employee"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload File Response</title>
<style>
.main-form{
  margin : 0 auto;
  width : 800px;
}
.emloyeeTable{
  border : thin solid black;
}

.row{
  border : thin solid black;
}
</style>


</head>
<body>
	<%-- Using JSP EL to get message attribute value from request scope --%>
	<%--=(String)request.getAttribute("message")--%>
<div class="main-form">
    <p>Generate And Send Increment Letters</p>
    <form action = "sendEmail" method= "post">
	<table class= "employeeTable" border = 1px>
		<tr class= "row">
			<th>Full Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Salary</th>
		</tr>
		<%
		  // retrieve your list from the request, with casting 
		  ArrayList<Employee> list = (ArrayList<Employee>) request
		      .getAttribute("employeelist");
		  // print the information about every category of the list
		  for (Employee employee : list) {
		%>
		<tr class= "row">
			<td>
				<%
				  out.println(employee.getFullName());
				%>
			</td>
			<td>
				<%
				  out.println(employee.getEmail());
				%>
			</td>
			<td>
				<%
				  out.println(employee.getAddress());
				%>
			</td>
			<td>
				<%
				  out.println(employee.getSalary());
				%>
				<input type="hidden" name="emails[]" value="<% out.print(employee.getEmail());%>">
				<input type="hidden" name="names[]" value="<% out.print(employee.getFullName());%>">
			</td>
		</tr>
		
		<%
		
		  }
		%>


	</table>
	<br/>
	<br/>
	
        <input type="submit" value="Send Increment Letter" name="uploadFile" />
    </form>
</div>

</body>
</html>