/*Let n be an odd integer given as a command line argument. Validate the argument!
Create a n x n matrix, representing the adjacency matrix of a random graph .
Display on the screen the generated matrix (you might want to use the geometric shapes from the Unicode chart to create a "pretty" representation of the matrix).
Verify if the generated graph is connected and display the connected components (if it is not).
Assuming that the generated graph is connected, implement an algorithm that creates a partial tree of the graph. Display the adjacency matrix of the tree.
For larger n display the running time of the application in nanoseconds (DO NOT display the matrices). Try n > 30_000. You might want to adjust the JVM Heap Space using the VM options -Xms4G -Xmx4G.
Launch the application from the command line, for example: java Lab1 100.*/

import java.util.*;

public class TaskC {
    public static boolean[][] adjacencyMatrix;
    public static boolean[] visited;
    public static boolean[][] treeAdjacencyMatrix;

    static int n = 0;
    static String componentsBuffer = "";

    public static void DFS(int start) {
        if (!visited[start]) {
            visited[start] = true;
            componentsBuffer += (start);
            for (int j = 0; j < n; ++j) {
                if (adjacencyMatrix[start][j] && !visited[j]) {
                    treeAdjacencyMatrix[start][j] = treeAdjacencyMatrix[j][start] = true;
                    componentsBuffer += ',';
                    DFS(j);
                }
            }
        }
    }

    public static void printMatrix(boolean[][] matrix) {
        /* Matrix print in binary format */
        if(n>30000)
            return;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("%d ", matrix[i][j] ? 1 : 0);
            }
            System.out.print('\n');
        }
    }

    public static void graphInitialization() {
        adjacencyMatrix = new boolean[n][n];
        visited = new boolean[n];
        Random randomBoolean = new Random();
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = randomBoolean.nextBoolean();
    }

    public static int checkConnectivity() {
        /* Check if the graph is connected and compute the partial tree */
        int count = 0;
        treeAdjacencyMatrix = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                componentsBuffer += ("Component[" + count + "]:{");
                DFS(i);
                componentsBuffer += "}\n";
                ++count;
            }
        }
        if (count == 1) {
            connected();
        } else {
            disconnected();
        }
        return count;
    }

    public static void connected() {
        System.out.println("The graph is connected");
        System.out.println("The adjacency matrix of the spanning tree generated for the graph:");
        printMatrix(treeAdjacencyMatrix);
    }

    public static void disconnected() {
        System.out.println("The graph is disconnected. The components are:");
        System.out.println(componentsBuffer);
    }

    public static void printTime(long startTime){
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.printf("Total time: %d nanoseconds.",totalTime);
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if (args.length > 0) {

            try {
                n = Integer.parseInt(args[0]);
                if (n % 2 == 0) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    System.out.println("The argument is not an integer number");
                    return;
                } else if (e instanceof IllegalArgumentException) {
                    System.out.println("The integer is not odd.");
                    return;
                }
            }

            graphInitialization();
            printMatrix(adjacencyMatrix);
            checkConnectivity();

        } else System.out.print("No argument given.");

        printTime(startTime);
    }
}
