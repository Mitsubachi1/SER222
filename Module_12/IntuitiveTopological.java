import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

public class IntuitiveTopological implements TopologicalSort{
    private BetterDiGraph graph;
    private Map<Integer, Integer> indegreeMap;

    public IntuitiveTopological(BetterDiGraph graph) {
        this.graph = graph;
        this.indegreeMap = new HashMap<>();
        calculateIndegrees();
    }

    private void calculateIndegrees() {
        for (int vertex : graph.vertices()) {
            indegreeMap.put(vertex, graph.getIndegree(vertex));
        }
    }

    @Override
    public boolean isDAG() {
        // Check if the graph has a cycle (not a Directed Acyclic Graph)
        // You need to implement this method based on your graph representation.
        // Hint: You can use depth-first search (DFS) for cycle detection.
        return !hasCycle();
    }
    private boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> currentlyVisiting = new HashSet<>();

        for (int vertex : graph.vertices()) {
            if (!visited.contains(vertex)) {
                if (dfsHasCycle(vertex, visited, currentlyVisiting)) {
                    return true; // Cycle found
                }
            }
        }

        return false; // No cycle found
    }

    private boolean dfsHasCycle(int currentVertex, Set<Integer> visited, Set<Integer> currentlyVisiting) {
        visited.add(currentVertex);
        currentlyVisiting.add(currentVertex);

        for (int neighbor : graph.getAdj(currentVertex)) {
            if (!visited.contains(neighbor)) {
                if (dfsHasCycle(neighbor, visited, currentlyVisiting)) {
                    return true; // Cycle found in the DFS
                }
            } else if (currentlyVisiting.contains(neighbor)) {
                return true; // Back edge found, indicating a cycle
            }
        }

        currentlyVisiting.remove(currentVertex);
        return false; // No cycle found in this DFS branch
    }

    @Override
    public Iterable<Integer> order() {
        if (!isDAG()) {
            throw new IllegalStateException("The graph has a cycle. Topological sort is not possible.");
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> topologicalOrder = new ArrayList<>();

        for (int vertex : graph.vertices()) {
            if (indegreeMap.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            topologicalOrder.add(currentVertex);

            for (int neighbor : graph.getAdj(currentVertex)) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                if (indegreeMap.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return topologicalOrder;
    }
}
