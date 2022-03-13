package presentation.listeners;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Product;
import presentation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa pentru definirea listenerului pentru butonul de inserare a unui produs
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class InsertProductListener implements ActionListener {
    /**
     * Interfata grafica
     */
    private View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public InsertProductListener(View view) {
        this.view = view;
    }

    /**
     * Suprascirerea metodei actionPerformed
     * @param e eveniment - apasarea butonului de inserare a produsului
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Product p = view.getProductView().getNewProduct();
            ProductBLL productBLL = view.getProductView().getProductBLL();
            productBLL.getProductQuantityValidator().validate(p);
            productBLL.insertProduct(p);
            view.getProductView().setScrollPane(view.getProductView().updateTable());
            view.getProductView().setVisible(false);
            view.setProductView(new ProductView(productBLL));
            view.getProductView().setVisible(true);
            ClientBLL clientBLL = view.getOrderView().getClientBLL();
            view.getOrderView().updateProducts();
            view.getOrderView().setVisible(false);
            view.setOrderView(new OrderView(clientBLL, productBLL));
            view.getOrderView().setVisible(true);
            view.getOrderView().addPlaceActionListener(new presentation.listeners.PlaceOrderListener(view));
            view.getProductView().addInsertListener(new presentation.listeners.InsertProductListener(view));
            view.getProductView().addUpdateListener(new presentation.listeners.UpdateProductListener(view));
            view.getProductView().addDeleteListener(new presentation.listeners.DeleteProductListener(view));
        } catch (IllegalArgumentException ex) {
            view.getProductView().showError(ex.getMessage());
        }
    }
}
