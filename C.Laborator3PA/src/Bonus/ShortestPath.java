package Bonus;

import java.util.ArrayList;
import java.util.List;

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

    void addParents(int[]parents,int source,int current,int position,int initial,int[][]path){
        if (parents[current] == -1) {
            path[initial][position]=source;
            return;
        }
        ++position;
        addParents(parents, source, parents[current],position,initial,path);
        path[initial][position-1]=current;
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
    int dijkstra(int[][] graph, int source, int destination, TravelPlan t,int noprint) {
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
        if(distance[destination]!=Integer.MAX_VALUE){
            if(noprint==0) {
                System.out.printf("Shortest path from %d to %d: %d\n", source, destination, distance[destination]);
                System.out.println("Path:");
                printParents(parents, source, destination);
                System.out.println("\n\n");
            }
        }
        return distance[destination];
    }


    void dijkstra(int[][] graph, int source, int destination, TravelPlan t,int[][]paths) {
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
        if(distance[destination]!=Integer.MAX_VALUE){
            System.out.printf("Shortest path from %d to %d: %d\n", source, destination, distance[destination]);
            System.out.println("Path:");
            printParents(parents, source, destination);
            addParents(parents,source,destination,0,source,paths);
            reversePaths(paths,source);
            System.out.println("\n\n");
        }
    }


    void fromHotelDijkstra(int[][] graph, int source, TravelPlan t,int[][]paths) {
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

        }
        for(int i=0;i<graph.length;++i) {
            if(i!=source) {
                if(distance[i]!=Integer.MAX_VALUE){
                System.out.printf("Shortest path from %d to %d: %d\n", source, i, distance[i]);
                System.out.println("Path:");
                printParents(parents, source, i);
                addParents(parents,source,i,0,i,paths);
                reversePaths(paths,i);
                System.out.println("\n\n");
                }
            }
        }
    }

    void toHotelDijkstra(int[][] graph,int destination, TravelPlan t,int [][]paths){
        for(int i=0;i<graph.length;++i){
            dijkstra(graph,i,destination,t,paths);
        }
    }

    void reversePaths(int [][]paths,int position){
        List<Integer> aux=new ArrayList<>();
        for(int i=0;i<paths[position].length;++i){
            if(paths[position][i]==-1){
                for(int j=0;j<aux.size();++j)
                    paths[position][j]=aux.get(aux.size()-j-1);
                return;
            }
            aux.add(paths[position][i]);
        }
    }
}
