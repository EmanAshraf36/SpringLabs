<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Task ${task.id}</title></head>
<body>

<h1>${task.title}</h1>
<p>ID: ${task.id}</p>
<p>Priority: ${task.priority}</p>
<p>Status: ${task.completed ? "Done" : "Pending"}</p>

<a href="<c:url value='/tasks'/>">← Back to list</a>

</body>
</html>