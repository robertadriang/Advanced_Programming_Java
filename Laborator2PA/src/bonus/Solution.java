package bonus;
import java.util.Arrays;
/**
 * This is the solution class that solves a Transportation problem.
 */
public class Solution {
    private int[][] unitsMatrix;
    private int[] sourceValues;
    private int[] destinationValues;
    private  int totalCost;
    int toBeTransferred;
    /**
     * Constructor that allocates memory for the arrays based on the problem size
     * @param p the problem statement
     */
    public Solution(Problem p) {
        this.unitsMatrix = new int[p.getSourceSize()][p.getDestinationsSize()];
        this.sourceValues = new int[p.getSourceSize()];
        this.destinationValues = new int[p.getDestinationsSize()];
        this.totalCost = 0;

    }

    /**
     * Function that solves the problem given in the
     * @param pr - problem statement
     * @return -1 if the problem doesn't satisfy the demand=supply / the cost of the transfer otherwise
     */
    public int solveProblem(Problem pr){

        if (checkSolutionExistence(pr) != null) return -1;

        for(int i=0;i<sourceValues.length;++i){
            for(int j=0;j<destinationValues.length;++j){
                if(sourceValues[i]>0){
                    if(destinationValues[j]>0){
                        unitsMatrix[i][j]=Math.min(sourceValues[i],destinationValues[j]);
                        sourceValues[i]-=unitsMatrix[i][j];
                        destinationValues[j]-=unitsMatrix[i][j];
                    }
                }
            }
        }
        printSolution();
        return calculateTotalCost(pr);
    }

    private int[][] computeLinePenalties(int [][]matrix){
        for(int i=0;i<matrix.length-2;++i)
        {
            if(matrix[i][matrix[0].length-2]!=0) {
                int[] aux = new int[matrix[0].length - 2];
                for (int j = 0; j < matrix[0].length - 2; ++j)
                    aux[j] = matrix[i][j];
                Arrays.sort(aux);
                for(int k=0;k<aux.length-1;++k)
                {
                    if(aux[k]!=0){
                        matrix[i][matrix[0].length - 1] = Math.abs(aux[k] - aux[k+1]);
                        break;
                    }
                }

            }
        }
        return matrix;
    }

    private int[][] computeColumnPenalties(int[][]matrix){
        for(int j=0;j<matrix[0].length-2;++j)
        {
            if(matrix[matrix.length-2][j]!=0){
                int[] aux=new int[matrix.length-2];
                for(int i=0;i<matrix.length-2;++i)
                    aux[i]=matrix[i][j];
                Arrays.sort(aux);
                for(int k=0;k<aux.length-1;++k)
                {
                    if(aux[k]!=0){
                        matrix[matrix.length-1][j]=Math.abs(aux[k]-aux[k+1]);
                        break;
                    }
                }

            }
        }
        return matrix;
    }

    private int[][] computePenalties(int [][]costMatrix){
        int [][] costMatrixWithPenalties=new int[costMatrix.length+1][costMatrix[0].length+1];
        for(int i=0;i<costMatrix.length;++i)
            for(int j=0;j<costMatrix[0].length;++j)
                costMatrixWithPenalties[i][j]=costMatrix[i][j];

        costMatrixWithPenalties=computeLinePenalties(costMatrixWithPenalties);
        costMatrixWithPenalties=computeColumnPenalties(costMatrixWithPenalties);
        return costMatrixWithPenalties;
    }

    private int findMaxPenalty(int[][]matrix){
        int max=matrix[0][matrix[0].length-1];
        for(int i=0;i<matrix.length;++i)
        {
            if(matrix[i][matrix[0].length-1]>max)
                max=matrix[i][matrix[0].length-1];
        }
        for(int i=0;i<matrix[0].length;++i)
        {
            if(matrix[matrix.length-1][i]>max)
                max=matrix[matrix.length-1][i];
        }
        return max;
    }

    private int[][] transferUnits(int[][]matrix,int max){
        for(int i=0;i<matrix.length-2;++i)
        {
            if(matrix[i][matrix[0].length-1]==max && matrix[i][matrix[0].length-2]!=0)
            {
                int minCost=2147483647;
                int position=0;
                for(int j=0;j<matrix[i].length-2;++j)
                {
                    if(matrix[i][j]<minCost && matrix[i][j]!=0)
                    {
                        minCost=matrix[i][j];
                        position=j;
                    }
                }
                int transferValue=Math.min(matrix[i][matrix[i].length-2],matrix[matrix.length-2][position]);
                unitsMatrix[i][position]=transferValue;
                matrix[i][matrix[i].length-2]-=transferValue;
                matrix[matrix.length-2][position]-=transferValue;
                return matrix;
            }
        }
        for(int j=0;j<matrix[0].length-2;++j)
        {
            if(matrix[matrix.length-1][j]==max && matrix[matrix.length-2][j]!=0)
            {
                int minCost=2147483647;
                int position=0;
                for(int i=0;i<matrix.length-2;++i)
                {
                    if(matrix[i][j]<minCost && matrix[i][j]!=0)
                    {
                        minCost=matrix[i][j];
                        position=i;
                    }
                }
                int transferValue=Math.min(matrix[matrix.length-2][j],matrix[position][matrix[0].length-2]);
                unitsMatrix[position][j]=transferValue;
                matrix[matrix.length-2][j]-=transferValue;
                matrix[position][matrix[0].length-2]-=transferValue;
                toBeTransferred-=transferValue;
            }
        }
        return matrix;
    }

