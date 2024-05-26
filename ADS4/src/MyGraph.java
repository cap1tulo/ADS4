import java.util.*;

public class MyGraph<V> {
    private Map<V, List<V>> adjacencyList;
    private boolean isDirected;

    public MyGraph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(V from, V to) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());
        adjacencyList.get(from).add(to);
        if (!isDirected) {
            adjacencyList.get(to).add(from);
        }
    }

    public List<V> getAdjacencyList(V vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }
}
