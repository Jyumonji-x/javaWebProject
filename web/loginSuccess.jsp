<%--
  Created by IntelliJ IDEA.
  User: 许同樵
  Date: 2020/7/21
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
</head>
<script language="javascript">
    <%
        String href = "";
        if(request.getSession().getAttribute("url")!=null){
            href = (String) request.getSession().getAttribute("url");
            request.getSession().removeAttribute("url");
        }
        else{
            href = "index.index";
        }
    %>
    alert("Login Success！ Uid=<%=request.getSession().getAttribute("uid")%>");
    location.href="<%=href%>";
</script>
<body onload="load()">
</body>
</html>
