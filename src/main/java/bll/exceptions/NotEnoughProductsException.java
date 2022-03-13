package bll.exceptions;

/**
 * Clasa pentru definirea unei exceptii in cazul in care un client doreste sa cumpere o cantitate de produs mai mare decat cea disponibila in stoc
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 * */
public class NotEnoughProductsException extends Exception {
    /**
     * Constructor fara parametri
     */
    public NotEnoughProductsException() {
        super("There are less products in the store than the desired quantity!");
    }
}
