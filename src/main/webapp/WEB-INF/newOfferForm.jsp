<%-- 
    Document   : newOfferForm
    Created on : Oct 22, 2018, 10:57:28 PM
    Author     : Milad
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--<link rel="icon" href="../../../../favicon.ico">-->

        <title>Food Sharing Platform</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">foodie</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">My Dashboard</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>

        <main role="main" class="container">
            <h1>Make an Offer</h1>

            <c:if test="${not empty requestScope.errors}">
                <c:forEach items="${requestScope.errors}" var="error"> 
                    <p class="alert alert-danger" role="alert">Please correct this error: ${error.propertyPath} ${error.message}</p>
                </c:forEach>
            </c:if>

            <form action="/msabouri-fp/OfferServlet" method="post">
                <div class="form-group">
                    <label for="txtTitle">Title</label>
                    <input value="${requestScope.offer.title}" type="text" id="txtTitle" name="title" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="txtDescription">Description</label>
                    <input value="${requestScope.offer.description}" type="text" id="txtDescription" name="description" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="txtRecipe">Price</label>
                    <input value="${requestScope.offer.unitPrice}" type="text" id="txtPrice" name="price" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input value="${requestScope.offer.quantity}" type="text" id="txtQuantity" name="quantity" class="form-control"/>
                </div>
                <button class="btn btn-primary" type="submit">Submit</button>
                <!--<input type="submit" value="Submit me">-->
            </form>
        </main>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
