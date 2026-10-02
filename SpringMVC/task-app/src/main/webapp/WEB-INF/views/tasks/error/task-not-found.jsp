<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Task not found</title></head>
<body>

<h1>404 — Task not found</h1>
<p>${message}</p>
<a href="<c:url value='/tasks'/>">← Back to list</a>

</body>
</html>