
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Demo Main Index</title>
</head>
<body>

<c:forEach items="${model}" var="m">
    <div><a href="<%=request.getContextPath()%>/demo/main/${m.id}" target="_blank">${m.name}</a></div>
</c:forEach>
</body>
</html>