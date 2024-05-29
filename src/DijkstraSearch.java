import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<Vertex<V>, Vertex<V>> edgeTo;
    private final Map<Vertex<V>, Double> distTo;
    private final PriorityQueue<Vertex<V>> pq;

    public DijkstraSearch(WeightedGraph<V> graph, V sourceData) {
        this.edgeTo = new HashMap<>();
        this.distTo = new HashMap<>();
        this.pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        Vertex<V> source = graph.getVertex(sourceData);
        for (Vertex<V> vertex : graph.getAllVertices()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);
        pq.offer(source);
        performDijkstra(graph);
    }

    private void performDijkstra(WeightedGraph<V> graph) {
        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            for (Map.Entry<Vertex<V>, Double> neighborEntry : current.getNeighbors().entrySet()) {
                Vertex<V> neighbor = neighborEntry.getKey();
                double weight = neighborEntry.getValue();
                double newDist = distTo.get(current) + weight;
                if (newDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current);
                    pq.offer(neighbor);
                }
            }
        }
    }

    @Override
    public Iterable<V> pathTo(V destinationData) {
        List<V> path = new ArrayList<>();
        Vertex<V> destination = edgeTo.keySet().stream()
                .filter(v -> v.getValue().equals(destinationData))
                .findFirst().orElse(null);
        if (destination == null || distTo.get(destination) == Double.POSITIVE_INFINITY) return path;
        for (Vertex<V> vertex = destination; vertex != null; vertex = edgeTo.get(vertex)) {
            path.add(vertex.getValue());
        }
        Collections.reverse(path);
        return path;
    }
}
