<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Session 2 Lab</title></head>
<body>

<h1>Session 2 Lab — Task Manager</h1>

<h3>Task 1 — read endpoints</h3>
<ul>
    <li><a href="<c:url value='/tasks'/>">/tasks</a> — list all</li>
    <li><a href="<c:url value='/tasks/1'/>">/tasks/1</a> — one task by id</li>
    <li><a href="<c:url value='/tasks/search?priority=HIGH'/>">/tasks/search?priority=HIGH</a> — filter</li>
</ul>

<h3>Task 2 + 3 — create and validate</h3>
<ul>
    <li><a href="<c:url value='/tasks/new'/>">/tasks/new</a> — the form (try submitting it empty)</li>
</ul>

<h3>Task 4 — exception handling</h3>
<ul>
    <li><a href="<c:url value='/tasks/999'/>">/tasks/999</a> — TaskNotFoundException → @ControllerAdvice</li>
</ul>

</body>
</html>