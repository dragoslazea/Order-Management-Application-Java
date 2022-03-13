package model;

/**
 * Clasa pentru obiectul de tip Product
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 */
public class Product {
    /**
     * Id-ul produsului in baza de date
     */
    private int id;

    /**
     * Numele produsului
     */
    private String name;

    /**
     * Pretul produsului
     */
    private float price;

    /**
     * Cantitatea disponibila in stoc
     */
    private int quantity;

    /**
     * Constructor pentru obiecte de tip Product
     * @param id id-ul produsului
     * @param name numele produsului
     * @param price pretul produslui
     * @param quantity cantitatea disponibila in stoc
     */
    public Product(int id, String name, float price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructor pentru obiecte de tip Product fara field-ul id
     * @param name numele produslui
     * @param price pretul produsului
     * @param quantity cantitatea disponibila in stoc
     */
    public Product(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Constructor fara parametri
     */
    public Product() {

    }

    /**
     * Getter pentru id-ul produslui
     * @return id-ul produsului
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id-ul produsului
     * @param id valoarea la care va fi setat id-ul produsului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pentru numele produsului
     * @return numele produsului
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pentru numele produslui
     * @param name numele la care va fi setat numele produslui
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pentru pretul produslui
     * @return pretul produsului
     */
    public float getPrice() {
        return price;
    }

    /**
     * Setter pentru pretul produslui
     * @param price noul pret al produsului
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Getter pentru canitatea disponibila in stoc
     * @return cantitatea disponibila in stoc
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter pentru cantitatea disponibila in stoc
     * @param quantity valoarea la care va fi setata cantitatea disponibila in stoc
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
