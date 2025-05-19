package PizzaOrderingSystem;

import java.util.*;

public class FeedbackManager {
    private static FeedbackManager instance; // Singleton instance
    private final List<PizzaFeedback> feedbackList = new ArrayList<>();

    // Private constructor to prevent instantiation
    private FeedbackManager() {}

    // Public method to get the singleton instance
    public static FeedbackManager getInstance() {
        if (instance == null) {
            instance = new FeedbackManager();
        }
        return instance;
    }

    // Method to add feedback
    public void addFeedback(PizzaFeedback feedback) {
        feedbackList.add(feedback);
    }

    // Method to get all feedback
    public List<PizzaFeedback> getAllFeedback() {
        return feedbackList;
    }

    // Method to get average ratings
    public Map<String, Double> getAverageRatings() {
        Map<String, List<Integer>> ratingsByPizza = new HashMap<>();
        for (PizzaFeedback feedback : feedbackList) {
            ratingsByPizza.computeIfAbsent(feedback.getPizzaName(), k -> new ArrayList<>())
                          .add(feedback.getRating());
        }
        Map<String, Double> averageRatings = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : ratingsByPizza.entrySet()) {
            List<Integer> ratings = entry.getValue();
            double average = ratings.stream().mapToInt(Integer::intValue).average().orElse(0);
            averageRatings.put(entry.getKey(), average);
        }
        return averageRatings;
    }
}
