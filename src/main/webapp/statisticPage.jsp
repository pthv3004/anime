<%--
  Created by IntelliJ IDEA.
  User: vitpt
  Date: 7/15/2020
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Statistic Page</title>
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
                       value="${param.txtSearchValue}"
                       placeholder="Tìm: tên anime...">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Tìm kiếm</button>
        </form>
    </div>
    <div class="card">
        <div class="card-header">
            <h6>Thống kê category qua các mùa</h6>
            <form action="StatisticServlet">
                <select name="season">
                    <option value=""></option>
                    <option value="Xuân">Xuân</option>
                    <option value="Thu">Thu</option>
                </select>
                <input type="submit" value="Statistic"/>
            </form>
        </div>

        <div class="card-body">
            <c:set var="listMovie" value="${sessionScope.MOVIES}"/>

            <a href="${detailLink}" class="text-decoration-none">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Category</th>
                        <th scope="col">Interact Point</th>
                        <th scope="col">Season </th>
                    </tr>
                    </thead>
                    <c:forEach var="movie"  varStatus="counter" items="${listMovie}">
                        <c:url var="detailLink" value="ViewDetailServlet">
                            <c:param name="movieId" value="${movie.movieId}"/>
                        </c:url>
                        <tbody>
                        <tr>
                            <th scope="row">${counter.count}</th>
                            <td>${movie.categories.split(",")[0]}</td>
                            <td>${movie.interactPoint}</td>
                            <td>${movie.season}</td>
                        </tr>
                        </tbody>
                    </c:forEach>

                </table>
            </a>
            <c:set var="listStatistic" value="${sessionScope.STATISTICS}"/>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Category</th>
                    <th scope="col">Season</th>
                    <th scope="col">VIEW </th>
                </tr>
                </thead>
                <c:forEach var="statistic"  varStatus="sCounter" items="${listStatistic}">
                    <tbody>
                    <tr>
                        <th scope="row">${sCounter.count}</th>
                        <td>${statistic[0]}</td>
                        <td>${statistic[1]}</td>
                        <td>${statistic[2]}</td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
