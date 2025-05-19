package PizzaOrderingSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import PizzaOrderingSystem.FeedbackCommands.AddFeedbackCommand;
import PizzaOrderingSystem.FeedbackCommands.DisplayTopRatedCommand;
import PizzaOrderingSystem.FeedbackCommands.ViewAllFeedbackCommand;

public class UserInterface {
	
	// Object Builder creating for customize pizzas
	Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
	// StrategyManager to manage payment section and loyalty point strategies
    StrategyManager strategyManager = new StrategyManager();
    // Using PromotionContext to handle promotions applied to order
    PromotionContext promotionContext = new PromotionContext();
    
    private Scanner scanner; // Scanner object use for User Input
    private int orderIdCounter = 1000; // Use Create unique OrderID started in 1000
    private HashMap<String, Order> orders; // Use Map to store orders, with OrderID as the key
    private User activeUser; // Use for represent the currently active user
    private Map<String, User> userProfiles = new HashMap<>(); // Use Map to store User profiles, with Username as the key 
   
    
    
    // Color Codes for Add the background colors
    public static final String Backgroung_Green = "\033[42m";
    public static final String Background_Red   = "\033[41m"; 
    public static final String Background_Blue  = "\033[44m";   
    public static final String Background_White = "\033[107m";
    public static final String Background_Bright_Magenta = "\033[105m";
    
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
    
    public UserInterface() {
        
    	// Initialize necessary components for the UserInterface. 
        this.scanner = new Scanner(System.in);
        this.orders = new HashMap<>();
         
    }
    
    
		// The main method to start the UserInterface Displays welcome banner and loops 
	    public void start() {
	    	    
	    	
	    	int choice = -1; // Initialize choice variable for main menu selectio
	    	do {
	    	    // Display welcome banner 
	    	    System.out.println(Backgroung_Green + Bold_Weight + Bright_White + "♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦");
	    	    System.out.println("♦                                                                                                         ♦");
	    	    System.out.println("♦                                 ♥ WELCOME TO PIZZA ORDERING SYSTEM ♥                                    ♦");
	    	    System.out.println("♦                                                                                                         ♦");
	    	    System.out.println("♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦" + Reset_Color);
	    	    System.out.println();
	    	    System.out.println();

	    	    manageUserProfile(); // Manage User Profile (login or creation , recovery password)

	    	    // Display the Main Menu options
	    	    System.out.println(Yellow_color + "");
	    	    System.out.println("                                          1. Customize & Order Pizza                                          ");
	    	    System.out.println("                                          2. View Favorites & ReOrder Pizza                                      ");
	    	    System.out.println("                                          3. View User Profile                                                ");
	    	    System.out.println("                                          4. Real-Time Order Tracking                                         ");
	    	    System.out.println("                                          5. Feedback and Ratings                                             ");
	    	    System.out.println("                                          6. Exit                                                             ");

	    	    System.out.println();
	    	    System.out.println("*****************************************************************************" + Reset_Color);

	    	    // Input validation for Main menu selection
	    	    boolean validInput = false; 
	    	    while (!validInput) {
	    	        System.out.print(Green_color + Bold_Weight + "Please choose an option: " + Bright_Yellow);

	    	        String input = scanner.nextLine().trim(); 

	    	        if (isValidNumber(input)) {
	    	            choice = Integer.parseInt(input);
	    	            if (choice >= 1 && choice <= 6) {
	    	                validInput = true; // Valid menu selected choice
	    	            } else {
	    	                System.out.println(Bold_Weight + Bright_Red + "Invalid choice. Please select a number between 1 and 6." + Reset_Color);
	    	            }
	    	        } else {
	    	            System.out.println(Bold_Weight + Bright_Red + "Invalid input. Please enter a valid number." + Reset_Color);
	    	        }
	    	    }

	    	    System.out.println();
	    	    // Handle and Perform actions based user's choice with respective methods
	    	    switch (choice) {
	    	        case 1:
	    	            CustomizePizzaAndOrder(); // Customize and Order Pizza
	    	            break;
	    	        case 2:
	    	            ViewFavoritesAndReorder(); // View favorite pizzas and Reorder 
	    	            break;
	    	        case 3:
	    	            viewUserProfile(); // View User Profile details
	    	            break;
	    	        case 4:
	    	            TrackOrder(); // Real Time Track existing orders
	    	            break;
	    	        case 5:
	    	            ProvideFeedback(); //  Manage User feedbacks
	    	            break;
	    	        case 6:
	    	            System.out.print(Bright_Green + Bold_Weight +"Are you sure you want to quit the system? (Y/N): "+  Bright_Yellow);
	    	            String confirmation = scanner.nextLine().trim();
	    	            if (confirmation.equalsIgnoreCase("Y")) {
	    	                System.out.println(Backgroung_Green + Bold_Weight + Bright_White +"Thank you for using the system. Goodbye!");
	    	                choice = 8; // Exit the loop
	    	            } else {
	    	                choice = -1; // Reset and return to main menu
	    	                System.out.println(Green_color + Bold_Weight +"Returning to the main menu...");
	    	                System.out.println("");
	    	            }
	    	            break;
	    	        default:
	    	            System.out.println(Bold_Weight + Bright_Red +"Invalid choice. Please try again."+ Reset_Color);
	    	    }
	    	} while (choice != 8); // Loop until the user choose to exit
	    }
	    	
	    	 // Helping method to validate input number if a string is a valid number.
	    	 
	    	private boolean isValidNumber(String input) {
	    	    try {
	    	        Integer.parseInt(input); // Attempt to parse input as an integer
	    	        return true;
	    	    } catch (NumberFormatException e) {
	    	        return false; // Input is not a valid number
	    	    }
	    	}
	    
	    
	    
