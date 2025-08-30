import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;

// Node for Decision Tree
class Node {
    String decision;
    Node yes, no;

    Node(String decision) {
        this.decision = decision;
    }
}

public class DecisionTreeSwing {

    private JFrame frame;
    private JLabel questionLabel;
    private JButton yesBtn, noBtn;
    private Node current;

    public DecisionTreeSwing() {
        // Build tree
        Node root = new Node("Do you like Science?");
        
        root.yes = new Node("Do you like Computers?");
        root.yes.yes = new Node("Go for Computer Science!");
        root.yes.no = new Node("Go for Biology!");


        root.no = new Node("Do you like Arts?");
        root.no.yes = new Node("Go for Literature!");
        root.no.no = new Node("Try another field!");

        current = root;

        // GUI
        frame = new JFrame("Decision Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel(current.decision, SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(questionLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        yesBtn = new JButton("Yes");
        noBtn = new JButton("No");
        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        yesBtn.addActionListener(e -> moveToNext("yes"));
        noBtn.addActionListener(e -> moveToNext("no"));

        frame.setVisible(true);
    }

    private void moveToNext(String answer) {
        if (current.yes == null && current.no == null) {
            JOptionPane.showMessageDialog(frame, "Final Decision: " + current.decision);
            return;
        }

        current = answer.equals("yes") ? current.yes : current.no;

        if (current.yes == null && current.no == null) {
            questionLabel.setText("Final Decision: " + current.decision);
            yesBtn.setEnabled(false);
            noBtn.setEnabled(false);
        } else {
            questionLabel.setText(current.decision);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DecisionTreeSwing::new);
    }
}
