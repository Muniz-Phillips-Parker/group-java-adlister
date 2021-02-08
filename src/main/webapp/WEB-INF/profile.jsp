<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>

<body>

    <jsp:include page="/WEB-INF/partials/LoginNavbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
    </div>


<div class="container">
    <a href="ads/create"><button class="btn btn-primary btn-block">Create a new ad</button></a>
    <br>
    <a href="ads"><button class="btn btn-primary btn-block">View all ads</button></a>

    <%--&lt;%&ndash;      displays users ads--Jordy's snippet here. %>--%>

<%--&lt;%&ndash;      displays users ads--Jordy's snippet here. %>--%>

    <h1>Here are all the ads you posted!</h1>
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <form>
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>

<%--                ATTEMPT TO PULL AD TO EDIT SERVLET--%>
                <a href="<c:url value="ads/edit" >
    <c:param name="id" value="${ad.id}"/>
    </c:url>">Edit</a>

<%--    /////////////////////////////////////////////////////--%>
<%--                Deleting the ad feature--%>
<%--    /////////////////////////////////////////////////////--%>
                <a href="<c:url value="ads/delete" >
    <c:param name="id" value="${ad.id}"/>
    </c:url>">Delete</a>

<%--                <a class="btn btn-primary deletebtn" id="deletebtn" href="/ads/delete?id=${ad.id}">Delete</a>--%>

<%--                <input type="button" value="Edit" id="Edit" class =" btn btn-secondary" onclick="window.location='ads/edit';">--%>
<%--                <input type="button" value="Delete" id="Delete" class =" btn btn-secondary">--%>
            </form>
        </div>
    </c:forEach>
</div>

</body>
<footer>
    <div class="container">
        <a href="/updateProfile/${user.id}"><button class="btn btn-primary btn-block">Edit Profile</button></a>
        <br>
        <a href="delete/${user.id}"><button class="btn btn-primary btn-block">Delete Profile</button></a>

    </div>
</footer>
</html>

