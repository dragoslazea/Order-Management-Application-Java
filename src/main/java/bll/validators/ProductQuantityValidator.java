package bll.validators;

import model.Product;

/**
 * Clasa pentru validarea cantitatii disponibile in stoc
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */
public class ProductQuantityValidator implements Validator<Product> {
    /**
     * Cantitatea minima (0)
     */
    private static final int MIN_QUANTITY = 0;

    /**
     * Suprascrierea metodei validate din interfata implementata
     * @param p produsul pentru care se verifica valididatea cantitatii disponibile
     */
    public void validate(Product p) {
        if (p.getQuantity() < MIN_QUANTITY) {
            throw new IllegalArgumentException("Quantity cannot be a negative number!");
        }

    }
}
