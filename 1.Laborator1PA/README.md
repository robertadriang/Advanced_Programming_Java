# Laborator1PA
--------------------------------------------------------------------------------------------
The requirements for this lab can be found here: https://profs.info.uaic.ro/~acf/java/labs/lab_01.html

The tasks can be split in 3 categories: Compulsory, Optional and Bonus.
--------------------------------------------------------------------------------------------
### Compulsory task:
--------------------------------------------------------------------------------------------
The Compulsory task is split into two files: 
+HelloWorld.java implementing the basic print of the "Hello World!" message.
+TaskB.java implementing basic operations with integer numbers and Array allocation/accesing

### Optional task:
--------------------------------------------------------------------------------------------
This task can be found in the TaskC.java file. The program takes as a parameter from the command line an odd integer (n) creating an n x n matrix, representing the adjacency matrix of a random graph. If the generated graph is connected then we create a spanning tree of the graph. Otherwise we are printing the components that sum the graph. The running time of the application  is also tracked and displayed in nanoseconds.  

*In order to run the program with a relatively big n the stack size must be increased by adding the -Xss{size}{unitofmeasure} parameter at execution (e.g. -Xss1G increases the stack-size to 1GB).

Key concepts: 
+ Argument validation using try{}catch{}
+ DFS traversing of a graph
+ Matrix traversing
+ Time tracking with System.nanoTime()

### Bonus task: 
--------------------------------------------------------------------------------------------
This task is implemented in the Bonus.java file. The program recursively generates a random tree with its adjacency matrix and displays it in a formatted way. 
In order to avoid breaking the stack memory by multiple recurssion calls a fixed number of children nodes was set for each parent node and a maximum depth size was fixed (otherwise in theory the program could generate an infinity of grandgrandgrand...children for a node. 

Because we are unable to know the number of vertexes before generating the whole tree the partial subtrees are saved in a matrix of 2^n width/height (with size doubling everytime the matrix is full). After the tree is generated we know the number of trees and we can shrink the matrix size down to the number of vertexes width/height. 

Key terms:
+ Matrix memory realocation.


