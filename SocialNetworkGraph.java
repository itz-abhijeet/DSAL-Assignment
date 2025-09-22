import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SocialNetworkGraph {

    private int numberOfUsers;
    private int[][] adjMatrix;
    private ArrayList<ArrayList<Integer>> adjList;
    private ArrayList<ArrayList<Integer>> inverseAdjList;

    public SocialNetworkGraph(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
        
        // Adjacency Matrix: O(N^2) space
        adjMatrix = new int[numberOfUsers][numberOfUsers];
        
        // Adjacency List: O(N + E) space
        adjList = new ArrayList<>(numberOfUsers);
        for (int i = 0; i < numberOfUsers; i++) {
            adjList.add(new ArrayList<>());
        }
        
        // Inverse Adjacency List: O(N + E) space
        inverseAdjList = new ArrayList<>(numberOfUsers);
        for (int i = 0; i < numberOfUsers; i++) {
            inverseAdjList.add(new ArrayList<>());
        }
    }

    // Adds a directed connection (A follows B)
    public void addConnection(int follower, int followed) {
        // Adjacency Matrix
        adjMatrix[follower][followed] = 1;
        
        // Adjacency List
        adjList.get(follower).add(followed);
        
        // Inverse Adjacency List
        inverseAdjList.get(followed).add(follower);
    }
    
    // Outputs a user's immediate connections (who they follow)
    public void getConnections(int userId) {
        System.out.println("\nImmediate connections for user " + userId + " (using Adjacency List):");
        for (Integer friend : adjList.get(userId)) {
            System.out.println("- User " + friend);
        }
    }
    
    // Outputs who follows a specific user
    public void getFollowers(int userId) {
        System.out.println("\nUsers who follow user " + userId + " (using Inverse Adjacency List):");
        for (Integer follower : inverseAdjList.get(userId)) {
            System.out.println("- User " + follower);
        }
    }

    public static void main(String[] args) {
        int numUsers = 5; // Users 0 to 4
        SocialNetworkGraph network = new SocialNetworkGraph(numUsers);

        // A (0) follows B (1) and C (2)
        network.addConnection(0, 1);
        network.addConnection(0, 2);
        
        // D (3) follows A (0)
        network.addConnection(3, 0);

        // B (1) follows A (0) and D (3)
        network.addConnection(1, 0);
        network.addConnection(1, 3);
        
        // Output who user 0 follows
        network.getConnections(0);
        
        // Output who follows user 0
        network.getFollowers(0);
        
        // Output who user 1 follows
        network.getConnections(1);
        
        // Output who follows user 1
        network.getFollowers(1);
    }
}