package service.category;

import dao.categoryDAO.CategoryDAO;
import model.Category;
import model.Product;

import java.util.List;

public class CategoryService implements ICategoryService{
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public boolean createNew(Product product) {
        return false;
    }

    @Override
    public boolean update(int id, Product product) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List findAll() {
        return categoryDAO.findAll();
    }

    public Category findById(int id) {
        return categoryDAO.findById(id);
    }
}
