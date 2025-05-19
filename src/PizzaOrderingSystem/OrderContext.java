package PizzaOrderingSystem;

import java.util.ArrayList;
import java.util.List;

// Interface defining the state methods
interface OrderState {
    void next(OrderContext context); // Transition to the next state
    void prev(OrderContext context); // Transition to the previous state
    void printStatus(); // Print the current status of the order
    
    // Color Codes for Add the background colors
    public static final String Backgroung_Green = "\033[42m";
    public static final String Background_Red   = "\033[41m"; 
    public static final String Background_Blue  = "\033[44m";   
    public static final String Background_White = "\033[107m";
    public static final String Background_Bright_Magenta= "\033[105m";
    
    // Text Style Codes
    public static final String Bold_Weight = "\033[1m";
    public static final String Reset_Color = "\033[0m";  
    
    //Text Color Codes
    public static final String Bright_White   = "\033[97m";
    public static final String Bright_Cyan    = "\033[96m";
    public static final String Bright_Yellow  = "\033[93m";
    public static final String Bright_Red     = "\033[91m";
    public static final String Bright_Magenta = "\033[95m";
    public static final String Yellow_color   = "\033[33m";
    public static final String Green_color    = "\033[32m";
    public static final String Bright_Green   = "\033[92m";
    public static final String Bright_Blue    = "\033[34m";
   
}



// Concrete states representing various stages in the order lifestyle

class PlacedState implements OrderState {
    // Trasition to the next state : Preparation
	@Override
    public void next(OrderContext context) {
        context.setState(new PreparationState());
    }

	// Transition to the previous state: Print a message as it's the initial state
    @Override
    public void prev(OrderContext context) {
        System.out.println( Bold_Weight + Bright_White + Background_Blue  +"Order is in its initial state.");
    }

    // Print the current status of the order
    @Override
    public void printStatus() {
        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"Order placed and awaiting preparation.");
    }
}


class PreparationState implements OrderState {
	// Trasition to the next state : Out for Delivery
	@Override
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

	// Transition to the previous state: Placed state
    @Override
    public void prev(OrderContext context) {
        context.setState(new PlacedState());
    }

    // Print the current status of the order
    @Override
    public void printStatus() {
        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"Order is being prepared.");
    }
}

class OutForDeliveryState implements OrderState {
	// Trasition to the next state : Delivered
	@Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
    }

	// Transition to the previous state: Preparation state
    @Override
    public void prev(OrderContext context) {
        context.setState(new PreparationState());
    }
 
    // Print the current status of the order
    @Override
    public void printStatus() {
        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"Order is out for delivery.");
    }
}

class DeliveredState implements OrderState {
    // /no next state; the order has been delivered
	@Override
    public void next(OrderContext context) {
        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"Order already delivered.");
    }

	// Transition to the previous state: Out for delivery
    @Override
    public void prev(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    // Print the current status of the order
    @Override
    public void printStatus() {
        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"Order is delivered, Enjoy Your Meal !!!"+Reset_Color);
    }
}

// Context class managing the current state, tracking state history, and notifications
class OrderContext {
    private OrderState state = new PlacedState();
    private List<OrderState> stateHistory = new ArrayList<>();
    private List<String> statusNotifications = new ArrayList<>();

    // Set the current state
    public void setState(OrderState state) {
        this.state = state;
        stateHistory.add(state);  // Track state history
    }

    // Transition to the next state
    public void nextState() {
        state.next(this);
        notifyUser();  // Notify the user on state transition
    }

    // Transition to the previous state
    public void prevState() {
        state.prev(this);
        notifyUser();  // Notify the user on state transition
    }

    // Print the current state
    public void printStatus() {
        state.printStatus();
    }

    // Notify the user with the current state of the order
    private void notifyUser() {
        state.printStatus();
        statusNotifications.add("Order status updated: " + state.getClass().getSimpleName());
    }

    // View the order summary
    public void getOrderSummary() {
        System.out.println("\nOrder Summary:");
        System.out.println("Current state: " + state.getClass().getSimpleName());
        System.out.println("State History: " + stateHistory);
        System.out.println("Status Notifications: " + statusNotifications);
    }
}
