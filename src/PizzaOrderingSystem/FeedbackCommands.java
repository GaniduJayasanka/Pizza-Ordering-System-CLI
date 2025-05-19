package PizzaOrderingSystem;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FeedbackCommands {

    // Base interface for all feedback-related commands
    public interface FeedbackCommand {
        void execute();
    }

    // Color Codes for Add the background colors
    public static final String Backgroung_Green = "\033[42m";
    public static final String Background_Red   = "\033[41m"; 
    public static final String Background_Blue  = "\033[44m";   
    public static final String Background_White = "\033[107m";
    public static final String Background_Bright_Magenta = "\033[105m";
    
    // Text Style Codes
    public static final String Bold_Weight = "\033[1m";
    public static final String Reset_Color = "\033[0m";  
    
    // Text Color Codes
    public static final String Bright_White   = "\033[97m";
    public static final String Bright_Cyan    = "\033[96m";
    public static final String Bright_Yellow  = "\033[93m";
    public static final String Bright_Red     = "\033[91m";
    public static final String Bright_Magenta = "\033[95m";
    public static final String Yellow_color   = "\033[33m";
    public static final String Green_color    = "\033[32m";
    public static final String Bright_Green   = "\033[92m";
    public static final String Bright_Blue    = "\033[34m";

    // Add Feedback Command
    public static class AddFeedbackCommand implements FeedbackCommand {
        private FeedbackManager feedbackManager;
        private Scanner scanner;

        public AddFeedbackCommand(FeedbackManager feedbackManager, Scanner scanner) {
            this.feedbackManager = feedbackManager;
            this.scanner = scanner;
        }

        @Override
        public void execute() {
            // Validate customer name
            String customerName = null;
            while (customerName == null) {
                System.out.print(Bold_Weight + Bright_White + "Enter your name: " + Bright_Yellow);
                String input = scanner.nextLine().trim();
                if (isValidName(input)) {
                    customerName = input;
                } else {
                    System.out.println(Bold_Weight + Bright_Red + "Invalid name! Please use only alphabetic characters and spaces.");
                }
            }

            // Validate pizza name (only letters and spaces)
            String pizzaName = null;
            while (pizzaName == null) {
                System.out.print(Bold_Weight + Bright_White + "Enter pizza name: " + Bright_Yellow);
                String input = scanner.nextLine().trim();
                if (isValidPizzaName(input)) {
                    pizzaName = input;
                } else {
                    System.out.println(Bold_Weight + Bright_Red + "Invalid pizza name! Please use only alphabetic characters and spaces.");
                }
            }

            // Validate feedback comment (only letters and spaces)
            String feedbackComment = null;
            while (feedbackComment == null) {
                System.out.print(Bold_Weight + Bright_White + "Enter feedback: " + Bright_Cyan);
                String input = scanner.nextLine().trim();
                if (isValidFeedback(input)) {
                    feedbackComment = input;
                } else {
                    System.out.println(Bold_Weight + Bright_Red + "Invalid feedback! Please use only alphabetic characters and spaces.");
                }
            }

            // Validate rating
            int rating = 0;
            while (true) {
                System.out.print(Bold_Weight + Bright_White + "Enter rating (1-10): " + Bright_Green);
                String input = scanner.nextLine().trim();
                if (isValidInteger(input)) {
                    rating = Integer.parseInt(input);
                    if (rating >= 1 && rating <= 10) {
                        break; // Valid rating
                    } else {
                        System.out.println(Bold_Weight + Bright_Red + "Invalid rating! Please enter a number between 1 and 10.");
                    }
                } else {
                    System.out.println(Bold_Weight + Bright_Red + "Invalid input! Please enter a valid number.");
                }
            }

            // Add feedback to the manager
            PizzaFeedback feedback = new PizzaFeedback(customerName, pizzaName, rating, feedbackComment);
            feedbackManager.addFeedback(feedback);
            System.out.println();
            System.out.println(Bold_Weight + Bright_White + Backgroung_Green + "Feedback added successfully." + Reset_Color);
        }

        // Helper method to validate names
        private boolean isValidName(String name) {
            return name.matches("[a-zA-Z ]+") && !name.isBlank();
        }

        // Helper method to validate pizza names (only alphabetic characters and spaces)
        private boolean isValidPizzaName(String name) {
            return name.matches("[a-zA-Z ]+") && !name.isBlank();
        }

        // Helper method to validate feedback (only alphabetic characters and spaces)
        private boolean isValidFeedback(String feedback) {
            return feedback.matches("[a-zA-Z ]+") && !feedback.isBlank();
        }

        // Helper method to validate integers
        private boolean isValidInteger(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }


    // View All Feedback Command
    public static class ViewAllFeedbackCommand implements FeedbackCommand {
        private FeedbackManager feedbackManager;

        public ViewAllFeedbackCommand(FeedbackManager feedbackManager) {
            this.feedbackManager = feedbackManager;
        }

        @Override
        public void execute() {
            System.out.println(Bold_Weight + Bright_Magenta + "::::::::::::::::::::::::::::::::::Customer Feedbacks::::::::::::::::::::::::::::::::::");
            System.out.println(Reset_Color);
            
            List<PizzaFeedback> feedbackList = feedbackManager.getAllFeedback();
            if (feedbackList.isEmpty()) {
                System.out.println(Bold_Weight + Bright_Red + "No feedback available.");
            } else {
                System.out.println(Bold_Weight + Bright_White + "All Feedback:" + Yellow_color);
                for (PizzaFeedback feedback : feedbackList) {
                    System.out.println(feedback);
                }
            }
        }
    }

    // Display Top-Rated Pizzas Command
    public static class DisplayTopRatedCommand implements FeedbackCommand {
        private FeedbackManager feedbackManager;

        public DisplayTopRatedCommand(FeedbackManager feedbackManager) {
            this.feedbackManager = feedbackManager;
        }

        @Override
        public void execute() {
            System.out.println(Bold_Weight + Bright_Green + "::::::::::::::::::::::::::::::::::Top Rated Pizzas::::::::::::::::::::::::::::::::::");
            System.out.println(Reset_Color);
            
            Map<String, Double> averageRatings = feedbackManager.getAverageRatings();

            if (averageRatings.isEmpty()) {
                System.out.println(Bold_Weight + Bright_Red + "No feedback available to determine top-rated pizzas.");
                return;
            }

            System.out.println(Bold_Weight + Bright_White + "Top-Rated Pizzas:" + Bold_Weight + Yellow_color);
            averageRatings.entrySet()
                          .stream()
                          .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue())) // Descending order
                          .forEach(entry -> 
                              System.out.printf("Pizza: %s, Average Rating: %.2f%n", 
                                                entry.getKey(), entry.getValue()));
        }
    }
}
