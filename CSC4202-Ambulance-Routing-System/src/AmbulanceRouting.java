import java.util.*;

public class AmbulanceRouting {

    static class Node implements Comparable<Node> {
        int id;
        double dist;
        Node(int id, double dist) {
            this.id = id;
            this.dist = dist;
        }
        public int compareTo(Node other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    static class Edge {
        int to;
        double cost;
        Edge(int to, double cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        String[] names = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        addEdge(graph, 0, 2, 3.20);
        addEdge(graph, 0, 1, 2.50);
        addEdge(graph, 2, 4, 2.00);
        addEdge(graph, 2, 5, 0.70);
        addEdge(graph, 4, 7, 1.00);
        addEdge(graph, 5, 7, 2.00);
        addEdge(graph, 7, 8, 0.40);
        addEdge(graph, 7, 9, 0.65);
        addEdge(graph, 8, 6, 0.27);
        addEdge(graph, 8, 9, 0.35);
        addEdge(graph, 9, 6, 0.84);
        addEdge(graph, 6, 3, 0.90);
        addEdge(graph, 1, 3, 2.80);

        int source = 0;
        int destination = 9;

        double[] dist = new double[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(prev, -1);

        // Start the timer right before the algorithm runs
        long startTime = System.nanoTime();

        dist[source] = 0.0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (Edge e : graph.get(u)) {
                int v = e.to;
                double newDist = dist[u] + e.cost;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u;
                    pq.add(new Node(v, newDist));
                }
            }
        }

        // Stop the timer right after the algorithm finishes
        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;

        System.out.println("Shortest effective cost from A (HSAAS) to every node:");
        for (int i = 0; i < n; i++) {
            System.out.printf("  A -> %s : %.2f%n", names[i], dist[i]);
        }

        List<Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        StringBuilder route = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            route.append(names[path.get(i)]);
            if (i < path.size() - 1) {
                route.append(" -> ");
            }
        }

        System.out.println();
        System.out.println("Source node      : A (HSAAS)");
        System.out.println("Destination node : J (KOSASS B)");
        System.out.println("Optimal route    : " + route.toString());
        System.out.printf("Total cost       : %.2f%n", dist[destination]);
        System.out.println();
        System.out.printf("Time taken       : %d nanoseconds%n", elapsed);
        System.out.printf("                 : %.4f milliseconds%n", elapsed / 1_000_000.0);
    }

    static void addEdge(List<List<Edge>> graph, int u, int v, double cost) {
        graph.get(u).add(new Edge(v, cost));
        graph.get(v).add(new Edge(u, cost));
    }
}
