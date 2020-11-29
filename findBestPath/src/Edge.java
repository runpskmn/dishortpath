public class Edge {
    private int weight;
    private Vertex A;
    private Vertex B;

    public Edge(int weight, Vertex A, Vertex B) {
        this.weight = weight;
        this.A = A;
        this.B = B;
    }
    
    public Vertex getOutVertex(){
        return A;
    }
    
    public Vertex getInVertex(){
        return B;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public void setOutVertex(Vertex v){
        A = v;
    }
    
    public void setInVertex(Vertex v){
        B = v;
    }
    
    public Vertex getNeighbourVertex(Vertex v){
        if(v == A)
            return B;
        else
            return A;
    }
}
