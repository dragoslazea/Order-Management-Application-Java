package model;

/**
 * Clasa pentru obiectul de tip Order - a fost denumita Orders pentru a evita folosirea cuvantului rezervat order din limbajul MySQL
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 */
public class Orders {
    /**
     * Id-ul comenzii in baza de date
     */
    private int id;

    /**
     * Id-ul clientului care a plasat comanta
     */
    private int idClient;

    /**
     * Id-ul produsului comandat
     */
    private int idProduct;

    /**
     * Numarul de bucati comandate
     */
    private int amount;

    /**
     * Constructor pentru obiecte de tip Orders
     * @param id id-ul comenzii
     * @param idClient id-ul clientului
     * @param idProduct id-ul produsului
     * @param amount cantitatea
     */
    public Orders(int id, int idClient, int idProduct, int amount) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.amount = amount;
    }

    /**
     * Constructor pentru obiecte de tip Orders fara field-ul id
     * @param idClient id-ul clientului
     * @param idProduct id-ul produsului
     * @param amount cantitatea
     */
    public Orders(int idClient, int idProduct, int amount) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.amount = amount;
    }

    /**
     * Constructor fara parametri
     */
    public Orders() {

    }

    /**
     * Getter pentru id-ul comenzii
     * @return id-ul comenzii
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id-ul comenzii
     * @param id valoarea la care se va seta id-ul comenzii
     */
    public void setId(int id) {
        this.id = id;
    }
}
