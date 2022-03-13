package start;

import bll.ClientBLL;
import bll.ProductBLL;
import presentation.*;

/**
 * Clasa principala ce contine o metoda main in care se vor instantia obiectele necesare utilizarii aplicatiei
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 17, 2021
 */
public class MainClass {

    /**
     * Metoda main care permite rularea aplicatiei
     * @param args argumentele din linia de comanda
     */
    public static void main(String[] args) {
        ProductBLL productBLL = new ProductBLL();
        ProductView pv = new ProductView(productBLL);
        pv.setVisible(true);
        ClientBLL clientBLL = new ClientBLL();
        ClientView cv = new ClientView(clientBLL);
        cv.setVisible(true);
        OrderView ov = new OrderView(clientBLL, productBLL);
        ov.setVisible(true);
        View view = new View(cv, pv, ov);
        ClientController cc = new ClientController(view);
        ProductController pc = new ProductController(view);
        OrderController oc = new OrderController(view);
    }
}

