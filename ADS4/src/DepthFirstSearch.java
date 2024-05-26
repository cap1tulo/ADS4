import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Map<V, Boolean> visited;
    private Map<V, V> pathToSource;
    private final V startVertex;

    public DepthFirstSearch(MyGraph<V> graph, V startVertex) {
        visited = new HashMap<>();
        pathToSource = new HashMap<>();
        this.startVertex = startVertex;
        executeDFS(graph, startVertex);
    }

    private void executeDFS(MyGraph<V> graph, V vertex) {
        visited.put(vertex, true);
        for (V neighbor : graph.getAdjacencyList(vertex)) {
            if (!visited.containsKey(neighbor)) {
                pathToSource.put(neighbor, vertex);
                executeDFS(graph, neighbor);
            }
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return visited.containsKey(vertex);
    }

    @Override
    public List<V> pathTo(V vertex) {
        if (!hasPathTo(vertex)) return null;
        List<V> path = new LinkedList<>();
        for (V current = vertex; current != null; current = pathToSource.get(current)) {
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }
}
