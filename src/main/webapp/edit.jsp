<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
<h3> Edit product</h3>
<c:if test="${message == 'success'}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Congratulation!</strong> Added successfully
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<c:if test="${message == 'error'}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Ops</strong> Something wrong
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<form style="margin-left: 20px" action="?action=edit&productId=${product.getId()}" method="post">
    <div class="form-group">
        <label for="exampleInputEmail1">Name</label>
        <input name="name" type="text" class="form-control" id="exampleInputEmail1" placeholder="${product.getName()}">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Price</label>
        <input name="price" type="number" class="form-control" id="exampleInputPassword1" placeholder="${product.getPrice()}">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Quantity</label>
        <input name="quantity" type="number" class="form-control" id="quantity" placeholder="${product.getQuantity()}">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Color</label>
        <input name="color" type="text" class="form-control" id="color" placeholder="${product.getColor()}">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Description</label>
        <input name="description" type="text" class="form-control" id="description" placeholder="${product.getDescription()}">
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Category</label>
        <select  name="category" class="form-control" id="dropDownList" name="dropDownList">
            <c:forEach var="categories" items="${categories}">
                <option <c:if test="${product.getCategory() == categories.getId()}"> selected="selected" </c:if> value="${categories.getId()}"> ${categories.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <button type="submit" class="btn btn-primary">Edit</button>
        <a href="/products" class="btn btn-info active" role="button" aria-pressed="true">Back</a>
    </div>
</form>
</body>
</html>
