<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" /></head>
<body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

<div class="container" style="margin-top: 20px">
    <form class="row g-3" action="/products" method="get">
        <div class="col-auto">
            <a href="?action=create" class="btn btn-success active" role="button"
               aria-pressed="true"><i class="fas fa-plus-square"></i> Add new
                product</a>
        </div>
        <div class="col-auto">
            <input name="q" class="form-control " type="nameSearch" placeholder="Enter book name"
                   aria-label="Search" style="width: 300px;margin-left: 520px;">
        </div>
        <div class="col-auto">
            <button class="btn btn-primary active" type="submit" > <i class="fas fa-search"></i> Search
            </button>
        </div>
    </form>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h4><i class="fas fa-list"></i> Product list</h4>
            <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                    <th>#</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Color</th>
                    <th>Category</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <td>${product.getId()}</td>
                            <td>${product.getName()}</td>
                            <td>${product.getPrice()}</td>
                            <td>${product.getQuantity()}</td>
                            <td>${product.getColor()}</td>
                            <td><c:forEach items="${categories}" var="categories">
                                <c:if test="${categories.getId() == product.getCategory()}"> ${categories.getName()} </c:if>
                            </c:forEach>
                            </td>
                            <td>
                                <a href="?action=edit&productId=${product.getId()}"
                                   class="btn btn-info btn-sm active" role="button" aria-pressed="true"><i class="fas fa-edit"></i></a>
                            </td>
                            <td>
                                <a href="?action=delete&productId=${product.getId()}"
                                   class="btn btn-danger btn-sm active" role="button" aria-pressed="true"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