	 // Method for user login and password managing
	    private void handleLogin() {
	        int attempts = 0;// Tracks the number of login attempts to the system
	        
	        while (attempts < 2) { // Only Allow up to two attempts of wrong passwords
	            System.out.print(Bold_Weight + Bright_White +"Enter your password: " + Bright_Yellow);
	            String password = scanner.nextLine();

	            // Check if the entered password matches the active user's password
	            if (password.equals(activeUser.getPassword())) {
	                System.out.println(Bright_Green + Bold_Weight +"Login successful. Welcome, " + activeUser.getUsername() + "!");
	                return; // Exit the method
	            } else {
	                attempts++;
	                System.out.println(Bold_Weight + Bright_Red +"\nIncorrect password. Attempts left: " + (2 - attempts));
	            }
	        }
            
	        // Offer the option to recover the password if both attempts are failed
	        System.out.print(Bright_Green + Bold_Weight +"Do you want to recover your password? (Y/N): "+ Bright_Yellow);
	        String choice = scanner.nextLine().trim().toUpperCase();

	        if ("Y".equals(choice)) {
	            recoverPassword(); // Proceed to Invoke the password recovery method
	        } else {
	            System.out.println(Bright_Green + Bold_Weight +"Please try logging in again.");
	            handleLogin(); // Restart login process
	        }
	    }

	    // Method for password recovery 
	    // Prompts the user to enter a new password
	    // Updates the password for the active user
	    private void recoverPassword() {
	        String newPassword = null; // Initialize the variable to hold the new password

	        // Loop until a valid password is entered
	        while (newPassword == null) {
	            System.out.print(Bold_Weight + Bright_White + "Enter a new password: " + Bright_Yellow);
	            String input = scanner.nextLine().trim(); // Read the new User Password Input

	            // Validate the entered password
	            if (isValidPassword(input)) {
	                newPassword = input; // Set the valid password
	            } else {
	                // Display error message for invalid password
	                System.out.println(Bold_Weight + Bright_Red +
	                    "Invalid password! Password must be at least 4 characters long and include both letters and numbers." +
	                    Reset_Color);
	            }
	        }

	        // Update the active user's password and confirm success
	        activeUser.setPassword(newPassword);
	        System.out.println(Bright_Green + Bold_Weight + 
	            "Your password has been successfully reset! Please log in again." + Reset_Color);
	        System.out.println("");

	        // Redirect to the login or user management process
	        manageUserProfile();
	    }

        
	    // Validate Username only letters
	    // check if username already exit
	    // If the user exists, prompts 
	    private void manageUserProfile() {
	        System.out.println(Bold_Weight + Bright_Cyan + "----------------Inert Login Credentials----------------" + Reset_Color);
	        System.out.println("");

	        // Username validation
	        String username = null;
	        while (username == null) {
	            System.out.print(Bold_Weight + "Enter Your Username: " + Bright_Yellow + Bold_Weight);
	            String input = scanner.nextLine().trim();
	            System.out.println("" + Reset_Color);

	            if (isValidUsername(input)) {
	                username = input; // Valid username
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid username! Please use only alphabetic characters." + Reset_Color);
	            }
	        }

	        // Check if the user already exists
	        if (userProfiles.containsKey(username)) {
	            activeUser = userProfiles.get(username);
	            System.out.println("Welcome back, " + activeUser.getUsername() + "!");
	            handleLogin(); // Handles login with password validation and recovery
	        } else {
	            // Password validation
	            String password = null;
	            while (password == null) {
	                System.out.print(Bold_Weight + Bold_Weight + Bright_White + "Set a password for your account: " + Bright_Yellow + Bold_Weight);
	                String input = scanner.nextLine().trim();
	                if (isValidPassword(input)) {
	                    password = input; // Valid password
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid password! Password must be at least 4 characters long and include both letters and numbers." + Reset_Color);
	                }
	            }

	            // Create  and store the new user profiles 
	            activeUser = new User(username, password);
	            userProfiles.put(username, activeUser);
	            System.out.println("");

	            System.out.println(Bold_Weight + Bright_Green +"Profile created successfully!!! " + Reset_Color);
	            System.out.println("");
	            System.out.println(Yellow_color + Bold_Weight + "[!!!---------------------------Welcome To Pizza System  " + Bright_White + activeUser.getUsername() + Yellow_color + Bold_Weight + "---------------------------!!!]");
	        }
	    }

	    // Ensure only letter include
	    private boolean isValidUsername(String username) {
	        return username.matches("[a-zA-Z]+"); // Regular expression to allow only alphabetic characters
	    }

