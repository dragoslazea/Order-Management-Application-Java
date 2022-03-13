package presentation;

/**
 * Clasa de tip Controller atasata clasei ProductView
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 18, 2021
 */
public class ProductController {
    /**
     * Interfata grafica cu cele 3 componente ale sale
     */
    private View view;

    /**
     * Constructor
     * @param view interfata grafica
     */
    public ProductController(View view) {
        this.view = view;

        this.view.getProductView().addInsertListener(new presentation.listeners.InsertProductListener(view));
        this.view.getProductView().addUpdateListener(new presentation.listeners.UpdateProductListener(view));
        this.view.getProductView().addDeleteListener(new presentation.listeners.DeleteProductListener(view));
    }
}
