package Bonus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
In the City class, create a method to display the locations that are visitable and are not payable, sorted by their opening hour. -Done
Create default methods in the interface Visitable, with the opening hour 09:30 and the closing hour 20:00. -Done
Create a static method getVisitingDuration,in the interface Visitable, that returns a Duration object, representing how long a location is opened during a day. -Done
Create the class TravelPlan. An instance of this class will contain a city and the preferences regarding the visiting order.
Implement an efficient agorithm to determine the shortest path between two given locations, conforming to the preferences.
 */
public class Main {
    public static List<List<Integer>> computeAvailablePaths(int[][] p1, int[][] p2) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < p1.length; ++i) {
            int ok = 0;
            List<Integer> aux = new ArrayList<>();
            for (int j = 0; j < p1[0].length; ++j) {
                if (p1[i][j] != -1) {
                    aux.add(p1[i][j]);
                    if (ok == 0) {
                        ok = 1;
                    }
                } else break;
            }
            for (int j = 1; j < p2[0].length; ++j) {
                if (p2[i][j] != -1) {
                    aux.add(p2[i][j]);
                    if (ok == 1) {
                        ok = 2;
                    }
                } else break;
            }
            if (ok != 2) {
                aux.clear();
                aux.add(-1);
            }
            result.add(aux);
        }
        return result;
    }

    public static int[] computeBestRoute(List<List<Integer>> paths, int[] visited, City city, double startTime, double endTime, int[][] graph, TravelPlan t) {
        int bestVisited[] = new int[visited.length];
        int bestVisitedCount = 0;
        int bestPath=-1;
        for (int i = 0; i < paths.size(); ++i) {
            if (paths.get(i).get(0) != -1) {
                int currentVisited[] = new int[visited.length];
                int currentVisitedCount = 0;
                for (int j = 1; j < paths.get(i).size() - 1; ++j) {
                    int location = paths.get(i).get(j);
                    if (visited[location] != 1) {
                        var place = city.places.get(location);
                        if (place.isVisitable() == 0) {
                            currentVisitedCount++;
                            currentVisited[location] = 1;
                        } else {
                            ShortestPath r = new ShortestPath();
                            double time = ((int) startTime) * 60 + (startTime - (int) startTime) + r.dijkstra(graph, paths.get(i).get(0), location, t,1);
                            if (place.isOpen(time) == 1) {
                                currentVisitedCount++;
                                currentVisited[location] = 1;
                            }
                        }
                    }
                }
                if (currentVisitedCount > bestVisitedCount) {
                    bestVisitedCount = currentVisitedCount;
                    bestPath=i;
                    for (int k = 0; k < bestVisited.length; ++k) {
                        bestVisited[k] = currentVisited[k];
                    }
                }
            }
        }
        System.out.printf("Best path chosen: %d\n\n",bestPath);
        return bestVisited;
    }

    private static List<List<Integer>> getLists ( int[][] graph, TravelPlan trip, ShortestPath r){
        int[][] paths = new int[graph.length][graph.length];
        for (int i = 0; i < paths.length; ++i) {
            for (int j = 0; j < paths[0].length; ++j)
                paths[i][j] = -1;
        }
        r.fromHotelDijkstra(graph, 4, trip, paths);
        int[][] paths2 = new int[graph.length][graph.length];
        for (int i = 0; i < paths2.length; ++i) {
            for (int j = 0; j < paths2[0].length; ++j)
                paths2[i][j] = -1;
        }
        r.toHotelDijkstra(graph, 4, trip, paths2);
        List<List<Integer>> availableRoutes = computeAvailablePaths(paths, paths2);
        return availableRoutes;
    }

    public static void main(String[] args) {
        Location v1 = new Hotel("International", "Hotel International Iasi - decent", new Pair(15, 25), "4");
        Location v2 = new Museum("Muzeul Unirii", "Muzeul Unirii - Monument istoric și de arhitectură", new Pair(25, 20), 8, 16, 5, "Muzeu de istorie");
        Location v3 = new Museum("Palatul Culturii", "clădire emblematică în perimetrul fostei curți domnești medievale moldovenești", new Pair(102, 90), 10, 16, 50, "Muzeu de istorie");
        Location v4 = new Church("Catedrala Mitropolinata", "biserica catedrală a Mitropoliei Moldovei și Bucovinei", new Pair(99, 98), "Ortodoxa");
        Location v5 = new Church("Catedrala \"Sfânta Fecioară Maria, Regină\"", "catedrală episcopală", new Pair(99, 97), "Catolica");
        Location v6 = new Restaurant("Mamma mia", "Restaurant din centrul orasului.", new Pair(70, 75), 9, 23, "Pizzeria", "$$");
        Location v7 = new Restaurant("Dopo Poco", "Restaurant langa Moldova Mall", new Pair(50, 50), 10, 20, "FastFood", "$$");
        Location v8 = new Restaurant("Kiwan", "Restaurant Stefan", new Pair(50, 60), 12, 21, "FastFood", "$");
        Location v9 = new Restaurant("Alila", "Pizza prin Cantemir", new Pair(20, 15), "Pizzeria", "$");
        Location[] mustVisit = {v1, v2, v3, v4, v5, v6, v7, v8, v9};

        City Iasi = new City();

        for (int i = 0; i < mustVisit.length; ++i)
            Iasi.places.add(mustVisit[i]);

        Collections.sort(Iasi.places);
        System.out.println("Come visit Iasi");

        Iasi.addTripDuration(v1, v2, 10);
        Iasi.addTripDuration(v1, v3, 50);
        Iasi.addTripDuration(v2, v3, 20, 1);
        Iasi.addTripDuration(v2, v4, 20);
        Iasi.addTripDuration(v2, v5, 10);
        Iasi.addTripDuration(v3, v4, 20);
        Iasi.addTripDuration(v4, v5, 30, 1);
        Iasi.addTripDuration(v4, v6, 10);
        Iasi.addTripDuration(v5, v6, 20);

        System.out.println("we added the paths");

        Iasi.showVisitableNotPayable();
        System.out.println("we added the paths");

        List<Integer> tripPrefferences = new ArrayList<>();
        tripPrefferences.add(1);
        tripPrefferences.add(0);

        int[][] graph = new int[mustVisit.length][mustVisit.length];
        for (int i = 0; i < mustVisit.length; ++i)
            for (Map.Entry<String, Integer> entry : Iasi.places.get(i).relatedPlaces.entrySet()) {
                graph[i][Iasi.getPosition(entry.getKey())] = entry.getValue();
            }
        int path = 5;
        for (int i = 0; i < mustVisit.length; ++i) {
            graph[i][4] = path;
            path += 5;
        }
        TravelPlan trip = new TravelPlan(Iasi, tripPrefferences);

        ShortestPath r = new ShortestPath();
        r.dijkstra(graph, 4, 6, trip,0);

        int[] visited = new int[graph.length];
        int nr_of_days = 3;
        List<List<Integer>> availableRoutes = getLists(graph, trip, r);
        for (int day = 0; day < nr_of_days; ++day) {
            int changes = 0;
            int[] currentVisited = computeBestRoute(availableRoutes, visited, Iasi, 9, 20, graph, trip);
            System.out.printf("Day %d\n",day);
            for (int i = 0; i < visited.length; ++i) {
                if (currentVisited[i] == 1) {
                    visited[i] = 1;
                    System.out.printf("%d ",i);
                    changes++;
                }
            }
            System.out.println("\n\n\n");
            if (changes == 0) {
                System.out.println("Some places can't be visited!");
                break;
            } else {
                int allVisited = 1;
                for (int i = 0; i < visited.length; ++i) {
                    if (visited[i] == 0) {
                        allVisited = 0;
                        break;
                    }
                }
                if (allVisited == 1) {
                    System.out.println("All places were visited!");
                    break;
                }
            }
        }
    }
}
