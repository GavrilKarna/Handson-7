package Task2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    // transactionId, type, amount, timestamp, description
    private static int counter = 1000;
    private String transactionId;
    private String type;
    private double amount;
    private String timestamp;
    private String description;

    // Constructor dan methods
    public Transaction(String type, double amount, String description) {
        this.transactionId = "TX" + (++counter);
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + transactionId + "] " + timestamp + " | " + type + " | " + amount + " | " + description;
    }
}
