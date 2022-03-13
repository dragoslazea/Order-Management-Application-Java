package presentation;

/**
 * Clasa pentru interfata grafica a aplicatiei cu cele trei ferestre atasate
 * @author Dragos Lazea, student, UTCN, CTI-RO, Seria A, Grupa 30223
 * @since Apr 17, 2021
 */
public class View {
    /**
     * Fereastra pentru client
     */
    private ClientView clientView;

    /**
     * Fereastra pentru produs
     */
    private ProductView productView;

    /**
     * Fereastra pentru comanda
     */
    private OrderView orderView;

    /**
     * Constructor
     * @param clientView fereastra pentru client
     * @param productView fereastra pentru produs
     * @param orderView fereastra pentru comanda
     */
    public View(ClientView clientView, ProductView productView, OrderView orderView) {
        this.clientView = clientView;
        this.productView = productView;
        this.orderView = orderView;
    }

    /**
     * Getter pentru fereastra pentru client
     * @return fereastra pentru client
     */
    public ClientView getClientView() {
        return clientView;
    }

    /**
     * Getter pentru fereastra pentru produs
     * @return fereastra pentru produs
     */
    public ProductView getProductView() {
        return productView;
    }

    /**
     * Getter pentru fereastra pentru comanda
     * @return fereastra pentru comanda
     */
    public OrderView getOrderView() {
        return orderView;
    }

    /**
     * Setter pentru fereastra pentru client
     * @param clientView noua fereastra pentru client
     */
    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    /**
     * Setter pentru fereastra pentru produs
     * @param productView noua fereastra pentru produs
     */
    public void setProductView(ProductView productView) {
        this.productView = productView;
    }

    /**
     * Setter pentru fereastra pentru comanda
     * @param orderView noua fereastra pentru comanda
     */
    public void setOrderView(OrderView orderView) {
        this.orderView = orderView;
    }
}
