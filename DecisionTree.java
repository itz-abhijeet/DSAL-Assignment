import java.util.*;

// Node for Decision Tree
class Node {
    String decision;
    Node yes, no;

    Node(String decision) {
        this.decision = decision;
        this.yes = this.no = null;
    }
}

public class DecisionTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Build Decision Tree
        
        Node root = new Node("Do you prefer Science?");
        root.yes = new Node("Do you prefer Computers?");
        root.no = new Node("Do you prefer Arts?");
        
        root.yes.yes = new Node("Go for Comxputer Science!");
        root.yes.no = new Node("Go for Biology!");
        root.no.yes = new Node("Go for Literature!");
        root.no.no = new Node("Go for Commerce!");

        // Traverse interactively with Yes/No
        Node current = root;
        while (current.yes != null || current.no != null) {
            System.out.println(current.decision + " (yes/no): ");
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals("yes")) {
                current = current.yes;
            } else if (ans.equals("no")) {
                current = current.no;
            } else {
                System.out.println("Please answer yes or no.");
            }
        }
        System.out.println("Decision: " + current.decision);
        sc.close();
    }
}
