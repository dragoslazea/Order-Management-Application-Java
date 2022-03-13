package presentation;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;
import javax.swing.*;
import java.awt.*;

/**
 * Clasa pentru fereastra din interfata grafica dedicata produsului
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class ProductView extends JFrame {
    /**
     * Panoul de continut
     */
    private JPanel contentPanel = new JPanel();

    /**
     * Eticheta de titlu
     */
    private JLabel titleLabel = new JLabel("Products");

    /**
     * Eticheta pentru sectiunea de inserare a unui nou produs
     */
    private JLabel registerLabel = new JLabel("Register a new product");

    /**
     * Eticheta pentru numele produslui
     */
    private JLabel nameLabel = new JLabel("Name: ");

    /**
     * Eticheta pentru pretul produslui
     */
    private JLabel priceLabel = new JLabel("Price: ");

    /**
     * Eticheta pentru cantitatea disponibila
     */
    private JLabel availableLabel = new JLabel("Available quantity: ");

    /**
     * Eticheta pentru sectiunea de afisare a tuturor clientilor
     */
    private JLabel allProductLabel = new JLabel("All products ");

    /**
     * Camp de text pentru intoducerea numelui produslui de inserat
     */
    private JTextField nameTxtField = new JTextField();

    /**
     * Camp de text pentru intoducerea pretului produslui de inserat
     */
    private JTextField priceTxtField = new JTextField();

    /**
     * Camp de text pentru intoducerea cantitatii disponibile a produslui de inserat
     */
    private JTextField availableTxtField = new JTextField();

    /**
     * Buton pentru inserarea unui nou produs
     */
    private JButton btnInsert = new JButton("Register product");

    /**
     * Buton pentru actualizarea produsului selectat din tabela de produse
     */
    private JButton btnUpdate = new JButton("Edit product");

    /**
     * Buton pentru stergerea produsului selectat din tabela de produse
     */
    private JButton btnDelete = new JButton("Delete product");

    /**
     * Tabela de produse
     */
    private ProductsTable productsTable = new ProductsTable();

    /**
     * Obiect pentru operatiile de tip produs
     */
    private ProductBLL productBLL;

    /**
     * Panoul de derulare pentru tabelul cu produse
     */
    private JScrollPane scrollPane;

    /**
     * Constructor
     * @param productBLL obiect pentru operatiile pe obiecte de tip produs
     */
    public ProductView(ProductBLL productBLL) {
        this.productBLL = productBLL;
        this.setTitle("Products");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(900, 580));
        this.setLocation(980, 15);
        this.setContentPane(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(118,181,197));

        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(128, 57, 30));
        titleLabel.setAlignmentX(0.5f);
        contentPanel.add(titleLabel);

        this.registerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        this.registerLabel.setForeground(new Color(128, 57, 30));
        this.registerLabel.setAlignmentX(0.5f);
        contentPanel.add(registerLabel);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBackground(new Color(118, 181, 197));
        this.nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.nameLabel.setForeground(new Color(128, 57, 30));
        this.nameLabel.setAlignmentX(0.5f);
        this.nameTxtField.setBackground(new Color(128, 57, 30));
        this.nameTxtField.setForeground(new Color(118, 181, 197));
        this.nameTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.nameTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.nameTxtField.setHorizontalAlignment(JTextField.CENTER);
        namePanel.add(nameLabel);
        namePanel.add(nameTxtField);
        contentPanel.add(namePanel);

        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
        pricePanel.setBackground(new Color(118, 181, 197));
        this.priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.priceLabel.setForeground(new Color(128, 57, 30));
        this.priceLabel.setAlignmentX(0.5f);
        this.priceTxtField.setBackground(new Color(128, 57, 30));
        this.priceTxtField.setForeground(new Color(118, 181, 197));
        this.priceTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.priceTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.priceTxtField.setHorizontalAlignment(JTextField.CENTER);
        pricePanel.add(priceLabel);
        pricePanel.add(priceTxtField);
        contentPanel.add(pricePanel);

        JPanel availablePanel = new JPanel();
        availablePanel.setLayout(new BoxLayout(availablePanel, BoxLayout.Y_AXIS));
        availablePanel.setBackground(new Color(118, 181, 197));
        this.availableLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.availableLabel.setForeground(new Color(128, 57, 30));
        this.availableLabel.setAlignmentX(0.5f);
        this.availableTxtField.setBackground(new Color(128, 57, 30));
        this.availableTxtField.setForeground(new Color(118, 181, 197));
        this.availableTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.availableTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.availableTxtField.setHorizontalAlignment(JTextField.CENTER);
        availablePanel.add(availableLabel);
        availablePanel.add(availableTxtField);
        contentPanel.add(availablePanel);

        btnInsert.setBackground(new Color(128, 57, 30));
        btnInsert.setForeground(new Color(118, 181, 197));
        btnInsert.setFont(new Font("Arial", Font.BOLD, 20));

        this.allProductLabel.setFont(new Font("Arial", Font.BOLD, 26));
        this.allProductLabel.setForeground(new Color(128, 57, 30));
        this.allProductLabel.setAlignmentX(0.5f);
        contentPanel.add(allProductLabel);

        //productsTable.generateTable(productBLL.findAll());
        scrollPane = updateTable();
        contentPanel.add(scrollPane);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(118, 181, 197));

        btnUpdate.setBackground(new Color(128, 57, 30));
        btnUpdate.setForeground(new Color(118, 181, 197));
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 20));

        btnDelete.setBackground(new Color(128, 57, 30));
        btnDelete.setForeground(new Color(118, 181, 197));
        btnDelete.setFont(new Font("Arial", Font.BOLD, 20));

        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnInsert);
        contentPanel.add(btnPanel);
    }

    /**
     * Getter pentru tabelul cu produse
     * @return tabelul cu produse
     */
    public ProductsTable getProductsTable() {
        return productsTable;
    }

    /**
     * Getter pentru productBLL
     * @return obiect pentru operatiile pe obiecte de tip client
     */
    public ProductBLL getProductBLL() {
        return productBLL;
    }

    /**
     * getter pentru panoul de derulare
     * @return panoul de derulare pentru tabelul cu clienti
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    /**
     * Setter pentru panoul de derulare
     * @param scrollPane noul panou de derulare
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Metoda pentru determinarea produslui selectat din tabel
     * @return produsul selectat din tabel, null daca nu a fost selectat niciun produs
     */
    public Product getSelectedProduct() {
        int row = this.productsTable.getContentTable().getSelectedRow();
        String name = (String)this.productsTable.getContentTable().getValueAt(row, 1);
        String strPrice = (String)this.productsTable.getContentTable().getValueAt(row, 2);
        String strQuantity = (String)this.productsTable.getContentTable().getValueAt(row, 3);
        float price = Float.parseFloat(strPrice);
        int quantity = Integer.parseInt(strQuantity);
        return new Product(name, price, quantity);
    }

    /**
     * Getter pentru noul produs (de inserat)
     * @return noul produs (de inserat)
     */
    public Product getNewProduct() {
        return new Product(nameTxtField.getText(), Float.parseFloat(priceTxtField.getText()), Integer.parseInt(availableTxtField.getText()));
    }

    /**
     * Metoda pentru actualizarea tabelului cu produse in urma operatiilor efectuate
     * @return noul panou de derulare impreuna cu tabelul cu produse
     */
    public JScrollPane updateTable() {
        productsTable.generateTable(productBLL.findAll());
        productsTable.getContentTable().setEnabled(true);
        JScrollPane scroll = new JScrollPane(productsTable.getContentTable());
        return scroll;
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de inserare a unui nou produs
     * @param listener listener pentru butonul de inserare a unui nou produs
     */
    public void addInsertListener(presentation.listeners.InsertProductListener listener) {
        this.btnInsert.addActionListener(listener);
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de actualiare a unui produs
     * @param listener listener pentru butonul de actualizare a unui produs
     */
    public void addUpdateListener(presentation.listeners.UpdateProductListener listener) {
        this.btnUpdate.addActionListener(listener);
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de stergere a unui produs
     * @param listener listener pentru butonul de stergere a unui produs
     */
    public void addDeleteListener(presentation.listeners.DeleteProductListener listener) {
        this.btnDelete.addActionListener(listener);
    }

    /**
     * Metoda pentru afisarea unei ferestre cu un mesaj de eroare
     * @param errMessage mesajul de eroare
     */
    public void showError(String errMessage){
        JOptionPane.showMessageDialog(this, errMessage);
    }

}
