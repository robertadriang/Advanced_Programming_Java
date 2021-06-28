The requirements for this lab can be found here: https://profs.info.uaic.ro/~acf/java/labs/lab_02.html

Status: 

Compulsory - Completed 
The compulsory task models the Transportation Problem (https://www.mygreatlearning.com/blog/transportation-problem-explained/) in an OOP manner.
Each source has a name, a number of units that can supply and a type(the type is implemented as an enum)  . The source concept is implemented in the Source class ( With constructor/getters/setters AND toString overriden)
Each destination has a name and a demand ( a number of units that must be delivered to the destination). The destination concept is implemented in the Destination class ( With constructor/getters/setters AND toString overriden)

Each Transportation Problem can be modeled in the following way: 
-A number of sources 
-A number of destinations
-A cost matrix associated modelling a cost of transporting a unit from each source to each destination

The transportation problem is implemented in the Problem class (with Constructors/getters/setters/toString method overriden and a method of printing the object in a more readable format.

!Note: Some getters/setters were left in the code even if they are never used because they might be useful for the following tasks (Optional+Bonus). 

Optional -  Completed
-----------------------------------
The optional task requires to practice overriding methods/forming an abstract class, implementing a simple algorithm for solving the Transportation Problem and generating a class documentation using javadoc(https://docs.oracle.com/javase/9/javadoc/toc.html).
The algorithm chosen simply traverse the matrix and allocates for each source the first destination that is not already satisfied.

Bonus - Completed
-----------------------------------
The bonus requires implementing an algorithm for minimizing the total cost of transportation. 
The method chosen was Vogel's approximation method:  (https://www.youtube.com/watch?v=HVHPBAKTZWw&t=1s) (https://www.geeksforgeeks.org/transportation-problem-set-4-vogels-approximation-method/). 


Name - Gaina Robert-Adrian (2E4) 
