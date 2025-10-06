import java.util.Scanner;
import java.util.Arrays;

class OBST {

    // Helper class to store node key and its search frequency
    static class Node {
        String key;
        int frequency;

        Node(String key, int frequency) {
            this.key = key;
            this.frequency = frequency;
        }
    }

    // Class to represent the nodes of the actual tree structure
    static class TreeNode {
        String key;
        TreeNode left, right;

        TreeNode(String key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private static int[][] cost; // Stores the minimum search cost for each subarray
    private static int[][] root; // Stores the root of the optimal subtree for each subarray
    private static Node[] nodes; // Stores the sorted keys and their frequencies

    /**
     * Builds the Optimal Binary Search Tree using dynamic programming.
     *
     * @param keys The array of command strings (must be sorted).
     * @param frequencies The array of frequencies for each command.
     */
    public static void buildOBST(String[] keys, int[] frequencies) {
        int n = keys.length;
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(keys[i], frequencies[i]);
        }

        cost = new int[n + 1][n + 1];
        root = new int[n + 1][n + 1];

        // Initialize cost for single-node subtrees
        for (int i = 1; i <= n; i++) {
            cost[i][i] = nodes[i - 1].frequency;
            root[i][i] = i;
        }

        // Fill the cost and root tables for subtrees of increasing length
        for (int length = 2; length <= n; length++) {
            for (int i = 1; i <= n - length + 1; i++) {
                int j = i + length - 1;
                cost[i][j] = Integer.MAX_VALUE;
                int sumFrequencies = 0;
                // Calculate sum of frequencies for the current subarray
                for (int k = i - 1; k < j; k++) {
                    sumFrequencies += nodes[k].frequency;
                }

                // Try each node 'r' as the root of the current subtree
                for (int r = i; r <= j; r++) {
                    // Calculate the total cost for this root choice
                    int currentCost = (r > i ? cost[i][r - 1] : 0) + 
                                      (r < j ? cost[r + 1][j] : 0) + 
                                      sumFrequencies;
                    // Update cost and root if a better combination is found
                    if (currentCost < cost[i][j]) {
                        cost[i][j] = currentCost;
                        root[i][j] = r;
                    }
                }
            }
        }
    }

    /**
     * Constructs the actual tree from the root table.
     * @param i The starting index of the subarray.
     * @param j The ending index of the subarray.
     * @return The root of the constructed subtree.
     */
    private static TreeNode constructTree(int i, int j) {
        if (i > j) {
            return null; // Base case: no nodes in this range
        }
        int r = root[i][j]; // Get the optimal root for this range
        TreeNode treeNode = new TreeNode(nodes[r - 1].key);
        treeNode.left = constructTree(i, r - 1); // Recursively build left subtree
        treeNode.right = constructTree(r + 1, j); // Recursively build right subtree
        return treeNode;
    }

    /**
     * Performs a search operation on the OBST and returns the search path length.
     * @param tree The root of the tree to search.
     * @param key The key to search for.
     * @return The length of the search path, or -1 if not found.
     */
    public static int searchOBST(TreeNode tree, String key) {
        if (tree == null) {
            return -1; // Not found
        }
        int pathLength = 1;
        TreeNode current = tree;
        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                return pathLength; // Found the key
            }
            pathLength++;
        }
        return -1; // Not found
    }

    /**
     * Inserts a new node into a normal BST.
     *
     * @param root The root of the BST.
     * @param key The key to insert.
     */
    private static void insertIntoBST(TreeNode root, String key) {
        if (key.compareTo(root.key) < 0) {
            if (root.left == null) {
                root.left = new TreeNode(key);
            } else {
                insertIntoBST(root.left, key); // Recursive insert into left subtree
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(key);
            } else {
                insertIntoBST(root.right, key); // Recursive insert into right subtree
            }
        }
    }
    
    /**
     * Performs a search on a normal BST.
     * @param root The root of the BST.
     * @param key The key to search for.
     * @return The length of the search path, or -1 if not found.
     */
    private static int searchBST(TreeNode root, String key) {
        if (root == null) {
            return -1; // Not found
        }
        int pathLength = 1;
        TreeNode current = root;
        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                return pathLength; // Found the key
            }
            pathLength++;
        }
        return -1; // Not found
    }

    /**
     * Driver method to demonstrate the OBST and BST comparison with user input.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of keys: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String[] keys = new String[n];
        int[] frequencies = new int[n];

        System.out.println("Enter the keys and their frequencies, separated by a space (e.g., 'git-add 10'):");
        for (int i = 0; i < n; i++) {
            System.out.print("Key " + (i + 1) + ": ");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            keys[i] = parts[0];
            try {
                frequencies[i] = Integer.parseInt(parts[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter a valid key and integer frequency.");
                i--; // Decrement i to re-enter the data for the current key
                continue;
            }
        }
        scanner.close();

        // Sort the keys and their frequencies together.
        Node[] sortedNodes = new Node[n];
        for (int i = 0; i < n; i++) {
            sortedNodes[i] = new Node(keys[i], frequencies[i]);
        }
        Arrays.sort(sortedNodes, (a, b) -> a.key.compareTo(b.key));
        
        String[] sortedKeys = new String[n];
        int[] sortedFrequencies = new int[n];
        for(int i = 0; i < n; i++) {
            sortedKeys[i] = sortedNodes[i].key;
            sortedFrequencies[i] = sortedNodes[i].frequency;
        }

        // OBST Implementation
        buildOBST(sortedKeys, sortedFrequencies);
        TreeNode obstRoot = constructTree(1, sortedKeys.length);
        System.out.println("\n--- OBST Analysis ---");
        System.out.println("Cost of OBST: " + cost[1][sortedKeys.length]);
        System.out.println("Search Path Lengths (OBST):");
        for (String key : sortedKeys) {
            System.out.println("  " + key + ": " + searchOBST(obstRoot, key));
        }

        System.out.println("\n-----------------");
        
        // Normal BST Implementation
        TreeNode bstRoot = new TreeNode(sortedKeys[0]);
        for (int i = 1; i < sortedKeys.length; i++) {
            insertIntoBST(bstRoot, sortedKeys[i]);
        }
        
        System.out.println("--- Normal BST Analysis ---");
        System.out.println("Search Path Lengths (Normal BST):");
        for (String key : sortedKeys) {
            System.out.println("  " + key + ": " + searchBST(bstRoot, key));
        }
    }
}