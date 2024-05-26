import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, Boolean> visited;
    private Map<V, V> pathToSource;
    private final V startVertex;

    public BreadthFirstSearch(MyGraph<V> graph, V startVertex) {
        visited = new HashMap<>();
        pathToSource = new HashMap<>();
        this.startVertex = startVertex;
        performBFS(graph, startVertex);
    }

    private void performBFS(MyGraph<V> graph, V startVertex) {
        Queue<V> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.put(startVertex, true);

        while (!queue.isEmpty()) {
            V currentVertex = queue.poll();
            for (V neighbor : graph.getAdjacencyList(currentVertex)) {
                if (!visited.containsKey(neighbor)) {
                    queue.add(neighbor);
                    visited.put(neighbor, true);
                    pathToSource.put(neighbor, currentVertex);
                }
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