    private int[][] shrinkMatrix(int[][]matrix){
        for(int i=0;i<matrix.length-2;++i)
            if(matrix[i][matrix[i].length-2]==0)
            {
                for(int j=0;j<matrix[i].length;++j)
                    matrix[i][j]=0;
            }
        for(int j=0;j<matrix[0].length-2;++j)
        {
            if(matrix[matrix.length-2][j]==0)
            {
                for(int i=0;i<matrix.length;++i)
                    matrix[i][j]=0;
            }
        }
        int [][]reducedMatrix=new int[matrix.length-1][matrix[0].length-1];
        for(int i=0;i<matrix.length-1;++i)
            for(int j=0;j<matrix[0].length-1;++j)
                reducedMatrix[i][j]=matrix[i][j];
        return reducedMatrix;
    }

    public int solveProblemVogel(Problem pr){
        if (checkSolutionExistence(pr) != null) return -1;
        int [][]costMatrix=pr.costMatrix;
        Vogel(costMatrix);
        printSolution();
        return calculateTotalCost(pr);
    }

    private int reducible(int[][]matrix){
        int sum=0;
        for(int i=0;i<matrix.length-1;++i){
            if(matrix[i][matrix[i].length-1]!=0)
                sum++;
        }
        if(sum==1)
            return 0;
        sum=0;
        for(int j=0;j<matrix[0].length-1;++j){
            if(matrix[matrix.length-1][j]!=0)
                sum++;
        }
        if(sum==1)
            return 0;
        return 1;
    }

    private void Vogel(int[][] costMatrix) {
        this.unitsMatrix = new int[unitsMatrix.length][unitsMatrix[0].length];
        int iterator=0;
        while(reducible(costMatrix)==1) {
            if(iterator%100==0)
                System.out.println(toBeTransferred);
            ++iterator;
            costMatrix = computePenalties(costMatrix);
            int max = findMaxPenalty(costMatrix);
            costMatrix = transferUnits(costMatrix, max);
            costMatrix = shrinkMatrix(costMatrix);
        }
        for(int i=0;i<costMatrix.length-1;++i)
            for(int j=0;j<costMatrix[0].length-1;++j)
            {
                if(costMatrix[i][j]!=0)
                {
                    int transferValue=Math.min(costMatrix[costMatrix.length-1][j],costMatrix[i][costMatrix[i].length-1]);
                    unitsMatrix[i][j]=transferValue;
                    costMatrix[costMatrix.length-1][j]-=transferValue;
                    costMatrix[i][costMatrix[i].length-1]-=transferValue;
                    costMatrix[i][j]=0;
                }
            }
    }

    /**
     * Function that calculates the total cost of transfer
     * @return the cost of transfer
     */
    private int calculateTotalCost(Problem pr){
        totalCost=0;
        for(int i = 0; i< sourceValues.length; ++i){
            for(int j = 0; j< destinationValues.length; ++j){
                totalCost+=unitsMatrix[i][j]*pr.costMatrix[i][j];
            }
        }
        return totalCost;
    }

    /**
     * Print a matrix where matrix[i][j] shows the number of units transferred from source i to destinationj
     */
    private void printSolution() {
        System.out.println("unitsMatrix:");
        for(int i = 0; i< sourceValues.length; ++i){
            for(int j = 0; j< destinationValues.length; ++j){
                System.out.printf("%d ",unitsMatrix[i][j]);
            }
            System.out.printf("\n");
        }
    }

    /**
     * Function that checks the validity of a probleminstance
     * @param pr the problem instance
     * @return -1 if the problem doesn't have a soluton null otherwise
     */
    private Integer checkSolutionExistence(Problem pr) {
        int sourceSum=0,destinationSum=0;
        for(int i = 0; i< pr.getSourceSize(); ++i){
            sourceValues[i]= pr.getSourceElement(i).getSupply();
            sourceSum+=sourceValues[i];
        }
        for(int i = 0; i< pr.getDestinationsSize(); ++i)
        {
            destinationValues[i]= pr.getDestinationElement(i).getDemand();
            destinationSum+=destinationValues[i];
        }
        if(sourceSum!=destinationSum)
            return -1;
        toBeTransferred=destinationSum;
        return null;
    }
}