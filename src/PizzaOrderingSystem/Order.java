package PizzaOrderingSystem;

// The Order class represents a customer's pizza order, including details.
public class Order {
    private String orderId; // Unique identifier for the order
    private String customerName; // Name of the customer
    private Pizza pizza; // Pizza object associated with the order
    private int quantity; // Quantity of pizzas ordered
    private double orderAmount; // Total amount for the order
    private String orderMethod; // Method of order
    private String deliveryAddress; // Delivery Address

    // Constructor for an order without a delivery address
    public Order(String orderId, String customerName, Pizza pizza, int quantity, double orderAmount, String orderMethod) {
        this(orderId, customerName, pizza, quantity,orderAmount, orderMethod, null);
    }

    // Constructor for an order with a delivery address
    public Order(String orderId, String customerName, Pizza pizza, int quantity,double orderAmount, String orderMethod, String deliveryAddress) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.pizza = pizza;
        this.quantity = quantity;
        this.orderAmount = orderAmount;
        this.orderMethod = orderMethod;
        this.deliveryAddress = deliveryAddress;
    }
    
    // Retrieves the method of the order.
    public String getCustomerName() {
        return customerName;
    }

    // Returns a string representation of the order details, including delivery address if applicable
    @Override
    public String toString() {
        return "Order ID: " + orderId + "                                                                                                                              " +
               "\nCustomer: " + customerName + "                                                                                                                               " +
               "\nPizza: " + pizza + "" +
               "\nQuantity: " + quantity + "                                                                                                                                    " +
               "\nOrderAmount: " + orderAmount + "                                                                                                                            " +
               "\nOrder Method: " + orderMethod + "                                                 " +
               (deliveryAddress != null ? "\nDelivery Address: " + deliveryAddress : "" + "                                                                          " );
    }

	public String getOrderMethod() {
		// TODO Auto-generated method stub
		return orderMethod;
	}
}
