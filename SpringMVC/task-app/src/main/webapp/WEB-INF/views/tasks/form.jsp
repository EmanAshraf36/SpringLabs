<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head><title>New Task</title></head>
<body>

<h1>New Task</h1>

<c:url var="saveUrl" value="/tasks"/>
<form:form modelAttribute="task" action="${saveUrl}" method="post">

    <p>
        Title:
        <form:input path="title"/>
    </p>

    <p>
        Priority:
        <form:select path="priority">
            <form:option value="" label="-- choose --"/>
            <form:options items="${priorities}"/>
        </form:select>
    </p>

    <p>
        <form:checkbox path="completed"/> Completed
    </p>

    <button type="submit">Save</button>
</form:form>

<a href="<c:url value='/tasks'/>">Cancel</a>

</body>
</html>