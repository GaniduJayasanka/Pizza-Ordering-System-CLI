package PizzaOrderingSystem;



//Payment Strategy Interface
interface PaymentStrategy {
	
	// Method to process the payment
 void pay(double amount);
}

//Loyalty Strategy Interface
interface LoyaltyStrategy {
	//Method to calculate loyalty point based on payment amount
 int calculatePoints(double amount);
}

//StrategyManager Class: Manages Payment and Loyalty Strategies
class StrategyManager {
 private PaymentStrategy paymentStrategy; // Current Payment Strategy
 private LoyaltyStrategy loyaltyStrategy; // Current Loyalty Strategy

 // Setters for strategies
 public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
     this.paymentStrategy = paymentStrategy;
 }

 public void setLoyaltyStrategy(LoyaltyStrategy loyaltyStrategy) {
     this.loyaltyStrategy = loyaltyStrategy;
 }

 // Execute Payment
 public void executePayment(double amount) {
     if (paymentStrategy != null) {
         paymentStrategy.pay(amount);
     } else {
         System.out.println("No payment method selected!");
     }
 }

 // Apply Loyalty Points
 public int applyLoyaltyPoints(double amount) {
     if (loyaltyStrategy != null) {
         return loyaltyStrategy.calculatePoints(amount);
     } else {
         System.out.println("No loyalty program selected!");
         return 0;
     }
 }
}

//Payment Strategy Implementations

// Credit Card Payement Stratehy
class CreditCardPayment implements PaymentStrategy {
 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " using Credit Card.");
 }
}

// Digital Wallet Payment Strategy
class DigitalWalletPayment implements PaymentStrategy {
 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " using Digital Wallet.");
 }
}

// Cash payment strategy
class CashPayment implements PaymentStrategy {
 @Override
 public void pay(double amount) {
     System.out.println("Paid " + amount + " in Cash.");
 }
}

//Loyalty Strategy Implementations

// Basic Loyalty Program: 1 Point for every 10 units of currency
class BasicLoyaltyProgram implements LoyaltyStrategy {
 @Override
 public int calculatePoints(double amount) {
     return (int) (amount / 10); 
 }
}

// Premium Loyalty Program: 1 point for every 5 units of currency
class PremiumLoyaltyProgram implements LoyaltyStrategy {
 @Override
 public int calculatePoints(double amount) {
     return (int) (amount / 5); // 1 point for every 5 currency units
 }


     
}
