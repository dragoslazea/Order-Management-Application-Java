package presentation;

/**
 * Clasa de tip Controller atasata clasei ProductView
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class OrderController {
    /**
     * Interfata grafica cu cele 3 componente ale sale
     */
    View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public OrderController(View view) {
        this.view = view;
        this.view.getOrderView().addPlaceActionListener(new presentation.listeners.PlaceOrderListener(view));
    }
}
