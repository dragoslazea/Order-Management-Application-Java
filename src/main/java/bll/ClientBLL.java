package bll;

import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import dao.ClientDAO;
import model.Client;
import presentation.ClientsTable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa in care s-au implementat operatiile pe obiectul de tip Client
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */
public class ClientBLL {
    /**
     * Componenta ClientDAO pentru mostenirea metodelor din AbstractDAO
     */
    private ClientDAO clientDAO;

    /**
     * Validator pentru varsta clientului
     */
    private ClientAgeValidator clientAgeValidator;

    /**
     * Validator pentru email-ul clientului
     */
    private EmailValidator emailValidator;

    /**
     * Consteuctor fara parametri
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
        clientAgeValidator = new ClientAgeValidator();
        emailValidator = new EmailValidator();
    }

    /**
     * Getter pentru validatorul de varsta
     * @return validatorul de varsta
     */
    public ClientAgeValidator getClientAgeValidator() {
        return clientAgeValidator;
    }

    /**
     * Getter pentru validatorul de email
     * @return validatorul de email
     */
    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

    /**
     * Metoda pentru inserarea unui client in baza de date
     * @param c clientul de inserat
     * @return clientul inserat
     */
    public Client insertClient(Client c) {
        c.setId(this.clientDAO.getMaxId(c) + 1);
        return clientDAO.insert(c);
    }

    /**
     * Metoda pentru actualizarea unui client din baza de date
     * @param c clientul de actualizat
     * @return clientul actualizat
     */
    public Client updateClient(Client c) {
        return clientDAO.update(c);
    }

    /**
     * Metoda pentru efectuarea unei comenzi de selectare a clientului cu un anumit id
     * @param id id-ul clientului cautat
     * @return clientul cu id-ul id
     */
    public Client selectById(int id) {
        Client c = this.clientDAO.findById(id);
        if (c == null) {
            throw new NoSuchElementException("The client with id " + id + "does not exist!");
        }
        return c;
    }

    /**
     * Metoda pentru determinarea tuturor clientilor din baza de date
     * @return lista cu toti clientii din baza de date
     */
    public List<Client> findAll() {
        return this.clientDAO.findAll();
    }

    /**
     * Metoda pentru stergerea unui client cu un anumit id
     * @param id id-ul clientului de sters
     */
    public void deleteById(int id) {
        Client c = this.clientDAO.findById(id);
        if (c == null) {
            throw new NoSuchElementException("The client with id " + id + "does not exist!");
        }
        this.clientDAO.deleteById(id);
    }
}
