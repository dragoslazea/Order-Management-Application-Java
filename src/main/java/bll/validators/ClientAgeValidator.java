package bll.validators;

import model.Client;

/**
 * Clasa pentru validarea varstei clientilor
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */
public class ClientAgeValidator implements Validator<Client> {
    /**
     * Varsta minima (18 ani)
     */
    private static final int MIN_AGE = 18;

    /**
     * Suprascrierea metodei validate din interfata implementata
     * @param c clientul a carui varsta este validata
     */
    public void validate(Client c) {
        if (c.getAge() < MIN_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }

    }

}

