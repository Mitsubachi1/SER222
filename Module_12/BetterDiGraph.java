import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class BetterDiGraph implements EditableDiGraph {
    private Map<Integer, Set<Integer>> adjacencyList;
    private int edgeCount;
    
    public BetterDiGraph() {
        this.adjacencyList = new HashMap<>();
        this.edgeCount = 0;
    }

    @Override
    public boolean containsVertex(int v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public Iterable<Integer> getAdj(int v) {
        if (!containsVertex(v)) {
            throw new NoSuchElementException("Vertex not found: " + v);
        }
        return adjacencyList.get(v);
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public int getIndegree(int v) throws NoSuchElementException {
        if (!containsVertex(v)) {
            throw new NoSuchElementException("Vertex not found: " + v);
        }

        int indegree = 0;
        for (Set<Integer> neighbors : adjacencyList.values()) {
            if (neighbors.contains(v)) {
                indegree++;
            }
        }

        return indegree;
    }

    @Override
    public int getVertexCount() {
        return adjacencyList.size();
    }

    @Override
    public void removeEdge(int v, int w) {
        if (containsVertex(v) && containsVertex(w)) {
            Set<Integer> neighbors = adjacencyList.get(v);
            if (neighbors != null) {
                neighbors.remove(w);
                edgeCount--;
            }
        }
    }

    @Override
    public void removeVertex(int v) {
        if (containsVertex(v)) {
            // Remove incoming edges
            for (Set<Integer> neighbors : adjacencyList.values()) {
                neighbors.remove(v);
            }

            // Remove outgoing edges
            adjacencyList.remove(v);
        }
    }

    @Override
    public Iterable<Integer> vertices() {
        return adjacencyList.keySet();
    }

    @Override
    public void addEdge(int v, int w) {
        if (!containsVertex(v)) {
            addVertex(v);
        }
        if (!containsVertex(w)) {
            addVertex(w);
        }

        adjacencyList.computeIfAbsent(v, k -> new HashSet<>()).add(w);
        edgeCount++;
    }

    @Override
    public void addVertex(int v) {
        adjacencyList.putIfAbsent(v, new HashSet<>());
    }

    @Override
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }
}
