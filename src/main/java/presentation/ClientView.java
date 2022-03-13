package presentation;

import bll.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;

/**
 * Clasa pentru fereastra din interfata grafica dedicata clientului
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class ClientView extends JFrame {

    /**
     * Panoul de continut
     */
    private JPanel contentPanel = new JPanel();

    /**
     * Eticheta pentru titlu
     */
    private JLabel titleLabel = new JLabel("Clients");

    /**
     * Eticheta pentru sectiunea de inregistrare a unui nou client
     */
    private JLabel registerLabel = new JLabel("Register a new client");

    /**
     * Eticheta pentru numele clientului
     */
    private JLabel nameLabel = new JLabel("Name: ");

    /**
     * Eticheta pentru adresa clientului
     */
    private JLabel addressLabel = new JLabel("Address: ");

    /**
     * Eticheta pentru emailul clientului
     */
    private JLabel emailLabel = new JLabel("Email: ");

    /**
     * Eticheta pentru varsta clientului
     */
    private JLabel ageLabel = new JLabel("Age: ");

    /**
     * Eticheta pentru seciunea in care sunt afisati toti clientii
     */
    private JLabel allClientsLabel = new JLabel("All clients ");

    /**
     * Camp de text pentru completarea numelui noului client
     */
    private JTextField nameTxtField = new JTextField();

    /**
     * Camp de text pentru completarea adresei noului client
     */
    private JTextField addressTxtField = new JTextField();

    /**
     * Camp de text pentru completarea emailului noului client
     */
    private JTextField emailTxtField = new JTextField();

    /**
     * Camp de text pentru completarea varstei noului client
     */
    private JTextField ageTxtField = new JTextField();

    /**
     * Buton pentru inserarea unui nou client
     */
    private JButton btnInsert = new JButton("Register client");

    /**
     * Buton pentru actualizarea informatiilor despre un client existent
     */
    private JButton btnUpdate = new JButton("Edit client");

    /**
     * Buton pentru stergerea unui client existent
     */
    private JButton btnDelete = new JButton("Delete client");

    /**
     * Tabelul cu client
     */
    private ClientsTable clientsTable = new ClientsTable();

    /**
     * Obiect pentru operatiile de tip client
     */
    private ClientBLL clientBLL;

    /**
     * Panou de derulare pentru tabelul cu clienti
     */
    private JScrollPane scrollPane;

    /**
     * Constructor
     * @param clientBLL obiect pentru operatiile definite asupra obiectului client
     */
    public ClientView(ClientBLL clientBLL) {
        this.clientBLL = clientBLL;
        this.setTitle("Clients");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(900, 580));
        this.setLocation(50, 15);
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

        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.Y_AXIS));
        addressPanel.setBackground(new Color(118, 181, 197));
        this.addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.addressLabel.setForeground(new Color(128, 57, 30));
        this.addressLabel.setAlignmentX(0.5f);
        this.addressTxtField.setBackground(new Color(128, 57, 30));
        this.addressTxtField.setForeground(new Color(118, 181, 197));
        this.addressTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.addressTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.addressTxtField.setHorizontalAlignment(JTextField.CENTER);
        addressPanel.add(addressLabel);
        addressPanel.add(addressTxtField);
        contentPanel.add(addressPanel);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(new Color(118, 181, 197));
        this.emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.emailLabel.setForeground(new Color(128, 57, 30));
        this.emailLabel.setAlignmentX(0.5f);
        this.emailTxtField.setBackground(new Color(128, 57, 30));
        this.emailTxtField.setForeground(new Color(118, 181, 197));
        this.emailTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.emailTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.emailTxtField.setHorizontalAlignment(JTextField.CENTER);
        emailPanel.add(emailLabel);
        emailPanel.add(emailTxtField);
        contentPanel.add(emailPanel);

        JPanel agePanel = new JPanel();
        agePanel.setLayout(new BoxLayout(agePanel, BoxLayout.Y_AXIS));
        agePanel.setBackground(new Color(118, 181, 197));
        this.ageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.ageLabel.setForeground(new Color(128, 57, 30));
        this.ageLabel.setAlignmentX(0.5f);
        this.ageTxtField.setBackground(new Color(128, 57, 30));
        this.ageTxtField.setForeground(new Color(118, 181, 197));
        this.ageTxtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.ageTxtField.setPreferredSize(new Dimension(2 * this.getWidth() / 3 + 40, 40));
        this.ageTxtField.setHorizontalAlignment(JTextField.CENTER);
        agePanel.add(ageLabel);
        agePanel.add(ageTxtField);
        contentPanel.add(agePanel);

        btnInsert.setBackground(new Color(128, 57, 30));
        btnInsert.setForeground(new Color(118, 181, 197));
        btnInsert.setFont(new Font("Arial", Font.BOLD, 20));

        this.allClientsLabel.setFont(new Font("Arial", Font.BOLD, 26));
        this.allClientsLabel.setForeground(new Color(128, 57, 30));
        this.allClientsLabel.setAlignmentX(0.5f);
        contentPanel.add(allClientsLabel);

        this.scrollPane = updateTable();
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
     * Metoda pentru determinarea clientului selectat din tabelul cu clienti
     * @return clientul selectat in table, null daca nu este selectat niciun client
     */
    public Client getSelectedClient() {
        int row = this.clientsTable.getContentTable().getSelectedRow();
        System.out.println("row = " + row);
        String name = (String)this.clientsTable.getContentTable().getValueAt(row, 1);
        String address = (String)this.clientsTable.getContentTable().getValueAt(row, 2);
        String email = (String)this.clientsTable.getContentTable().getValueAt(row, 3);
        String strAge = (String)this.clientsTable.getContentTable().getValueAt(row, 4);
        int age = Integer.parseInt(strAge);
        return new Client(name, address, email, age);
    }

    /**
     * Getter pentru clientBLL
     * @return field-ul clientBLL
     */
    public ClientBLL getClientBLL() {
        return clientBLL;
    }

    /**
     * Getter pentru tabelul cu clienti
     * @return tabelul cu clienti
     */
    public ClientsTable getClientsTable() {
        return clientsTable;
    }

    /**
     * Setter pentru panoul de derulare
     * @param scrollPane noul panou de derulare
     */
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Getter pentru clientul care se doreste a fi inserat (cu informatiile scrise in campurile de text)
     * @return clientul care se doreste a fi inserat
     */
    public Client getNewClient() {
        return new Client(nameTxtField.getText(), addressTxtField.getText(), emailTxtField.getText(), Integer.parseInt(ageTxtField.getText()));
    }

    /**
     * Metoda pentru afisarea unei ferestre cu un mesaj de eroare
     * @param errMessage mesajul de eroare
     */
    public void showError(String errMessage){
        JOptionPane.showMessageDialog(this, errMessage);
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de inserare a unui nou client
     * @param listener listener pentru butonul de inserare a unui nou client
     */
    public void addInsertListener(presentation.listeners.InsertClientListener listener) {
        this.btnInsert.addActionListener(listener);
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de actualiare a unui client
     * @param listener listener pentru butonul de actualizare a unui client
     */
    public void addUpdateListener(presentation.listeners.UpdateClientListener listener) {
        this.btnUpdate.addActionListener(listener);
    }

    /**
     * Metoda pentru adaugarea unui listener pentru butonul de stergere a unui client
     * @param listener listener pentru butonul de stergere a unui client
     */
    public void addDeleteListener(presentation.listeners.DeleteClientListener listener) {
        this.btnDelete.addActionListener(listener);
    }

    /**
     * Metoda pentru actualizarea tabellui cu clientii in urma operatiilor efectuate
     * @return panoul de derulare ce contine tabelul actualizat
     */
    public JScrollPane updateTable() {
        clientsTable.generateTable(clientBLL.findAll());
        clientsTable.getContentTable().setEnabled(true);
        JScrollPane scroll = new JScrollPane(clientsTable.getContentTable());
        return  scroll;
    }
}
