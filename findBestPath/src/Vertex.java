public class Vertex {
    private String name = "";
    private boolean visited;
    private int dist = Integer.MAX_VALUE;
    private Vertex previous;

    public Vertex(String name) {
        this.name = name;
        visited = false;
        previous = null;
    }
    
    public void setVisited(boolean value){
        visited = value;
    }
    
    public void setDist(int dist){
        this.dist = dist;
    }
    
    public boolean isVisited(){
        return visited;
    }
    
    public int getDist(){
        return dist;
    }
    
    public String getName(){
        return name;
    }
    
    public void setPrevious(Vertex v){
        previous = v;
    }
    
    public Vertex getPrevious(){
        return previous;
    }
    
    public boolean isEmptyPrevious(){
        return previous == null;
    }
}
