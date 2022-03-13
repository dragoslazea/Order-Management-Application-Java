package presentation.listeners;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import presentation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa pentru definirea listenerului pentru butonul de stergere a unui client
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class DeleteClientListener implements ActionListener {
    /**
     * Interfata grafica
     */
    private View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public DeleteClientListener(View view) {
        this.view = view;
    }

    /**
     * Metoda pentru actualizarea itnerefetei grafice dupa efectuarea unei stergeri a unui client
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
        view.getOrderView().addPlaceActionListener(new presentation.listeners.PlaceOrderListener(view));
        view.getClientView().addInsertListener(new presentation.listeners.InsertClientListener(view));
        view.getClientView().addUpdateListener(new presentation.listeners.UpdateClientListener(view));
        view.getClientView().addDeleteListener(new presentation.listeners.DeleteClientListener(view));
    }

    /**
     * Suprascirerea metodei actionPerformed
     * @param e eveniment - apasarea butonului de stergere a clientului
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getClientView().getClientsTable().getContentTable().getSelectedRow() != -1) {
            try {
                Client c = view.getClientView().getSelectedClient();
                ClientBLL clientBLL = view.getClientView().getClientBLL();
                c.setId(view.getClientView().getClientsTable().getSelectedId());
                clientBLL.deleteById(c.getId());
                refresh();
            } catch (IllegalArgumentException ex) {
                view.getClientView().showError(ex.getMessage());
            }
        }
    }
}
