package bll.validators;

import java.util.regex.Pattern;
import model.Client;

/**
 * Clasa pentru validarea email-ului clientilor
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 16, 2021
 * */
public class EmailValidator implements Validator<Client> {
    /**
     * Pattern-ul pentru un email vaild
     */
    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    /**
     * Suprascrierea metodei validate din interfata implementata
     * @param c clientul al carui email este validat
     */
    public void validate(Client c) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(c.getEmail()).matches()) {
            throw new IllegalArgumentException("Email is not a valid email!");
        }
    }

}
