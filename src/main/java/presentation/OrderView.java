package presentation;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import javax.swing.*;
import java.awt.*;

/**
 * Clasa pentru fereastra din interfata grafica dedicata comenzilor
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class OrderView extends JFrame {
    /**
     * Panoul de continut
     */
    private JPanel contentPanel = new JPanel();

    /**
     * Eticheta pentru titlul ferestrei
     */
    private JLabel orderLabel = new JLabel("Place an order");

    /**
     * Eticheta pentru sectiunea de selectare a unui client
     */
    private JLabel clientLabel = new JLabel("Select a client");

    /**
     * Eticheta pentru sectiunea de selectare a unui produs
     */
    private JLabel productLabel = new JLabel("Select a product");

    /**
     * Eticheta pentru sectiunea de introducere a cantitatii dorite de catre client
     */
    private JLabel quantityLabel = new JLabel("Desired quantiy:");

    /**
     * Camp de text pentru introducerea cantitatii dorite de catre client
     */
    private JTextField quantityTxtField = new JTextField();

    /**
     * Buton pentru plasarea comenzii
     */
    private JButton btnPlace = new JButton("Place order");

    /**
     * Obiect pentru operatiile pe client
     */
    private ClientBLL clientBLL;

    /**
     * Obiect pentru operatiile pe produs
     */
    private ProductBLL productBLL;

    /**
     * Tabelul cu produse
     */
    private ProductsTable productsTable = new ProductsTable();

    /**
     * Tabelul cu clienti
     */
    private ClientsTable clientsTable = new ClientsTable();

    /**
     * Panoul de derulare pentru clienti
     */
    private JScrollPane clientScrollPane;

    /**
     * Constructor
     * @param clientBLL obiect pentru operatiile pe client
     * @param productBLL obiect pentru operatiile pe produs
     */
    public OrderView(ClientBLL clientBLL, ProductBLL productBLL) {
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;
        this.setTitle("Orders");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(1000, 405));
        this.setLocation(420, 610);
        this.setContentPane(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(118,181,197));

        orderLabel.setFont(new Font("Arial", Font.BOLD, 32));
        orderLabel.setForeground(new Color(128, 57, 30));
        orderLabel.setAlignmentX(0.5f);
        contentPanel.add(orderLabel);

        JPanel tablesPanel = new JPanel();
        tablesPanel.setBackground(new Color(118,181,197));
        tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.X_AXIS));

        JPanel clientsPanel = new JPanel();
        clientsPanel.setLayout(new BoxLayout(clientsPanel, BoxLayout.Y_AXIS));
        clientsPanel.setBackground(new Color(118,181,197));
        this.clientLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.clientLabel.setForeground(new Color(128, 57, 30));
        this.clientLabel.setAlignmentX(0.5f);
        clientsTable.generateTable(clientBLL.findAll());
        clientScrollPane = updateClients();
        clientsPanel.add(clientLabel);
        clientsPanel.add(clientScrollPane);

        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setBackground(new Color(118,181,197));
        this.productLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.productLabel.setForeground(new Color(128, 57, 30));
        this.productLabel.setAlignmentX(0.5f);
        productsTable.generateTable(productBLL.findAll());
        JScrollPane productScrollPane = new JScrollPane(productsTable.getContentTable());
        productScrollPane.setBackground(new Color(118, 181, 197));
        productsPanel.add(productLabel);
        productsPanel.add(productScrollPane);

        tablesPanel.add(clientsPanel);
        tablesPanel.add(productsPanel);

        contentPanel.add(tablesPanel);

        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.Y_AXIS));
        quantityPanel.setBackground(new Color(118, 181, 197));
        this.quantityLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.quantityLabel.setForeground(new Color(128, 57, 30));
        this.quantityLabel.setAlignmentX(0.5f);
        this.quantityTxtField.setBackground(new Color(128, 57, 30));
        this.quantityTxtField.setForeground(new Color(118, 181, 197));
        this.quantityTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.quantityTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.quantityTxtField.setHorizontalAlignment(JTextField.CENTER);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantityTxtField);
        contentPanel.add(quantityPanel);

        btnPlace.setBackground(new Color(128, 57, 30));
        btnPlace.setForeground(new Color(118, 181, 197));
        btnPlace.setFont(new Font("Arial", Font.BOLD, 20));
        btnPlace.setAlignmentX(0.5f);
        contentPanel.add(btnPlace);
    }

    /**
     * Getter pentru clientBLL
     * @return clientBLL
     */
    public ClientBLL getClientBLL() {
        return clientBLL;
    }

    /**
     * Getter pentru tabelul cu produse
     * @return tabelul cu produse
     */
    public ProductsTable getProductsTable() {
        return productsTable;
    }

    /**
     * Getter pentru tabelul cu clienti
     * @return tabelul cu clienti
     */
    public ClientsTable getClientsTable() {
        return clientsTable;
    }

    /**
     * Setter pentru clientBLL
     * @param clientBLL noul clientBLL
     */
    public void setClientBLL(ClientBLL clientBLL) {
        this.clientBLL = clientBLL;
    }

    /**
     * Getter pentru clientBLL
     * @return clientBLL
     */
    public ProductBLL getProductBLL() {
        return productBLL;
    }

    /**
     * Metoda pentru actualizarea tabelului cu clienti
     * @return panoul de derulare cu noul tabel cu clienti
     */
    public JScrollPane updateClients() {
        clientsTable.generateTable(clientBLL.findAll());
        clientsTable.getContentTable().setEnabled(true);
        JScrollPane scroll = new JScrollPane(clientsTable.getContentTable());
        return  scroll;
    }

    /**
     * Metoda pentru actualizarea tabelului cu produse
     * @return panoul de derulare cu noul tabel cu produse
     */
    public JScrollPane updateProducts() {
        productsTable.generateTable(productBLL.findAll());
        productsTable.getContentTable().setEnabled(true);
        JScrollPane scroll = new JScrollPane(productsTable.getContentTable());
        return  scroll;
    }

    /**
     * Getter pentru id-ul clientului selectat
     * @return id-ul clientului selectat
     */
    public int getIdClient() {
        return this.clientsTable.getSelectedId();
    }

    /**
     * Getter pentru id-ul produsului selectat
     * @return id-ul produsului selectat
     */
    public int getIdProduct() {
        return this.productsTable.getSelectedId();
    }

    /**
     * Getter pentru cantitatea dorita
     * @return cantitatea dorita
     */
    public int getQuantity() {
        return Integer.parseInt(quantityTxtField.getText());
    }

    /**
     * Getter pentru cereea inregistrata
     * @return comanda inregistrata
     */
    public Orders getOrder() {
        return new Orders(getIdClient(), getIdProduct(), getQuantity());
    }

    /**
     * Metoda pentru determinarea produsului selectat
     * @return produsul selectat
     */
    public Product getSelectedProduct() {
        int row = this.productsTable.getContentTable().getSelectedRow();
        String name = (String)this.productsTable.getContentTable().getValueAt(row, 1);
        String strPrice = (String)this.productsTable.getContentTable().getValueAt(row, 2);
        String strQuantity = (String)this.productsTable.getContentTable().getValueAt(row, 3);
        float price = Float.parseFloat(strPrice);
        int quantity = Integer.parseInt(strQuantity);
        return new Product(this.productsTable.getSelectedId(), name, price, quantity);
    }

    /**
     * Metoda pentru determinarea clientului selectat
     * @return clientul selectat
     */
    public Client getSelectedClient() {
        int row = this.clientsTable.getContentTable().getSelectedRow();
        String name = (String)this.clientsTable.getContentTable().getValueAt(row, 1);
        String address = (String)this.clientsTable.getContentTable().getValueAt(row, 2);
        String email = (String)this.clientsTable.getContentTable().getValueAt(row, 3);
        String strAge = (String)this.clientsTable.getContentTable().getValueAt(row, 4);
        int age = Integer.parseInt(strAge);
        return new Client(this.clientsTable.getSelectedId(), name, address, email, age);
    }

    /**
     * Metoda pentru determinarea cantitatii dorite
     * @return cantitatea dorita de catre client
     */
    public int getDesiredQuantity() {
        try {
            return Integer.parseInt(quantityTxtField.getText());
        } catch(NumberFormatException ex) {
            this.showError(ex.getMessage());
        }
        return 0;
    }

    /**
     * Metoda pentru afisarea unei ferestre cu un mesaj de eroare
     * @param errMessage mesajul de eroare
     */
    public void showError(String errMessage){
        JOptionPane.showMessageDialog(this, errMessage);
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de plasare a comenzii
     * @param listener listener pentru butonul de plasare a comenzii
     */
    public void addPlaceActionListener(presentation.listeners.PlaceOrderListener listener) {
        this.btnPlace.addActionListener(listener);
    }

//    public static void main(String[] args) {
//        ProductBLL productBLL = new ProductBLL();
//        ProductView v = new ProductView(productBLL);
//        v.setVisible(true);
//        ClientBLL clientBLL = new ClientBLL();
//        ClientView cv = new ClientView(clientBLL);
//        cv.setVisible(true);
//        OrderView ov = new OrderView(clientBLL, productBLL);
//        ov.setVisible(true);
//        View view = new View(cv, v, ov);
//        ClientController cc = new ClientController(view);
//        ProductController pc = new ProductController(view);
//        OrderController oc = new OrderController(view);
//    }


}
