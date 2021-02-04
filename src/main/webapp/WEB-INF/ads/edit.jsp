<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/LoginNavbar.jsp" />
<div class="container">
    <h1>Edit your ad!</h1>
    <form action="/ads/edit" method="post">
        <div class="form-group">
            <label for="title">New Title</label>
            <input id="title" name="title" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="description">New Description</label>
            <textarea id="description" name="description" class="form-control" type="text"></textarea>
        </div>
        <input type="Submit" class="btn btn-block btn-primary" value="Edit Ad">
    </form>
</div>
</body>
</html>

