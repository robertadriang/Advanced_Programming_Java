package optional;

import java.util.*;

/* Class that solves the problem using the adaptation of Dijkstras algorithm
* https://www.freecodecamp.org/news/dijkstras-shortest-path-algorithm-visual-introduction/
The algorithm stops when the destination node is visited*/

public class ShortestPath {
    /* Return the position of the node at the shortest distance from the current conex component*/
    int minDistance(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < distance.length; ++i) {
            if (visited[i] == false && distance[i] <= min) {
                min = distance[i];
                index = i;
            }
        }
        return index;
    }

    /* Function for returning the path from source to destination */
    void printParents(int[] parents, int source, int current) {
        if (parents[current] == -1) {
            System.out.printf("%d ", source);
            return;
        }
        printParents(parents, source, parents[current]);
        System.out.printf("%d ", current);
    }

    /* Returning the visited nodes from source to destination*/
    List<Integer> path(int[] parents, List<Integer> ans, int source, int current) {
        if (parents[current] == -1) {
            ans.add(source);
            return ans;
        }
        ans = path(parents, ans, source, parents[current]);
        ans.add(current);
        return ans;
    }

    /* Check if the current path suits the preferences.
    A path is prefered if all the nodes traversed can be found in the same order in the prefference array  */
    boolean betterPreference(int[] parents, int source, int current, TravelPlan t) {
        List<Integer> ans = new ArrayList<Integer>();
        List<Integer> path = path(parents, ans, source, current);
        List<Integer> positions = new ArrayList<Integer>();
        for (int i = 0; i < path.size(); ++i) {
            int aux = t.getPrefferencePosition(path.get(i));
            if (aux != -1)
                positions.add(aux);
        }
        if (positions.size() > 1) {
            for (int i = 0; i < positions.size(); ++i) {
                if (positions.get(i) > positions.get(i + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Dijkstras algorithm
         https://www.freecodecamp.org/news/dijkstras-shortest-path-algorithm-visual-introduction/
       The algorithm stops when the destination node is visited*/
    void dijkstra(int[][] graph, int source, int destination, TravelPlan t) {
        int[] distance = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        int[] parents = new int[graph.length];
        parents[source] = -1;
        for (int i = 0; i < graph.length; ++i) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[source] = 0;

        for (int i = 0; i < graph.length - 1; ++i) {

            int next = minDistance(distance, visited);

            visited[next] = true;

            for (int j = 0; j < graph.length; ++j) {
                if (visited[j] == false && graph[next][j] != 0 && distance[next] != Integer.MAX_VALUE && (distance[next] + graph[next][j] < distance[j] || (distance[next] + graph[next][j] == distance[j] && betterPreference(parents, source, next, t) == true))) {
                    parents[j] = next;
                    distance[j] = distance[next] + graph[next][j];
                }
            }
            if (next == destination)
                break;
        }
        System.out.printf("Shortest path from %d to %d: %d\n", source, destination, distance[destination]);
        System.out.println("Path:");
        printParents(parents, source, destination);
    }
}
