package presentation.listeners;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import presentation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa pentru definirea listenerului pentru butonul de inserare a unui client
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class InsertClientListener implements ActionListener {
    /**
     * Interfata grafica
     */
    private View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public InsertClientListener(View view) {
        this.view = view;
    }

    /**
     * Suprascirerea metodei actionPerformed
     * @param e eveniment - apasarea butonului de inserare a clientului
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Client c = view.getClientView().getNewClient();
            ClientBLL clientBLL = view.getClientView().getClientBLL();
            clientBLL.getClientAgeValidator().validate(c);
            clientBLL.getEmailValidator().validate(c);
            clientBLL.insertClient(c);
            view.getClientView().setScrollPane(view.getClientView().updateTable());
            view.getClientView().setVisible(false);
            view.setClientView(new ClientView(clientBLL));
            view.getClientView().setVisible(true);
            view.getClientView().addInsertListener(new presentation.listeners.InsertClientListener(view));
            view.getClientView().addUpdateListener(new presentation.listeners.UpdateClientListener(view));
            view.getClientView().addDeleteListener(new presentation.listeners.DeleteClientListener(view));
            ProductBLL productBLL = view.getOrderView().getProductBLL();
            view.getOrderView().updateClients();
            view.getOrderView().setVisible(false);
            view.setOrderView(new OrderView(clientBLL, productBLL));
            view.getOrderView().setVisible(true);
            view.getOrderView().addPlaceActionListener(new presentation.listeners.PlaceOrderListener(view));
        } catch (IllegalArgumentException ex) {
            view.getClientView().showError(ex.getMessage());
        }
    }
}
