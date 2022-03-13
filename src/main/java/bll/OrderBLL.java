package bll;

import dao.OrderDAO;
import model.Orders;
import java.util.List;

/**
 * Clasa in care s-au implementat operatiile pe obiectul de tip Order
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */
public class OrderBLL {
    /**
     * Componenta ClientDAO pentru mostenirea metodelor din AbstractDAO
     */
    private OrderDAO orderDAO;

    /**
     * Constructor fara parametri
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Metoda pentru inserarea unei comenzi in baza de date
     * @param p comanda de inserat
     * @return comanda inserata
     */
    public Orders insertOrder(Orders p) {
        p.setId(this.orderDAO.getMaxId(p) + 1);
        return orderDAO.insert(p);
    }

    /**
     * Metoda pentru actualizarea unei comenzi din baza de date
     * @param p comanda de actualizat
     * @return comanda actualizata
     */
    public Orders updateOrder(Orders p) {
        return orderDAO.update(p);
    }

    /**
     * Metoda pentru selectarea unei comenzi dupa  id
     * @param id id-ul comenzii
     * @return comanda cu id-ul id
     */
    public Orders selectById(int id) {
        return this.orderDAO.findById(id);
    }

    /**
     * Metoda pentru determinarea tuturor comenzilor din baza de date
     * @return lista cu toate comenzile din baza de date
     */
    public List<Orders> findAll() {
        return this.orderDAO.findAll();
    }

    /**
     * Metoda pentru stergerea unei comenzi dupa id
     * @param id id-ul comenzii de sters
     */
    public void deleteById(int id) {
        this.orderDAO.deleteById(id);
    }
}
