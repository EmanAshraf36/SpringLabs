<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Tasks</title></head>
<body>

<h1>Tasks</h1>

<c:if test="${not empty selectedPriority}">
    <p>Showing priority: <b>${selectedPriority}</b></p>
</c:if>

<p>
    Filter:
    <a href="<c:url value='/tasks'/>">All</a> |
    <a href="<c:url value='/tasks/search?priority=HIGH'/>">High</a> |
    <a href="<c:url value='/tasks/search?priority=MEDIUM'/>">Medium</a> |
    <a href="<c:url value='/tasks/search?priority=LOW'/>">Low</a>
</p>

<c:choose>
    <c:when test="${empty tasks}">
        <p>No tasks found.</p>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="6">
            <tr><th>ID</th><th>Title</th><th>Priority</th><th>Status</th></tr>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.id}</td>
                    <td><a href="<c:url value='/tasks/${task.id}'/>">${task.title}</a></td>
                    <td>${task.priority}</td>
                    <td>${task.completed ? "Done" : "Pending"}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

<p><a href="<c:url value='/tasks/new'/>">+ New task</a></p>

</body>
</html>