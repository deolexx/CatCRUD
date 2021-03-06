<%--
  Created by IntelliJ IDEA.
  User: Deo
  Date: 27.10.2021
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cat Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header class="back_ground">
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #ADC2A9">
        <div>
            <a href="https://github.com/deolexx/CatCRUD" class="navbar-brand"> Cat
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">CatsList</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">


    <div class="container">
        <h3 class="text-center">List of Cats</h3>
        <hr>
        <div class="container text-right">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Cat</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead class="back_ground">
            <tr>
                <th>ID</th>
                <th>price</th>
                <th>breed</th>
                <th>Seller Name</th>
                <th>Seller Phone</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="cat" items="${listCat}">

                <tr>
                    <td><c:out value="${cat.id}"/></td>
                    <td><c:out value="${cat.price}"/></td>
                    <td><c:out value="${cat.breed}"/></td>
                    <td><c:out value="${cat.seller_name}"/></td>
                    <td><c:out value="${cat.seller_phone}"/></td>
                    <td><a href="edit?id=<c:out value='${cat.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${cat.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
