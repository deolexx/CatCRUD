<%--
  Created by IntelliJ IDEA.
  User: Deo
  Date: 27.10.2021
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cat Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #ADC2A9">
        <div>
            <a href="https://github.com/deolexx/CatCRUD" class="navbar-brand"> Cat Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Back to List</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${cat != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${cat == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${cat != null}">
                                Edit Cat
                            </c:if>
                            <c:if test="${cat == null}">
                                Add New Cat
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${cat != null}">
                        <input type="hidden" name="id" value="<c:out value='${cat.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Cat Price</label> <input type="text"
                                                        value="<c:out value='${cat.price}' />" class="form-control"
                                                        name="price" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Cat Breed</label> <input type="text"
                                                        value="<c:out value='${cat.breed}' />" class="form-control"
                                                        name="breed">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Cat Seller</label> <input type="text"
                                                          value="<c:out value='${cat.seller_name}' />" class="form-control"
                                                          name="seller_name">
                    </fieldset> <fieldset class="form-group">
                        <label>Seller Phone</label> <input type="text"
                                                          value="<c:out value='${cat.seller_phone}' />" class="form-control"
                                                          name="seller_phone">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>