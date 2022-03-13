package model;

/**
 * Clasa pentru obiectul de tip Client
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 */

public class Client {
    /**
     * Id-ul clientului in baza de date
     */
    private int id;

    /**
     * Numele clientului
     */
    private String name;

    /**
     * Adresa clientului
     */
    private String address;

    /**
     * Email-ul clientului
     */
    private String email;

    /**
     * Varsta clientului
     */
    private int age;

    /**
     * Constructor pentru client
     * @param id id-ul clientului
     * @param name numele clientului
     * @param address adresa clientului
     * @param email email-ul clientului
     * @param age varsta clientului
     */
    public Client(int id, String name, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Constructor pentru client care nu initializeaza explicit si campul id
     * @param name numele clientului
     * @param address adresa clientului
     * @param email email-ul clientului
     * @param age varsta clientului
     */
    public Client(String name, String address, String email, int age) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Constructor fara parametri
     */
    public Client() {

    }

    /**
     * Getter pentru id-ul clientului
     * @return id-ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id-ul clientului
     * @param id valoarea la care se seteaza id-ul clientului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pentru numele clientului
     * @return numele clientului
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pentru numele clientului
     * @param name numele la care se va seta numele clientului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pentru adresa clientului
     * @return adresa clientului
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter pentru adresa clientului
     * @param address noua adresa
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter pentru email-ul clientului
     * @return email-ul clientului
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter pentru email-ul clientului
     * @param email noul email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter pentru varsta clientului
     * @return varsta clientului
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter pentru varsta clientului
     * @param age valoarea la care se va seta varsta clientului
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Suprascrierea metodei toString()
     * @return sir de caractere ce contine informatiile despre client
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
