import java.util.*;

public class Graph {
    static class Edge {
        String start;
        String end;
        int weight;

        Edge(String start, String end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static class Graph1 {
        int vertices;
        LinkedList<Edge>[] adj;

        int paths;

        int Distance = 0;

        Graph1(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addingEdge(String start, String end, int weight) {
            Edge e1 = new Edge(start, end, weight);
            Edge e2 = new Edge(end, start, weight);
            int index1 = (int) (start.charAt(0)) - 65;
            int index2 = (int) (end.charAt(0)) - 65;
            adj[index1].addFirst(e1);
            adj[index2].addFirst(e2);
        }

        public int calculateAverageDistanceBetweenTwoPoints(String x, String y) {

            // TODO Add your implementation here
            boolean visited[] = new boolean[vertices];
            for (int i = 0; i < vertices; i++)
                visited[i] = false;
            paths = 0;
            Distance = 0;
            visited[(int) (x.charAt(0)) - 65] = true;
            calculate(x, y, visited, 0);
            return (Distance / paths);
        }

        public void calculate(String a, String b, boolean visited[], int dt) {
            if (a.equals(b)) {
                paths++;
                Distance += dt;
                return;
            }
            int index = (int) (a.charAt(0)) - 65;
            for (int i = 0; i < adj[index].size(); i++) {
                String to = adj[index].get(i).end;
                int indexOfDestination = (int) (to.charAt(0)) - 65;
                if (visited[indexOfDestination] == false) {
                    visited[indexOfDestination] = true;
                    calculate(to, b, visited, dt + adj[index].get(i).weight);
                    visited[indexOfDestination] = false;
                }
            }
            return;
        }
    }

    public static void main(String[] args) {
        Graph1 graph = new Graph1(5);
        graph.addingEdge("A", "B", 12);
        graph.addingEdge("A", "C", 13);
        graph.addingEdge("A", "D", 11);
        graph.addingEdge("A", "E", 8);
        graph.addingEdge("C", "B", 3);
        graph.addingEdge("E", "C", 4);
        graph.addingEdge("D", "E", 7);

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the source node");
        String source = input.next();
        System.out.println("Enter the destination node");
        String destination = input.next();
        double average_distance = graph.calculateAverageDistanceBetweenTwoPoints(source.toUpperCase(), destination.toUpperCase());
        System.out.println("The average distance required to travel between the two given points " + source.toUpperCase() + " and "
                + destination.toUpperCase() + " is " + average_distance);

        System.out.println("The average distance required to travel between the two given points A and B is "
                + graph.calculateAverageDistanceBetweenTwoPoints("A", "B"));
    }

}
