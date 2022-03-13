package presentation;

/**
 * Clasa de tip Controller atasata clasei ClientView
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class ClientController {
    /**
     * Interfata grafica cu cele 3 componente ale sale
     */
    private View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public ClientController(View view) {
        this.view = view;

        this.view.getClientView().addInsertListener(new presentation.listeners.InsertClientListener(view));
        this.view.getClientView().addUpdateListener(new presentation.listeners.UpdateClientListener(view));
        this.view.getClientView().addDeleteListener(new presentation.listeners.DeleteClientListener(view));
    }

}
