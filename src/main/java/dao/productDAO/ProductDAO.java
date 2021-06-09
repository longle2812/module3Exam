package dao.productDAO;

import dao.SQLConnection;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    public static final String CREATE_NEW_PRODUCT = "insert into products (name, price, amount, color,description,category) values (?,?,?,?,?,?)";
    public static final String UPDATE_PRODUCT = "update products set name=?, price=?,amount=?, color=?,description=?,category=? where id =?";
    public static final String DELETE_PRODUCT = "delete from products where id = ?";
    public static final String SHOW_ALL_PRODUCT = "select * from products";
    public static final String FIND_BY_ID = "select * from products where id = ?";
    public static final String SEARCH_BY_NAME = "select * from products where name like ?";

    @Override
    public boolean createNew(Product product) {
        Connection connection = SQLConnection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory());
            rowInserted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted != 0;
    }

    @Override
    public boolean update(int id, Product product) {
        Connection connection = SQLConnection.getConnection();
        int rowUpdated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory());
            preparedStatement.setInt(7, id);
            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated!=0;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = SQLConnection.getConnection();
        int rowDeleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted!=0;

    }

    @Override
    public List findAll() {
        Connection connection = SQLConnection.getConnection();
        List<Product> products = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                double price = Double.parseDouble(resultSet.getString("price"));
                int quantity = Integer.parseInt(resultSet.getString("amount"));
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int category = Integer.parseInt(resultSet.getString("category"));
                Product product = new Product(id, name, price, quantity, color, description, category);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public Product findById(int id) {
        Connection connection = SQLConnection.getConnection();
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("amount");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int category = resultSet.getInt("category");
                product = new Product(id, name, price, quantity, color, description, category);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public List<Product> search(String q) {
        Connection connection = SQLConnection.getConnection();
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME);
            preparedStatement.setString(1, "%"+q+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("amount");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int category = resultSet.getInt("category");
                Product product = new Product(id, name, price, quantity, color, description, category);
                products.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
