package presentation.listeners;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import presentation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa pentru definirea listenerului pentru butonul de acrualizare a unui client
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class UpdateClientListener implements ActionListener {
    /**
     * Interfata grafica
     */
    private View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public UpdateClientListener(View view) {
        this.view = view;
    }

    /**
     * Metoda pentru actualizarea interfetei grafice dupa actualizarea unui client
     */
    private void refresh() {
        ClientBLL clientBLL = view.getClientView().getClientBLL();
        view.getClientView().setScrollPane(view.getClientView().updateTable());
        view.getClientView().setVisible(false);
        view.setClientView(new ClientView(clientBLL));
        view.getClientView().setVisible(true);
        ProductBLL productBLL = view.getOrderView().getProductBLL();
        view.getOrderView().updateClients();
        view.getOrderView().setVisible(false);
        view.setOrderView(new OrderView(clientBLL, productBLL));
        view.getOrderView().setVisible(true);
        OrderController oc = new OrderController(new View(new ClientView(new ClientBLL()), new ProductView(new ProductBLL()), new OrderView(new ClientBLL(), new ProductBLL())));
        view.getOrderView().addPlaceActionListener(new presentation.listeners.PlaceOrderListener(view));
        view.getClientView().addInsertListener(new presentation.listeners.InsertClientListener(view));
        view.getClientView().addUpdateListener(new presentation.listeners.UpdateClientListener(view));
        view.getClientView().addDeleteListener(new presentation.listeners.DeleteClientListener(view));
    }

    /**
     * Suprascrierea metodei actionPerformed
     * @param e eveniment - apasarea butonului de actualizare a clientului
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getClientView().getClientsTable().getContentTable().getSelectedRow() != -1) {
            try {
                Client c = view.getClientView().getSelectedClient();
                ClientBLL clientBLL = view.getClientView().getClientBLL();
                clientBLL.getClientAgeValidator().validate(c);
                clientBLL.getEmailValidator().validate(c);
                c.setId(view.getClientView().getClientsTable().getSelectedId());
                clientBLL.updateClient(c);
                refresh();
            } catch (IllegalArgumentException ex) {
                view.getProductView().showError(ex.getMessage());
            }
        }
    }
}
