package dao.categoryDAO;

import dao.SQLConnection;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    @Override
    public boolean createNew(Object o) {
        return false;
    }

    @Override
    public boolean update(int id, Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List findAll() {
        Connection connection = SQLConnection.getConnection();
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
            ResultSet resultset = preparedStatement.executeQuery();
            while(resultset.next()){
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    public Category findById(int id) {
        Connection connection = SQLConnection.getConnection();
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                category = new Category(id,name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }
}
