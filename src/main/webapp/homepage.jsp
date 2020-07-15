<%--
  Created by IntelliJ IDEA.
  User: vitpt
  Date: 7/13/2020
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css"/>" type="text/css"/>--%>
</head>
<body>

<div class="container">
    <div class="card-header">
        <c:url var="homeLink" value="DispatcherServlet">
        </c:url>
        <a href="${homeLink}" class="text-decoration-none"><h5 class="text-danger">Welcome Wibu 💘 💘 💘 </h5>
        </a>
        <form class="form-inline" action="SearchServlet">
            <div class="form-group mx-sm-3 mb-2">
                <label for="inputSearchValue" class="sr-only">Anime</label>
                <input type="text" class="form-control" id="inputSearchValue" name="txtSearchValue"
                       placeholder="Tìm: tên anime...">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Tìm kiếm</button>
        </form>
    </div>
    <div class="card">
        <div class="card-header">
            <h6>Danh sách Anime</h6>
            <form action="StatisticServlet">
            <select name="season">
                <option value=""></option>
                <option value="Xuân">Xuân</option>
                <option value="Thu">Thu</option>
            </select>
                <input type="submit" value="Statistic"/>
            </form>
        </div>

        <div class="row row-cols-1 row-cols-md-3">
            <c:set var="listMovie" value="${sessionScope.MOVIES}"/>
            <c:forEach var="movie" items="${listMovie}">
                <c:url var="detailLink" value="ViewDetailServlet">
                    <c:param name="movieId" value="${movie.movieId}"/>
                </c:url>
                <a href="${detailLink}" class="text-decoration-none">
                    <div class="col mb-4">
                        <div class="card">
                            <img src="${movie.image}" class="card-img-top" height="306.63">
                            <div class="card-body">
                                <h6 class="card-title text-primary">${movie.movieName}</h6>
                                <p class="card-text text-danger">Interact Point: ${movie.interactPoint.toString()}</p>
                                <input type="hidden" value="${movie.movieId}">
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>


</body>
</html>
