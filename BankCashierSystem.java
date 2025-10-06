import java.util.LinkedList;
import java.util.Queue;

public class BankCashierSystem {

    // A queue to hold the customers
    private Queue<String> customerQueue;

    public BankCashierSystem() {
        this.customerQueue = new LinkedList<>();
    }

    /**
     * Adds a new customer to the back of the queue.
     *
     * @param customerName The name of the customer to add.
     */
    public void addCustomer(String customerName) {
        System.out.println("✅ " + customerName + " has joined the queue.");
        customerQueue.add(customerName);
    }

    /**
     * Serves the customer at the front of the queue.
     *
     * @return The name of the customer being served, or null if the queue is empty.
     */
    public String serveCustomer() {
        if (customerQueue.isEmpty()) {
            System.out.println("😔 The queue is empty. No customers to serve.");
            return null;
        }
        String servedCustomer = customerQueue.remove();
        System.out.println("👨‍💼 " + servedCustomer + " is now being served.");
        return servedCustomer;
    }

    /**
     * Displays all customers currently in the queue.
     */
    public void displayQueue() {
        if (customerQueue.isEmpty()) {
            System.out.println("The queue is currently empty.");
        } else {
            System.out.println("🚶‍♀️ Current customers in queue: " + customerQueue);
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isQueueEmpty() {
        return customerQueue.isEmpty();
    }

    /**
     * Gets the number of customers in the queue.
     *
     * @return The number of customers.
     */
    public int getQueueSize() {
        return customerQueue.size();
    }

    public static void main(String[] args) {
        BankCashierSystem bank = new BankCashierSystem();

        System.out.println("--- Bank Cashier System Simulation ---");

        // Add some customers
        bank.addCustomer("Alice");
        bank.addCustomer("Bob");
        bank.addCustomer("Charlie");
        System.out.println("--------------------------------------");

        // Display the current queue
        bank.displayQueue();
        System.out.println("Number of customers: " + bank.getQueueSize());
        System.out.println("--------------------------------------");

        // Serve a customer
        bank.serveCustomer();
        bank.displayQueue();
        System.out.println("--------------------------------------");

        // Add another customer
        bank.addCustomer("David");
        bank.displayQueue();
        System.out.println("--------------------------------------");

        // Serve the remaining customers
        bank.serveCustomer();
        bank.serveCustomer();
        bank.serveCustomer();
        System.out.println("--------------------------------------");

        // Try to serve from an empty queue
        bank.serveCustomer();
        bank.displayQueue();
    }
}