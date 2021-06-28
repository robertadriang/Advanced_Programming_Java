# Laborator3PA

The requirements for this lab can be found here: https://profs.info.uaic.ro/~acf/java/labs/lab_03.html

The tasks can be split in 3 categories: Compulsory, Optional and Bonus.

Compulsory task: (Completed)
----------------------------------------------------------
The compulsory task models the input data for the Tourist Trip Planning Problem. A city contains a list of locations that are of different types. Some of them are visitable, classifiable or an entry fee is required.
For each location in the city there is a map representing the time required to go from that location to others. 

Optional task: (Completed)
----------------------------------------------------------
The optional sections finds the shortes path between two given locations conforming to the tourist prefferences. It uses a modified version of dijkstra algorithm that stops when the destination is visited and if two paths of the same cost exists the one that respects the ordering of the prefferences is chosen.

Bonus task: (Completed)
-------------------------------------------
The bonus section composes all the shortest paths from the source to every other node and from every other node back to the source using the algorithm from the optional task
Then, we iterate through all the paths and and pick the cycle hotel-point-hotel that visits the most unvisited interest points.
