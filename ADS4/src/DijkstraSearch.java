import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distanceTo;
    private Map<V, V> pathToSource;
    private PriorityQueue<V> priorityQueue;
    private Map<V, Vertex<V>> vertices;

    public DijkstraSearch(WeightedGraph<V> graph, V startVertex) {
        distanceTo = new HashMap<>();
        pathToSource = new HashMap<>();
        vertices = graph.getVertices();
        priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distanceTo::get));

        // Initialize distanceTo map
        for (V vertex : vertices.keySet()) {
            distanceTo.put(vertex, Double.POSITIVE_INFINITY);
        }
        distanceTo.put(startVertex, 0.0);

        priorityQueue.add(startVertex);

        while (!priorityQueue.isEmpty()) {
            V currentVertex = priorityQueue.poll();
            for (Map.Entry<Vertex<V>, Double> entry : vertices.get(currentVertex).getAdjacentVertices().entrySet()) {
                V neighbor = entry.getKey().getData();
                relax(currentVertex, neighbor, entry.getValue());
            }
        }
    }

    private void relax(V u, V v, double weight) {
        double newDist = distanceTo.get(u) + weight;
        if (newDist < distanceTo.get(v)) {
            distanceTo.put(v, newDist);
            pathToSource.put(v, u);
            priorityQueue.add(v);
        }
    }

    @Override
    public boolean hasPathTo(V vertex) {
        return distanceTo.get(vertex) < Double.POSITIVE_INFINITY;
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
