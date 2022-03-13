package bll.validators;

/**
 * Interfata pentru validarea datelor introduse de utilizator
 * @author Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @since Apr 03, 2017
 */
public interface Validator<T> {
    /**
     * Metoda pentru validare
     * @param t obiect generic
     */
    public void validate(T t);
}
