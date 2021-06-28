
public class Bonus {
    static int max_depth = 5; ///Max tree depth
    static int maxChildrenNumber=4; /// actual number -1
    static int childNumber = 0;
    static int matrixSize = 2;
    static int[][] adjacencyMatrix;

    public static void resizeMatrix() {
        /*Double the matrix capacity*/
        int[][] auxMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j)
                auxMatrix[i][j] = adjacencyMatrix[i][j];
        }
        matrixSize *= 2;
        adjacencyMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize / 2; ++i) {
            for (int j = 0; j < matrixSize / 2; ++j)
                adjacencyMatrix[i][j] = auxMatrix[i][j];
        }
    }

    public static void shrinkMatrix(int n) {
        int[][] auxMatrix = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                auxMatrix[i][j] = adjacencyMatrix[i][j];
        }
        adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                adjacencyMatrix[i][j] = auxMatrix[i][j];
        }
    }

    public static void addEdge(int position, int parent) {
        if (position >= matrixSize) {
            resizeMatrix();
        }
        adjacencyMatrix[position][parent] = adjacencyMatrix[parent][position] = 1;
    }

    public static void treeGenerator(String front, int level, int parent) {
        /* Recursive function for printing the tree structure.
        @@@ For each node we randomly generate a number of children (0-3) until the maximum depth is achieved ( Max. depth is required in order to avoid infinite generation of children ) @@@
        PARAMS: front: string used for indentation of the tree
                level: the current depth in the tree
                parent: the parent of the current note*/
        int n;
        if (childNumber != 0)
            addEdge(childNumber, parent);
        if (level <= max_depth)
            n = (int) (Math.random() * maxChildrenNumber);
        else n = 0;
        if (n == 0) {
            System.out.printf("%s-leaf%d\n", front, childNumber);
        } else {
            System.out.printf("%s+node%d\n", front, childNumber);
            front += "  ";
            int aux = childNumber;
            for (int i = 0; i < n; ++i) {
                ++childNumber;
                treeGenerator(front, ++level, aux);
            }
        }

    }

    public static void printMatrix(int n) {
        /* Print a n*n matrix */
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                System.out.print(adjacencyMatrix[i][j]);
            System.out.print('\n');
        }
    }

    public static void main(String[] args) {

        adjacencyMatrix = new int[matrixSize][matrixSize];

        treeGenerator(" ", 0, 0); /* Generate a random tree*/
        //printMatrix(matrixSize); //---Uncomment to see how the shrink is made /* Print the initial matrix of the tree*/
        System.out.println(childNumber);
        shrinkMatrix(childNumber+1); /* Because we are storring the matrix in a matrix of size [2^n][2^n] */
        printMatrix(childNumber+1); /* Print the shrinked matrix */

    }
}

