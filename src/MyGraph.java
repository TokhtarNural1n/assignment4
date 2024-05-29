import java.util.*;

public class MyGraph<V> implements Graph<V> {
    private final boolean undirected;
    private final Map<V, List<V>> adjacencyMap = new HashMap<>();

    public MyGraph() {
        this(true);
    }

    public MyGraph(boolean undirected) {
        this.undirected = undirected;
    }

    @Override
    public void addVertex(V vertex) {
        if (!hasVertex(vertex)) {
            adjacencyMap.put(vertex, new LinkedList<>());
        }
    }

    @Override
    public void addEdge(V source, V destination) {
        if (!hasVertex(source)) {
            addVertex(source);
        }

        if (!hasVertex(destination)) {
            addVertex(destination);
        }

        if (hasEdge(source, destination) || source.equals(destination)) {
            return;
        }

        adjacencyMap.get(source).add(destination);

        if (undirected) {
            adjacencyMap.get(destination).add(source);
        }
    }

    public int getVertexCount() {
        return adjacencyMap.size();
    }

    public int getEdgeCount() {
        int edgeCount = 0;
        for (List<V> adjacentVertices : adjacencyMap.values()) {
            edgeCount += adjacentVertices.size();
        }

        if (undirected) {
            edgeCount /= 2;
        }

        return edgeCount;
    }

    public boolean hasVertex(V vertex) {
        return adjacencyMap.containsKey(vertex);
    }

    public boolean hasEdge(V source, V destination) {
        return hasVertex(source) && adjacencyMap.get(source).contains(destination);
    }

    @Override
    public List<V> adjacencyList(V vertex) {
        return hasVertex(vertex) ? adjacencyMap.get(vertex) : null;
    }
}
