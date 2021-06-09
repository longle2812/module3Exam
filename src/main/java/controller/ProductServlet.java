package controller;

import model.Category;
import model.Product;
import service.category.CategoryService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    CategoryService categoryService = new CategoryService();
    List<Category> categories = this.categoryService.findAll();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createFrom(request, response);
                break;
            case "edit":
                editForm(request, response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            default:
                showAllProduct(request, response);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productId"));
        this.productService.delete(id);
        showAllProduct(request,response);

    }

    private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productId"));
        request.setAttribute("categories", categories);
        Product product = this.productService.findById(id);
        String categoryName = this.categoryService.findById(product.getCategory()).getName();
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("product", product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = this.productService.findAll();
        String q = request.getParameter("q");
        if (q!= null){
            products = this.productService.search(q);
        }
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addNewProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productId"));
        Product product = this.productService.findById(id);
        String name = request.getParameter("name");
        if (name.equals("")) {
            name = product.getName();
        }
        String price = request.getParameter("price");
        double price1;
        if (price.equals("")) {
            price1 = product.getPrice();
        } else {
            price1 = Double.parseDouble(price);
        }
        String quantity = request.getParameter("quantity");
        int quantity1;
        if (quantity.equals("")) {
            quantity1 = product.getQuantity();
        } else {
            quantity1 = Integer.parseInt(quantity);
        }
        String color = request.getParameter("color");
        if (color.equals("")){
            color = product.getColor();
        }
        String description = request.getParameter("description");
        if (description.equals("")){
            description = product.getDescription();
        }
        int category = Integer.parseInt(request.getParameter("category"));
        product = new Product(name, price1, quantity1, color, description, category);
        if (this.productService.update(id, product)){
            request.setAttribute("message", "success");
        } else request.setAttribute("message", "error");
        String categoryName = this.categoryService.findById(category).getName();
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit.jsp");
        requestDispatcher.forward(request, response);

    }

    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product = new Product(name, price, quantity, color, description, category);
        if (this.productService.createNew(product)) {
            request.setAttribute("message", "success");
        } else request.setAttribute("message", "error");
        request.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(request, response);
    }

}
