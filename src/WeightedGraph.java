import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private final boolean directed;
    private final Map<V, Vertex<V>> vertices;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }
    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V sourceData, V destData, double weight) {
        addVertex(sourceData);
        addVertex(destData);
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);
        source.addNeighbor(dest, weight);
        if (!directed) {
            dest.addNeighbor(source, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }

    public static class Vertex<V> {
        private final V value;
        private final Map<Vertex<V>, Double> neighbors;

        public Vertex(V value) {
            this.value = value;
            this.neighbors = new HashMap<>();
        }

        public V getValue() {
            return value;
        }

        public void addNeighbor(Vertex<V> neighbor, double weight) {
            neighbors.put(neighbor, weight);
        }

        public Map<Vertex<V>, Double> getNeighbors() {
            return neighbors;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
