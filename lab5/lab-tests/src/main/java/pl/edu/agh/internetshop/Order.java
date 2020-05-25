package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Order {
    public static final int PRICE_PRECISION = 2;
    public static final int ROUND_STRATEGY = BigDecimal.ROUND_HALF_UP;
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final List<Product> productList;
    private boolean paid;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;
    private BigDecimal discount = BigDecimal.ZERO;

    public Order(List<Product> product, BigDecimal discount) {
        this(product);
        setDiscount(discount);
    }

    public Order(List<Product> product) {
        this.productList = Objects.requireNonNull(product);
        id = UUID.randomUUID();
        paid = false;
    }

    public UUID getId() {
        return id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public BigDecimal getPrice() {
        return productList
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add)
                .multiply(BigDecimal.ONE.subtract(discount));
    }

    public BigDecimal getPriceWithTaxes() {
        return getPrice()
                .multiply(TAX_VALUE)
                .setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccessful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccessful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public BigDecimal getDiscount(){
        return discount;
    }

    public void setDiscount(BigDecimal d) throws IllegalArgumentException{
        if(d.compareTo(BigDecimal.ZERO) < 0 || d.compareTo(BigDecimal.ONE) > 0){
            throw new IllegalArgumentException("Invalid discount value");
        }

        this.discount = d;
        this.discount.setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }
}
