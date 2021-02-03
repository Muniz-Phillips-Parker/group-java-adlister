<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>What is the ad you are searching for?</h1>
    <form action="/search_ads" method="POST">
        <div class="form-group">
            <label for="searched_ad">search</label>
            <input id="searched_ad" name="searched_ad" class="form-control" type="text">
        </div>
        <input type="submit" class="btn btn-primary btn-block" value="Log In">
    </form>
</div>
</body>
</html>