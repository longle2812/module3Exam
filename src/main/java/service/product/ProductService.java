package service.product;

import dao.productDAO.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService implements IProductService{
    ProductDAO productDAO = new ProductDAO();

    @Override
    public boolean createNew(Product product) {
        return productDAO.createNew(product);
    }

    @Override
    public boolean update(int id, Product product) {
        return productDAO.update(id, product);
    }

    @Override
    public boolean delete(int id) {
        return productDAO.delete(id);
    }

    @Override
    public List findAll() {
        return productDAO.findAll();
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }

    public List<Product> search(String q) {
        return productDAO.search(q);
    }
}
