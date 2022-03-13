package presentation.listeners;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import bll.exceptions.NotEnoughProductsException;
import model.Client;
import model.Orders;
import model.Product;
import presentation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa pentru definirea listenerului pentru butonul de plasare a unei comenzi
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class PlaceOrderListener implements ActionListener {
    /**
     * Interfata grafica
     */
    private View view;

    /**
     * Buffer pentru scriere in fisier
     */
    private BufferedWriter buf;

    /**
     * Fisierul ce va contine factura generata
     */
    private FileWriter billFile;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public PlaceOrderListener(View view) {
        this.view = view;
    }

    /**
     * Metoda pentru actualizarea interfetei grafice in urma plasarii unei comenzi
     */
    private void refresh() {
        ClientBLL clientBLL = view.getClientView().getClientBLL();
        ProductBLL productBLL = view.getOrderView().getProductBLL();
        view.getProductView().setVisible(false);
        view.setProductView(new ProductView(productBLL));
        view.getProductView().setVisible(true);
        view.getClientView().setVisible(false);
        view.setClientView(new ClientView(clientBLL));
        view.getClientView().setVisible(true);
        view.getClientView().addInsertListener(new presentation.listeners.InsertClientListener(view));
        view.getClientView().addUpdateListener(new presentation.listeners.UpdateClientListener(view));
        view.getClientView().addDeleteListener(new presentation.listeners.DeleteClientListener(view));
        view.getOrderView().updateClients();
        view.getOrderView().setVisible(false);
        view.setOrderView(new OrderView(clientBLL, productBLL));
        view.getOrderView().setVisible(true);
        view.getOrderView().addPlaceActionListener(new presentation.listeners.PlaceOrderListener(view));
        view.getProductView().addInsertListener(new presentation.listeners.InsertProductListener(view));
        view.getProductView().addUpdateListener(new presentation.listeners.UpdateProductListener(view));
        view.getProductView().addDeleteListener(new presentation.listeners.DeleteProductListener(view));
    }

    /**
     * Suprascrierea metodei actionPerformed
     * @param e eveniment - apasarea butonului de plasare a comenzii
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getOrderView().getClientsTable().getContentTable().getSelectedRow() != -1 && view.getOrderView().getProductsTable().getContentTable().getSelectedRow() != -1) {
            Product p = view.getOrderView().getSelectedProduct();
            Client c = view.getOrderView().getSelectedClient();
            int q = view.getOrderView().getDesiredQuantity();
            try {
                if (q > p.getQuantity()) {
                    throw new NotEnoughProductsException();
                }
                Orders orders = new Orders(c.getId(), p.getId(), q);
                OrderBLL orderBLL = new OrderBLL();
                orderBLL.insertOrder(orders);
                System.out.println(orders);
                p.setQuantity(p.getQuantity() - q);
                view.getOrderView().getProductBLL().updateProduct(p);
                try {
                    this.billFile = createBillFile();
                } catch (IOException ex) {
                    System.out.println("Couldn't create log file: " + ex.getMessage());
                    System.exit(1);
                }
                this.buf = new BufferedWriter(billFile);
                computeBill();
                bufferClose();
                refresh();
            } catch (NotEnoughProductsException ex) {
                view.getOrderView().showError(ex.getMessage());
            }

        }
    }

    /**
     * Metoda pentru determinarea totalului de plata
     * @return totalul de plata
     */
    public float computeTotalPrice() {
        try {
            float price = this.view.getOrderView().getSelectedProduct().getPrice();
            int pieces = this.view.getOrderView().getDesiredQuantity();
            return price * pieces;
        } catch (NumberFormatException ex) {
            this.view.getOrderView().showError(ex.getMessage());
        }
        return -1;
    }

    /**
     * Metoda pentru crearea fisierului ce va contine factura
     * @return fisierul ce va contine factura
     * @throws IOException exceptiie de intrare/iesire
     */
    public FileWriter createBillFile() throws IOException {
        FileWriter billFile = new FileWriter(this.view.getOrderView().getSelectedClient().getName() + "-" + this.view.getOrderView().getSelectedProduct().getName() + ".txt");
        return billFile;
    }

    /**
     * Metoda pentru scrierea in fisierul ce contine factura
     */
    public void computeBill() {
        try {
            buf.write(billString());
        } catch (IOException e){
            System.out.println("Couldn't write in the bill file: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Metoda pentru inchiderea bufferului de scriere
     */
    public void bufferClose(){
        try {
            this.buf.close();
        } catch (IOException e) {
            System.out.println("Couldn't close buffer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metoda pentru construirea sirului de caractere ce va fi scris in fiseirul cu factura
     * @return factura sub forma de sir de caractere
     */
    public String billString() {
        String bill = new String("");
        bill = bill + "Client Data\n-------------------------------------------------\n";
        bill = bill + "Name: " + this.view.getOrderView().getSelectedClient().getName() + "\n";
        bill = bill + "Address: " + this.view.getOrderView().getSelectedClient().getAddress() + "\n";
        bill = bill + "E-mail: " + this.view.getOrderView().getSelectedClient().getEmail() + "\n";
        bill = bill + "Age: " + this.view.getOrderView().getSelectedClient().getAge() + "\n";
        bill = bill + "\nProduct Data\n-------------------------------------------------\n";
        bill = bill + "Name: " + this.view.getOrderView().getSelectedProduct().getName() + "\n";
        bill = bill + "Price: " + this.view.getOrderView().getSelectedProduct().getPrice() + "\n";
        bill = bill + "Number of pieces: " + this.view.getOrderView().getDesiredQuantity() + "\n";
        bill = bill + "\nTOTAL VALUE:\n-------------------------------------------------";
        bill = bill + "\n" + computeTotalPrice();
        return bill;
    }
}
