import java.util.NoSuchElementException;

public class BetterDiGraph implements EditableDiGraph {
    @Override
    public boolean containsVertex(int v) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public Iterable<Integer> getAdj(int v) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public int getEdgeCount() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getIndegree(int v) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getVertexCount() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public void removeEdge(int v, int w) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void removeVertex(int v) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public Iterable<Integer> vertices() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void addEdge(int v, int w) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void addVertex(int v) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }
}
