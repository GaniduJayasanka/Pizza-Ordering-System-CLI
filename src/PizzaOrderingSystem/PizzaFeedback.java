package PizzaOrderingSystem;


// The PizzaFeedback class represents customer feedback for a specific pizza order.
public class PizzaFeedback {
    private String customerName;
    private String pizzaName;
    private int rating;
    private String comment;

    // Constructor to initialize the PizzaFeedback object with necessary details
    public PizzaFeedback(String customerName, String pizzaName, int rating, String comment) {
        this.customerName = customerName;
        this.pizzaName = pizzaName;
        this.rating = rating;
        this.comment = comment;
    }

    
    //Getters 
    public String getCustomerName() {
        return customerName;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    // Provide a string representation of the PizzaFeddback object
    @Override
    public String toString() {
        return "Customer: " + customerName + ", Pizza: " + pizzaName +
                ", Rating: " + rating + "/10, Comment: \"" + comment + "\"";
    }
}
