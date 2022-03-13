package bll;

import bll.validators.ProductQuantityValidator;
import dao.ProductDAO;
import model.Product;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa in care s-au implementat operatiile pe obiectul de tip Product
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */
public class ProductBLL {
    /**
     * Componenta ClientDAO pentru mostenirea metodelor din AbstractDAO
     */
    private ProductDAO productDAO;

    /**
     * Validator pentru cantiatea de produse din stoc
     */
    private ProductQuantityValidator productQuantityValidator;

    /**
     * Constructor fara parametri
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
        productQuantityValidator = new ProductQuantityValidator();
    }

    /**
     * Getter pentru validatorul cantitatii disponibile
     * @return validatorul cantitatii disponibile
     */
    public ProductQuantityValidator getProductQuantityValidator() {
        return productQuantityValidator;
    }

    /**
     * Metoda pentru inserarea unui produs in baza de date
     * @param p produsul de inserat
     * @return produsul inserat
     */
    public Product insertProduct(Product p) {
        p.setId(this.productDAO.getMaxId(p) + 1);
        return productDAO.insert(p);
    }

    /**
     * Metoda pentru actualizarea unui produs din baza de date
     * @param p produsul de actualizat
     * @return produsul actualizat
     */
    public Product updateProduct(Product p) {
        return productDAO.update(p);
    }

    /**
     * Metoda pentru selectarea unui produs dupa  id
     * @param id id-ul produsului
     * @return produsul cu id-ul id
     */
    public Product selectById(int id) {
        Product p = this.productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The product with id " + id + "does not exist!");
        }
        return p;
    }

    /**
     * Metoda pentru determinarea tuturor produselor din baza de date
     * @return lista cu toate produsele din baza de date
     */
    public List<Product> findAll() {
        return this.productDAO.findAll();
    }

    /**
     * Metoda pentru stergerea unui produs cu un anumit id
     * @param id id-ul produsului de sters
     */
    public void deleteById(int id) {
        Product p = this.productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The product with id " + id + "does not exist!");
        }
        this.productDAO.deleteById(id);
    }
}
