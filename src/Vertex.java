import java.util.Map;

public class Vertex<V> {
    private V value;
    private Map<Vertex<V>, Double> neighbors;

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
