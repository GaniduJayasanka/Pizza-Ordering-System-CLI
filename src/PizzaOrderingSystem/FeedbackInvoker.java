package PizzaOrderingSystem;

import java.util.ArrayList;
import java.util.List;

public class FeedbackInvoker {
    private final List<FeedbackCommands.FeedbackCommand> commandHistory = new ArrayList<>();

    public void executeCommand(FeedbackCommands.FeedbackCommand command) {
        command.execute();
        commandHistory.add(command); // Store command in history
    }
}
