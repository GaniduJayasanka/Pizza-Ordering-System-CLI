package PizzaOrderingSystem;


// Promotion Strategy Interface
 interface PromotionStrategy {
	 
	 //Method to apply a promotion based on order amount and quantity
    double applyPromotion(double orderAmount, int quantity);
}

// Seasonal Promotion: Applies a discount percentage
 class SeasonalPromotion implements PromotionStrategy {
    private final double discountPercentage; // Discount percentage


    // Constructor to set the discount percentage
    public SeasonalPromotion(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyPromotion(double orderAmount, int quantity) {
        // Apply the seasonal discount percentage
    	return orderAmount - (orderAmount * (discountPercentage / 100));
    }
}


// Bulk Purchase Promotion: Applies a fixed discount if the quantity exceeds a threshold
 class BulkPurchasePromotion implements PromotionStrategy {
    private final int thresholdQuantity; // Minimum quantity for discount
    private final double discountAmount; // Fixed discount amount

    // Constructor to set the threshold quantity and discount amount
    public BulkPurchasePromotion(int thresholdQuantity, double discountAmount) {
        this.thresholdQuantity = thresholdQuantity;
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyPromotion(double orderAmount, int quantity) {
       // Apply bulk discount if the quantity meets the threshold
    	if (quantity >= thresholdQuantity) {
            return orderAmount - discountAmount;
        }
        return orderAmount; // No discount if the quantity is below the threshold
    }
}

 // No Promotion: No discount applied
 class NoPromotion implements PromotionStrategy {
    @Override
    public double applyPromotion(double orderAmount, int quantity) {
        return orderAmount; // No discount
    }
}

 // Context Class to manage and apply promotions
public class PromotionContext {
    private PromotionStrategy promotionStrategy;

    // Set the current strategy
    public void setPromotionStrategy(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    // Apply the selected promotion
    public double applyPromotion(double orderAmount, int quantity) {
        if (promotionStrategy == null) {
            promotionStrategy = new NoPromotion(); // Default strategy
        }
        return promotionStrategy.applyPromotion(orderAmount, quantity);
    }

    // Preview the discounted price without applying the promotion
    public double calculateDiscountedPrice(double orderAmount, int quantity) {
        if (promotionStrategy == null) {
            return orderAmount; // No promotion applied
        }
        return promotionStrategy.applyPromotion(orderAmount, quantity);
    }
}
