<%--
  Created by Dennis Miller.
  User: dmiller
  Date: 2019-02-22
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.spiritwisdomcounseling.model.Contact" %>
<html>
<head>
    <title>Spirit Wisdom Counseling | Contact Result</title>
</head>
<body>

<div>

    <h3> Contact Information:</h3>

    <% Contact contact = (Contact)request.getAttribute("con");  %>

        <%=contact.getFirstName()%><br>
        <%=contact.getLastName()%>


    </div>

</body>
</html>