        // Validate the User Password and ensure it meets the beloow requirements
	    // At least 4 character
	    // Contains at least one letter
	    // Contains at least one numeric digit
	    private boolean isValidPassword(String password) {
	        return password.length() >= 4 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
	    }
	   
	    
	    private void CustomizePizzaAndOrder() {
	    	
	    	// Initialize the scanner to Capture the user inputs
	        Scanner scanner = new Scanner(System.in);
	        	
	        	System.out.println(Bold_Weight + Bright_Cyan +"------------------------Customize Your Pizza------------------------" + Reset_Color);
	            System.out.println(""+ Reset_Color);
	            
	            
				// Create the handler chain for processing the Pizza Customization and Order
				TypeHandler typeHandler = new TypeHandler();
				OrderHandler crustHandler = new CrustHandler();
				OrderHandler sauceHandler = new SauceHandler();
				ToppingHandler toppingHandler = new ToppingHandler(); 
				OrderHandler confirmationHandler = new ConfirmationHandler(activeUser);

				// Set the chain of responsibility where each handler processes a step in order customization
				typeHandler.setNextHandler(crustHandler);
				crustHandler.setNextHandler(sauceHandler);
				sauceHandler.setNextHandler(toppingHandler);
				toppingHandler.setNextHandler(confirmationHandler);

				// Create the builder to collect User selection and Build the pizza.
				Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();

				// Start the process by invoking the first handler in the chain
				typeHandler.handleOrder(builder);

				// Once all the handlers have processed, build the pizza with collected details
				Pizza customizePizza = builder.build();

				// Generate a Unique Order ID
				String orderId = generateOrderId();
				System.out.println("");
				System.out.println(Bold_Weight + Bright_White +"Generated Order ID: " + Bright_Magenta + orderId + Reset_Color);

				// Display the base price for the selected pizza
				int basePrice = typeHandler.getSelectedPrice();
				System.out.println(Bold_Weight + Bright_White +"\nBase price for the selected pizza: "+ Bright_Cyan +"Rs."+ basePrice+ Reset_Color);

				// retrive the topping costs and displa
				double toppingCost = toppingHandler.getTotalToppingCost(); // Fetch topping cost
				System.out.println(Bold_Weight + Bright_White +"Topping Cost: " + Bright_Cyan +"Rs."+ toppingCost+ Reset_Color);

				// Calculate the total cost before promotions
				double totalCostBeforePromotion = basePrice + toppingCost;
				System.out.println(Bold_Weight + Bright_White +"Total Price before Promotion: "+ Bright_Cyan +"Rs."+ totalCostBeforePromotion + Reset_Color);

				
				//Print eligible promotions for User selected pizzas
				System.out.println();
				System.out.println();
				System.out.println(Bold_Weight + Bright_Cyan + "********************Available Special Promotion for Your Pizza********************" + Reset_Color);
				System.out.println();

				// Apply promotions
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "*********************************************************************************");
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "                            Eligible Promotions (for 1 pizza):                   ");
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "                                                                                 ");
				// Set up the promotion context and display eligible promotions
				PromotionContext promotionContext = new PromotionContext();

				// Apply Seasonal promotion and calculate discount prices
				promotionContext.setPromotionStrategy(new SeasonalPromotion(10.0));
				double seasonalDiscountedPrice = promotionContext.calculateDiscountedPrice(totalCostBeforePromotion, 1);
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "1. Seasonal Promotion: 10% off - Discounted Price: Rs."+ seasonalDiscountedPrice +"                     ");
                
				// Offer bulk purchase promotion
				promotionContext.setPromotionStrategy(new BulkPurchasePromotion(5, 500.0));
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "2. Bulk Purchase Promotion: Buy 5 or more pizzas, get Rs.500 off (total price).  ");

				// Option for no promotion
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "3. No Promotion - Price per Pizza: Rs." + totalCostBeforePromotion + "                                     ");
				System.out.println(Background_Blue + Bright_White + Bold_Weight + "*********************************************************************************" + Reset_Color);

				System.out.println();
				System.out.println();
				System.out.println(Bold_Weight + Bright_Cyan + "*********************************************************************************" + Reset_Color);
				
				// Input validation for promo choice
				int promoChoice = -1; // Initialize with an invalid value
				boolean validInput = false; // Flag to track input validity

				while (!validInput) {
				    System.out.print(Green_color + Bold_Weight + "\nSelect a promotion to apply (Enter 1, 2, or 3): " + Bright_Yellow + Bold_Weight);
				    String input = scanner.nextLine().trim(); // Read input as string and trim whitespace

				    if (isValidNumber(input)) {
				        promoChoice = Integer.parseInt(input); // Convert to integer
				        if (promoChoice >= 1 && promoChoice <= 3) {
				            validInput = true; // Valid input
				        } else {
				            System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please enter 1, 2, or 3." + Reset_Color);
				        }
				    } else {
				        System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a number (1, 2, or 3)." + Reset_Color);
				    }
				}

				// Set the promotion strategy based on valid user input
				switch (promoChoice) {
				    case 1:
				        promotionContext.setPromotionStrategy(new SeasonalPromotion(10.0));
				        break;
				    case 2:
				        promotionContext.setPromotionStrategy(new BulkPurchasePromotion(5, 500.0));
				        break;
				    case 3:
				    default:
				        promotionContext.setPromotionStrategy(new NoPromotion());
				        break;
				}
				
				
				System.out.println();
				System.out.println();
				System.out.println(Bold_Weight + Bright_Cyan + "///////////////////////////////ORDER Your Pizza//////////////////////////////////" + Reset_Color);
				System.out.println();
				System.out.println();
				
				
				// Request the user to enter the quantity of pizzas to order with validation
				int quantity = 0; // Initialize quantity
				while (true) {
				    System.out.print(Green_color + Bold_Weight +"Enter quantity: "+ Bright_Yellow);
				    String input = scanner.nextLine().trim(); // Read input as string and trim whitespace

				    if (isValidNumber(input)) {
				        quantity = Integer.parseInt(input);
				        if (quantity > 0) {
				            break; // Valid quantity
				        } else {
				            System.out.println(Bold_Weight + Bright_Red +"Invalid quantity. Please enter a positive number."+ Reset_Color);
				        }
				    } else {
				        System.out.println(Bold_Weight + Bright_Red +"Invalid input. Please enter a valid number."+ Reset_Color);
				    }
				}

				// Calculate the total price based on the selected quantity and promotion
				double orderAmount = totalCostBeforePromotion * quantity;
				double finalPrice = promotionContext.applyPromotion(orderAmount, quantity);

				System.out.println();
				

				// Display the final price after applying the promotion
				System.out.println(Background_Red + Bold_Weight + Bright_White +"**********************************************************************************");
				System.out.println("                             Order Summary                                        ");
				System.out.println("Order ID: " + orderId + "                                                                 ");
				System.out.println("Quantity: " + quantity + "                                                                       ");
				System.out.println("Total Price (before promotion): Rs." + orderAmount + "                                         ");
				System.out.println("Final Price after applying selected promotion: Rs." + finalPrice + "                          ");
				System.out.println("**********************************************************************************" + Reset_Color);
				
				
				
				
				System.out.println();
				
				
				
				// Ask for the  Order Method (Pickup  or Delivery) with validation
				String orderMethod;
				while (true) {
				    System.out.print(Green_color + Bold_Weight +"Enter Order Method (P for Pickup, D for Delivery): "+ Bright_Yellow);
				    orderMethod = scanner.nextLine().trim().toUpperCase();
				    System.out.print("");
				   
				    if ("P".equals(orderMethod) || "D".equals(orderMethod)) {
				        break; // Valid order method
				    } else {
				        System.out.println(Bold_Weight + Bright_Red +"Invalid choice. Please enter 'P' for Pickup or 'D' for Delivery."+ Reset_Color);
				    }
				}

				// Proceed to save the order or other actions
				if ("P".equals(orderMethod)) {
				    handlePickupOrder(orderId, customizePizza, quantity, finalPrice, "Pickup");
				} else {
				    handleDeliveryOrder(orderId, customizePizza, quantity, finalPrice, "Delivery");
				}
				
				
				System.out.println();
				System.out.println(Bold_Weight + Bright_Cyan + "/////////////////////////////////////////////////////////////////////////////////" + Reset_Color);
				
	        }
	    
         // Generate the Uniqe Order Id     
	    private String generateOrderId() {
	        return "ORD" + (orderIdCounter++);
	    }
	    
	    // Manage and Handle Pickup order process by collect customer details, 
	    //Create and displaye the order summery of pickup method
	    private void handlePickupOrder(String orderId, Pizza customPizza, int quantity, double orderAmount, String orderMethod) {
	    	String customerName = null;

	        // Validate customer name to ensure only letter
	        while (customerName == null) {
	        	System.out.println();
	            System.out.print(Bold_Weight + Bright_White + "Enter Customer Name: " + Bright_Yellow); // Input on the same line
	            String input = scanner.nextLine().trim(); // Read input and trim whitespace

	            if (isValidCustomerName(input)) {
	                customerName = input; // Valid name
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid name! Please use only alphabetic characters and spaces." + Reset_Color);
	            }
	        }

	        // Create the order object for pickup
	        Order order = new Order(orderId, customerName, customPizza, quantity, orderAmount, orderMethod);

	        // Save the order (assuming `orders` is a Map<String, Order>)
	        orders.put(orderId, order);

	        // Display order summary
	        System.out.println();
	        System.out.println(Background_Bright_Magenta + Bold_Weight + Bright_White +"***********************************************************************************************************************************************");
	        System.out.println("                                       Pickup Order Summary                                                                                    ");
	        System.out.println("                                                                                                                                               ");
	        System.out.println(order + Reset_Color);
			System.out.println();
			
	        // Display preparation message indicating when the order will be ready
	        System.out.println("\n" + Bright_Green + Bold_Weight + "Your order is being prepared and will be ready for pickup in 20 minutes." + Reset_Color);
	        System.out.println();
	        // Handle payment process
	        HandlePayment();
	    }

	    
	    
	     // Helper method to validate if a customer's name contains only alphabetic characters and spaces.
	     
	    private boolean isValidCustomerName(String name) {
	        return name.matches("[a-zA-Z ]+") && !name.isBlank(); // Allows letters and spaces, and ensures input is not empty
	    }

	    // Manage and handle the delivery order process by collecting customer details
	    // Delivery address, and creating the order and calculating delivery time and charges
	    private void handleDeliveryOrder(String orderId, Pizza customPizza, int quantity, double orderAmount, String orderMethod) {
	        Scanner scanner = new Scanner(System.in);

	        // Validate Customer Name
	        String customerName = null;
	        while (customerName == null) {
	        	System.out.println();
	            System.out.print(Bold_Weight + Bright_White + "Enter Customer Name: " + Bright_Yellow); // Same-line input
	            String input = scanner.nextLine().trim();

	            if (isValidCustomerName(input)) {
	                customerName = input; // Valid name
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid name! Please use only alphabetic characters and spaces." + Reset_Color);
	            }
	        }


	     // Validate Delivery Address
	        String address = null;
	        while (address == null) {
	            System.out.print(Bold_Weight + Bright_White + "Enter Delivery Address: " + Bright_Yellow); // Same-line input
	            String input = scanner.nextLine().trim(); // Read user input and trim whitespace
	            System.out.println();

	            if (isValidAddress(input)) {
	                address = input; // Valid address
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid address! Please use only letters, numbers, commas, and spaces, and ensure it contains both letters and numbers." + Reset_Color);
	            }
	        }

	        // Estimate delivery timebasedon the address
	        String deliveryTime = estimateDeliveryTime(address);

	        
	        // Add a fixed delivery charge to the total order amount
	        final double deliveryCharge = 300.0;
	        orderAmount += deliveryCharge;

	        // Create the order object for delivery
	        Order order = new Order(orderId, customerName, customPizza, quantity, orderAmount, orderMethod, address);

	        // Save the order
	        orders.put(orderId, order);

	        // Display Delivery order summary
	        
	        System.out.println();
	        System.out.println(Background_Bright_Magenta + Bold_Weight + Bright_White +"***********************************************************************************************************************************************");
	        System.out.println("                                    Delivery Order Summary:                                                                                    ");
	        System.out.println("                                                                                                                                               ");
	        System.out.println("Order Number: " + orderId+"                                                                                                                          ");
	        System.out.println("Customer Name: " + customerName+"                                                                                                                           ");
	        System.out.println("Delivery Address: " + address+"                                                                                                            ");
	        System.out.println("Estimated Delivery Time: " + deliveryTime+"                                                                                                          ");
	        System.out.println("Pizza Details: " + customPizza+"                  ");
	        System.out.println("Quantity: " + quantity+"                                                                                                                                     ");
	        System.out.println("Order Amount (before delivery charge): " + (orderAmount - deliveryCharge)+"                                                                                                    ");
	        System.out.println("Delivery Charge: " + deliveryCharge+"                                                                                                                           ");
	        System.out.println("Total Order Amount (including Delivery Charges): " + orderAmount+"                                                                                          ");
	        System.out.print(""+ Reset_Color);
	        System.out.print("");
	        
	        // Inform the customer about order tracking options
	        System.out.println("\n" + Bright_Green + Bold_Weight + "You can check the order status and track the order in Main Menu Option 4: Real-Time Order Tracking." + Reset_Color);
	        System.out.print("");
	        
	        // Call Handle payment process
	        HandlePayment();
	    }
	    

	    
	     //Helper method to validate if a delivery address is valid.
	      //Allows letters, numbers, spaces, and commas.
	     
        private boolean isValidAddress(String address) {
            // Check if the address contains both letters and numbers
            boolean containsLetters = address.matches(".*[a-zA-Z].*");
            boolean containsNumbers = address.matches(".*\\d.*");
            boolean validCharacters = address.matches("[a-zA-Z0-9, ]+"); // Letters, numbers, commas, spaces only

            return containsLetters && containsNumbers && validCharacters;
        }

        // Estimates delivery time basedon the customer's location.
	    //Uses apredefined map of locations and their corresponding delivery times
		private String estimateDeliveryTime(String location) {
	        // Simulate a mapping service with predefined locations and delivery times
	        Map<String, String> locationEstimates = new HashMap<>();
	        locationEstimates.put("Pettah, Colombo 1", "45 minutes");
	        locationEstimates.put("UnionPlace, Colombo 2", "50 minutes");
	        locationEstimates.put("Kollupitiya, Colombo 3", "1 hour");
	        locationEstimates.put("Bambalapitiya, Colombo 4", "1 hour and 15 minutes");
	        locationEstimates.put("Dehivala, Colombo 5", "1 hour and 20 minutes");
	        locationEstimates.put("Kollupitiya, Colombo 6", "1 hour and 30 minutes");

	        return locationEstimates.getOrDefault(location, "Delivery estimate unavailable");
	    }

		// Displays the user profile information of the currently logged-in user.
	    private void viewUserProfile() {
	    	
	    	System.out.println();
			System.out.println(Bold_Weight + Bright_Cyan + "<><><><><><><><><><><><><><><><><><> > User Profiles < <><><><><><><><><><><><><><><><>" + Reset_Color);
			System.out.println();
			
			// Check if there's an active user session
	        if (activeUser == null) {
	            System.out.println("No active user. Please log in or register first.");
	            return;
	        }
               
	           
	        // Display user profile details (Loyality points, Favorits pizzas)
	        System.out.println();
	        System.out.println(Bright_White + Bold_Weight +"\nUser Profile:");
	        System.out.println();
	        System.out.println(Bright_White + Bold_Weight +"Username: " + Bright_Green + activeUser.getUsername());
	        System.out.println(Bright_White + Bold_Weight +"Loyalty Points: "+ Bright_Green + activeUser.getLoyaltyPoints());
	        System.out.println();
	        
	        // Show favorite pizzas
	        List<Pizza> favorites = activeUser.getFavoritePizzas();
	        if (favorites.isEmpty()) {
	            System.out.println("You have no favorite pizzas.");
	            System.out.println();
	        } else {
	            System.out.println(Bright_White + Bold_Weight + "Your Favorite Pizzas:" + Bright_Cyan);
	            
	            for (int i = 0; i < favorites.size(); i++) {
	                System.out.println((i + 1) + ". " + favorites.get(i)); // Display pizza details
	                System.out.println();
	            }
	            System.out.println();
	        }
	    }

	    // Real time track the status of an order based on Order ID or Customer Name.
	    // Only track delivery Orders
	    private void TrackOrder() {
	    	
        	System.out.println();
	        System.out.print(Bold_Weight + Bright_White + "Enter Order ID or Customer Name to track: " + Bright_Yellow);

	        // Read the entire line of input
	        String input = scanner.nextLine().trim();
	        System.out.println(Reset_Color);

	        // Validate User input
	        if (input.isEmpty()) {
	            System.out.println(Bold_Weight + Bright_Red + "Input cannot be empty. Please provide a valid Order ID or Customer Name." + Reset_Color);
	            System.out.println(Reset_Color);
	            return;
	        }

	        if (!isValidOrderId(input) && !isValidCustomerName(input)) {
	            System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please provide a valid Order ID (e.g., ORD123) or a valid Customer Name." + Reset_Color);
	            System.out.println(Reset_Color);
	            return;
	        }

	        // Attemp to locate the order by Order ID or Customer Name
	        Order order = null;
	        if (isValidOrderId(input)) {
	            order = orders.get(input); // Check if the input matches an Order ID
	        }

	        // If the input doesn't match an Order ID, check by Customer Name
	        if (order == null && isValidCustomerName(input)) {
	            for (Order o : orders.values()) {
	                if (o.getCustomerName().equalsIgnoreCase(input)) {
	                    order = o;
	                    break;
	                }
	            }
	        }

	        // If no matching order is found, print an error
	        if (order == null) {
	            System.out.println(Bold_Weight + Bright_Red + "Order ID or Customer Name not found. Please try again." + Reset_Color);
	            return;
	        }

	        // Check and Restrict real time tracking for pickup orders
	        if ("Pickup".equalsIgnoreCase(order.getOrderMethod())) {
	            System.out.println(Bold_Weight + Bright_Yellow + "Real-Time Order Tracking is unavailable for Pickup orders." + Reset_Color);
	            return;
	        }

	       // Display order details and tracking status
	        System.out.println();
			System.out.println(Bold_Weight + Bright_Blue + Background_White + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >> Real Time Tracking Your Orders << >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + Reset_Color);
			System.out.println();
	        System.out.println(Bold_Weight + Bright_White + Background_Bright_Magenta  + order + Reset_Color);
	        System.out.println();
	        
	        
	        
	        

	        // Initialize order tracking process using OrderContext
	        OrderContext orderContext = new OrderContext();

            // Update and display order status through various stages	        
	        System.out.println();
			System.out.println(Bold_Weight + Bright_Cyan+ ".............................. <> Order Delivery Status <> .............................. " + Reset_Color);
			System.out.println();
	        
			
	        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"");
	        orderContext.printStatus(); // Notify user: Order is placed
	        orderContext.nextState();   // Transition to Preparation state
	        System.out.println("Status Updated: Order is being prepared." );
	        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"");
	        
	        orderContext.printStatus();
	        orderContext.nextState();   // Transition to OutForDelivery state
	        System.out.println("Status Updated: Order is out for delivery." );
	        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"");
	        
	        
	        orderContext.printStatus();
	        System.out.println( "Status Updated: Order delivered." );
	        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"");
	        
	        orderContext.nextState();
	        orderContext.printStatus();
	        System.out.println(Bold_Weight + Bright_White + Background_Blue  +"" + Reset_Color);
	    }
	    
	    
	    // Matches IDs starting with "ORD" followed by numbers
	    private boolean isValidOrderId(String orderId) {
	        return orderId.matches("ORD\\d+"); 
	    }

	    
	    private void HandlePayment() {
	        Scanner scanner = new Scanner(System.in);

	        //Display header for the payment section
	        System.out.println();
	        System.out.println(Bold_Weight + Bright_Cyan + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Payment Section >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + Reset_Color);
	        System.out.println();

	        // Validate order amount with iput checking and user prompts
	        double orderAmount = 0;
	        while (true) {
	            System.out.print(Bold_Weight + Green_color + "Enter order amount: " + Bright_Yellow + "Rs.");
	            String input = scanner.nextLine().trim(); // Read input as string to handle errors

	            if (isValidDouble(input)) {
	                orderAmount = Double.parseDouble(input);
	                if (orderAmount > 0) {
	                    break; // Valid order amount
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Order amount must be a positive number." + Reset_Color);
	                }
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a valid amount." + Reset_Color);
	            }
	        }

	        System.out.println(Reset_Color);

	        // Prompt and validate payment method selection
	        int paymentChoice = 0;
	        while (true) {
	            System.out.println(Bold_Weight + Bright_White + "Select payment method:" + Reset_Color);
	            System.out.println(Bold_Weight + Bright_White + "1. Credit Card");
	            System.out.println("2. Digital Wallet");
	            System.out.println("3. Cash" + Reset_Color);
	            System.out.println();
	            
	            System.out.print(Bright_Green + Bold_Weight +"Your choice: " + Bright_Yellow);
	            String input = scanner.nextLine().trim(); // Read input as string to handle errors
	            System.out.println();
	            
	            if (isValidInteger(input)) {
	                paymentChoice = Integer.parseInt(input);
	                if (paymentChoice >= 1 && paymentChoice <= 3) {
	                    break; // Valid payment choice
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please select 1, 2, or 3." + Reset_Color);
	                }
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a number (1, 2, or 3)." + Reset_Color);
	            }
	        }

	        // Configure the payment strategy based on the user's choice
	        switch (paymentChoice) {
	            case 1:
	                strategyManager.setPaymentStrategy(new CreditCardPayment());
	                break;
	            case 2:
	                strategyManager.setPaymentStrategy(new DigitalWalletPayment());
	                break;
	            case 3:
	                strategyManager.setPaymentStrategy(new CashPayment());
	                break;
	        }

	        // Execute the payment using the selected strategy
	        strategyManager.executePayment(orderAmount); // Execute the chosen payment method

	        // Prompt and validate loyalty program selection
	        int loyaltyChoice = 0;
	        while (true) {
	        	System.out.println();
	            System.out.println(Bold_Weight + Bright_White + "Select loyalty program:" + Reset_Color);
	            System.out.println(Bold_Weight + Bright_White + "1. Basic Loyalty Program");
	            System.out.println("2. Premium Loyalty Program" + Reset_Color);
	            System.out.println();
	            System.out.print(Bold_Weight+Bright_Green + "Your choice: " + Bright_Yellow);
	            String input = scanner.nextLine().trim(); // Read input as string to handle errors
                
	            if (isValidInteger(input)) {
	                loyaltyChoice = Integer.parseInt(input);
	                if (loyaltyChoice == 1 || loyaltyChoice == 2) {
	                    break; // Valid loyalty choice
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please select 1 or 2." + Reset_Color);
	                }
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a number (1 or 2)." + Reset_Color);
	            }
	        }

	        // Configure loyalty program and calculate earned points
	        int loyaltyPoints = 0; // Initialize loyalty points
	        switch (loyaltyChoice) {
	            case 1:
	                strategyManager.setLoyaltyStrategy(new BasicLoyaltyProgram());
	                loyaltyPoints = strategyManager.applyLoyaltyPoints(orderAmount); // Calculate points
	                break;
	            case 2:
	                strategyManager.setLoyaltyStrategy(new PremiumLoyaltyProgram());
	                loyaltyPoints = strategyManager.applyLoyaltyPoints(orderAmount); // Calculate points
	                break;
	        }

	        // Display earned loyalty points and update the user's profile
	        System.out.println();
	        System.out.println(Bold_Weight + Bright_Magenta +"Earned Loyalty Points: "+ Bright_Yellow + loyaltyPoints );
	        
	        // Add loyalty points to the active user's profile
	        activeUser.addLoyaltyPoints(loyaltyPoints);
	        System.out.println();
	        System.out.println(Bold_Weight+ Bright_Green +"Loyalty points have been added to your profile."+ Reset_Color);
	   
	    	    
	    }
	    
	    // Helper method to validate if a string can be parsed as a double
	    private boolean isValidDouble(String input) {
	        try {
	            Double.parseDouble(input);
	            return true; // Input is a valid double
	        } catch (NumberFormatException e) {
	            return false; // Invalid double
	        }
	    }
	    
	    // Helper method to validate if a string can be parsed as an integer
	    private boolean isValidInteger(String input) {
	        try {
	            Integer.parseInt(input);
	            return true; // Input is a valid integer
	        } catch (NumberFormatException e) {
	            return false; // Invalid integer
	        }
	    }

	    
	    private void ViewFavoritesAndReorder() {
	        Scanner scanner = new Scanner(System.in);

	        // Handler to manage user confirmations and favorites
	        ConfirmationHandler confirmationHandler = new ConfirmationHandler(activeUser);

	        // Display section header for viewing and reordering favorite pizzas
	        System.out.println(Bold_Weight + Bright_Cyan + "==============================View Favorite Pizza &  ReOrder Favorite===============================");
	        System.out.println("" + Reset_Color);

	        // Prompt user to view their favorite pizzas
	        String viewFavs;
	        while (true) {
	            System.out.print(Bold_Weight + Bright_Green + "Do you want to view your favorite pizzas? (yes/no): " + Bright_Yellow);
	            viewFavs = scanner.nextLine().trim().toLowerCase();
	            System.out.println(Reset_Color + Bold_Weight + Bright_White+ "");

	            // Validate input for yes or no
	            if ("yes".equals(viewFavs) || "no".equals(viewFavs)) {
	                break;
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter 'yes' or 'no'." + Reset_Color);
	            }
	        }

	        if ("yes".equals(viewFavs)) {
	        	// Diplay user's favorite pizzas
	            confirmationHandler.viewUserFavorites();

	            // Prompt user to recorder a favorite pizza
	            String reorderChoice;
	            while (true) {
	            	System.out.println("" + Reset_Color);
	                System.out.print(Bold_Weight + Bright_Green + "Do you want to reorder a favorite pizza? (yes/no): " + Bright_Yellow);
	                reorderChoice = scanner.nextLine().trim().toLowerCase();
	                System.out.println("" + Reset_Color);

	                // Validate input for yes or no
	                if ("yes".equals(reorderChoice) || "no".equals(reorderChoice)) {
	                    break;
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter 'yes' or 'no'." + Reset_Color);
	                }
	            }
                 
	               // Check if user has favorite pizzas
	            if ("yes".equals(reorderChoice)) {
	                List<Pizza> favoritePizzas = activeUser.getFavoritePizzas();

	                if (favoritePizzas.isEmpty()) {
	                    System.out.println(Bold_Weight + Bright_Red + "You have no favorite pizzas to reorder.");
	                    return;
	                }

	                // Prompt user to select a favorite pizza for reorder
	                int favoriteNumber = 0;
	                while (true) {
	                	System.out.println("" + Reset_Color);
	                    System.out.print(Bold_Weight + Bright_Green +  "Enter the number of the favorite pizza you want to reorder: " + Bright_Yellow);
	                    String input = scanner.nextLine().trim();
                        System.out.println("" + Reset_Color); 
	                    
	                    if (isValidInteger(input)) {
	                        favoriteNumber = Integer.parseInt(input);
	                        if (favoriteNumber >= 1 && favoriteNumber <= favoritePizzas.size()) {
	                            break; // Valid selection
	                        } else {
	                            System.out.println(Bold_Weight + Bright_Red + "Invalid selection. Please choose a number between 1 and " + favoritePizzas.size() + Reset_Color);
	                        }
	                    } else {
	                        System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a valid number." + Reset_Color);
	                    }
	                }

	                // Retrieve and process the selected pizza for reorder
	                Pizza pizzaToReorder = favoritePizzas.get(favoriteNumber - 1);
	                System.out.println(Bold_Weight + Bright_White +"Reordering: " +Bright_Cyan+ pizzaToReorder);

	                // Proceed with reorder logic
	                handleReorder(pizzaToReorder);
	            }
	        }
	    }

	    //Handle the reorder process for a pizza.
	    private void handleReorder(Pizza pizzaToReorder) {
	        Scanner scanner = new Scanner(System.in);

	        // Generate the orderId
	        String orderId = generateOrderId();
	        String customerName = activeUser.getUsername();

	        // Retrive the base price of the selected pizza
	        double basePrice = getPriceForPizza(pizzaToReorder);

	        // Prompt user to enter quantity
	        int quantity = 0;
	        while (true) {
	            System.out.println("");
	            System.out.print(Bold_Weight + Bright_Green + "Enter quantity: " + Bright_Yellow);
	            String input = scanner.nextLine().trim();
	            System.out.println("");
	            if (isValidInteger(input)) {
	                quantity = Integer.parseInt(input);
	                if (quantity > 0) {
	                    break; // Valid quantity
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid quantity. Please enter a positive number." + Reset_Color);
	                }
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a valid number." + Reset_Color);
	            }
	        }

	        // Calculate total order amount before delivery charge
	        double orderAmount = basePrice * quantity;
	        System.out.println(Bold_Weight + Bright_White + "Total order amount (before delivery charge): " + Bright_Cyan + "Rs." + orderAmount);
	        System.out.println("");

	        // Prompt user to select order method (Pickup or Delivery)
	        String orderMethod;
	        while (true) {
	            System.out.print(Bold_Weight + Bright_Green + "Enter Order Method (P for Pickup, D for Delivery): " + Bright_Yellow);
	            orderMethod = scanner.nextLine().trim().toUpperCase();
	            System.out.println("");
	            if ("P".equals(orderMethod) || "D".equals(orderMethod)) {
	                break;
	            } else {
	                System.out.println(Bold_Weight + Bright_Red + "Invalid choice. Please enter 'P' for Pickup or 'D' for Delivery." + Reset_Color);
	            }
	        }

	        // Initialize variable for delivery details
	        String address = "";
	        double deliveryCharge = 0;

	        // If delivery is chosen, validate additional details
	        if ("D".equals(orderMethod)) {
	            // Prompt user for customer name
	            while (true) {
	                System.out.print(Bold_Weight + Bright_White + "Enter Customer Name: " + Bright_Yellow);
	                String input = scanner.nextLine().trim();
	                if (isValidCustomerName(input)) {
	                    customerName = input;
	                    break;
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid name! Please use only alphabetic characters and spaces." + Reset_Color);
	                }
	            }

	            // Prompt user for delivery address
	            while (true) {
	                System.out.print(Bold_Weight + Bright_White + "Enter Delivery Address: " + Bright_Yellow);
	                String input = scanner.nextLine().trim();
	                if (isValidAddress(input)) {
	                   // deliveryAddress = input;
	                    break;
	                } else {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid address! Please use only letters, numbers, commas, and spaces." + Reset_Color);
	                }
	            }

	            // Set fixed delivery charge
	            deliveryCharge = 300.0;
	        }

	        // Calculate total order amount including delivery charge
	        double totalOrderAmount = orderAmount + deliveryCharge;

	        // Create the order object and store it
	        Order order = new Order(orderId, customerName, pizzaToReorder, quantity, orderAmount, orderMethod, address);
	        orders.put(orderId, order);

	        // Display order summary
	        System.out.println();
	        System.out.println(Background_Bright_Magenta + Bold_Weight + Bright_White +"***********************************************************************************************************************************************");
	        System.out.println("                                     Order Summary:                                                                                    ");
	        System.out.println("                                                                                                                                               ");
	        System.out.println("Order Number: " + orderId+"                                                                                                                          ");
	        System.out.println("Customer Name: " + customerName+"                                                                                                                           ");
	        System.out.println("Delivery Address: " + address+"                                                                                                            ");
	        System.out.println("Pizza Details: " + pizzaToReorder+"                  ");
	        System.out.println("Quantity: " + quantity+"                                                                                                                                     ");
	        System.out.println("Order Amount : " + orderAmount +"                                                                                                    ");
	        System.out.println("Delivery Charge: " + deliveryCharge+"                                                                                                                           ");
	        System.out.println("Total Order Amount : " + totalOrderAmount+"                                                                                                                    ");
	        System.out.print(""+ Reset_Color);
	        System.out.print("");
	        
	        // Inform user about order tracking
	        System.out.println("\n" + Bright_Green + Bold_Weight + "You can check the order status and track the order in Main Menu Option 4: Real-Time Order Tracking." + Reset_Color);
	        System.out.print("");
	          
	        // Proceed to mange payment
	      	        HandlePayment();
	    }


	    
	    private double getPriceForPizza(Pizza pizza) {
	        // Define a map to store pizza prices based on type and size 
	        Map<String, Integer> pizzaPriceMap = new HashMap<>();
	        pizzaPriceMap.put("Deval Chiken Pizza_Large Pizza", 1450);
	        pizzaPriceMap.put("Chicken Pizza_Large Pizza", 1350);
	        pizzaPriceMap.put("Sosegus Pizza_Large Pizza", 1250);
	        pizzaPriceMap.put("Deval Chiken Pizza_Pan Pizza", 1050);
	        pizzaPriceMap.put("Chicken Pizza_Pan Pizza", 950);
	        pizzaPriceMap.put("Sosegus Pizza_Pan Pizza", 850);

	        // Construct the key using the pizza type and size
	        String pizzaType = pizza.getType().get(0); // Assuming type is stored as a list
	        String pizzaSize = pizza.getSize();
	        String key = pizzaType + "_" + pizzaSize;

	        // Retrieve the price from the map
	        return pizzaPriceMap.getOrDefault(key, 0); // Default to 0 if key not found
	    }
	    
	    private void ProvideFeedback() {
	    	// Initialize the feedback manager (singleton pattern ensure one instance)
	        FeedbackManager feedbackManager = FeedbackManager.getInstance(); // Use singleton
	        FeedbackInvoker feedbackInvoker = new FeedbackInvoker();
	        Scanner scanner = new Scanner(System.in);
            
	        // Display the feedback section header
	        System.out.println(Bold_Weight + Bright_Cyan + "::::::::::::::::::::::::::::::::::Customer Feedback Section::::::::::::::::::::::::::::::::::");
	        System.out.println("" + Reset_Color);

	        while (true) {
	            try {
	            	// Display the feedback menu options
	                System.out.println(Bold_Weight + Bright_White + "\nFeedback Menu:");
	                System.out.println("1. Add Feedback");
	                System.out.println("2. View All Feedback");
	                System.out.println("3. Show Top-Rated Pizzas");
	                System.out.println("4. Exit" + Reset_Color);
	                System.out.println();
	                System.out.println(Bold_Weight + Bright_Cyan + "*********************************************************************************************" + Reset_Color);
	                System.out.print(Bold_Weight + Bright_Green + "Choose an option: " + Bright_Yellow);

	                // Read user input and trim any extra spaces
	                String input = scanner.nextLine().trim(); // Read input as a string to handle non-integer cases
	                System.out.println("" + Reset_Color);

	                // Validate if input is an integer
	                if (!isValidInteger(input)) {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a number between 1 and 4." + Reset_Color);
	                    continue;
	                }

	                int choice = Integer.parseInt(input);

	                // Validate if the choice is within the valid range
	                if (choice < 1 || choice > 4) {
	                    System.out.println(Bold_Weight + Bright_Red + "Invalid choice! Please enter a number between 1 and 4." + Reset_Color);
	                    continue;
	                }

	                // Execute the corresponfing action based on the user's choice
	                switch (choice) {
	                    case 1 -> feedbackInvoker.executeCommand(new AddFeedbackCommand(feedbackManager, scanner));
	                    case 2 -> feedbackInvoker.executeCommand(new ViewAllFeedbackCommand(feedbackManager));
	                    case 3 -> feedbackInvoker.executeCommand(new DisplayTopRatedCommand(feedbackManager));
	                    case 4 -> {
	                    	//Exit the feedback menu
	                        System.out.println(Bold_Weight + Bright_Green + "Exiting feedback menu. Thank you!" + Reset_Color);
	                        return;
	                    }
	                    default -> {
	                        // This case will not be reached due to prior validation
	                    }
	                }
	            } catch (Exception e) {
	                System.out.println(Bold_Weight + Bright_Red + "An unexpected error occurred. Please try again." + Reset_Color);
	                scanner.nextLine(); // Clear the scanner buffer
	            }
	        }
	    }
}

	
		
	
	

