
import java.util.ArrayList;

public class Graph {
    private ArrayList<Edge> Edges;
    private ArrayList<Vertex> Vertexs;
    private String path;
    private int shortpath;
    
    public Graph() {
        Edges = new ArrayList<>();
        Vertexs = new ArrayList<>();
        path = "";
        shortpath = 0;
    }
    
    public void addVertex(String name) {
        Vertexs.add(new Vertex(name));
    }

    public void addEdge(int weight, String nameA, String nameB) {
        Vertex A = getVertex(nameA);
        Vertex B = getVertex(nameB);
        Edges.add(new Edge(weight, A, B));
    }
    
    public void editEdge(String nameA, String nameB,int newWeight,String newA,String newB){
        for(int i=0;i<Edges.size();++i){
            if(Edges.get(i).getOutVertex().getName().equals(nameA) && Edges.get(i).getInVertex().getName().equals(nameB)){
                Edges.get(i).setWeight(newWeight);
                Edges.get(i).setOutVertex(getVertex(newA));
                Edges.get(i).setInVertex(getVertex(newB));
                break;
            }
        }
    }
    
    public Vertex getVertex(String name){
        Vertex v = null;
        for (Vertex vs : Vertexs) {
            if (vs.getName().equals(name)) {
                v = vs;
                break;
            }
        }
        return v;
    }
    
    public boolean isRepeatVertex(String name){
        boolean repeat = false;
        for(Vertex v:Vertexs){
            if(v.getName().equals(name)){
                repeat = true;
                break;
            }
        }
        return repeat;
    }
    
    public boolean isRepeatEdge(String nameA,String nameB){
        Vertex A = getVertex(nameA);
        Vertex B = getVertex(nameB);
        boolean repeat = false;
        for(Edge e : Edges){
            if((e.getInVertex() == A && e.getOutVertex() == B) || (e.getInVertex() == B && e.getOutVertex() == A)){
                repeat = true;
            }
        }
        return repeat;
    }
    
    public void findShortestPath(String vName1,String vName2){
        shortestPath stp = new shortestPath(Edges,Vertexs);
        int src = 0;
        int stop = 0;
        for(int i=0;i<Vertexs.size();++i){
            if(Vertexs.get(i).getName().equals(vName1)){
                src = i;
                break;
            }
        }
        for(int i=0;i<Vertexs.size();++i){
            if(Vertexs.get(i).getName().equals(vName2)){
                stop = i;
                break;
            }
        }
        stp.Dijkstra(src);
        stp.printResult(stop);
        shortpath = Vertexs.get(stop).getDist();
        path = stp.getPath(Vertexs.get(stop));
        for(Vertex v:Vertexs){
            v.setDist(Integer.MAX_VALUE);
            v.setVisited(false);
            v.setPrevious(null);
        }
    }
    
    public String getPath(){
        return path;
    }
    
    public int getShortPath(){
        return shortpath;
    }

}

class shortestPath{
    private ArrayList<Edge> Edges;
    private ArrayList<Vertex> Vertexs;
    private int n;
    public shortestPath(ArrayList<Edge> Edges,ArrayList<Vertex> Vertexs){
        this.Edges = Edges;
        this.Vertexs = Vertexs;
        n = Vertexs.size();
    }
    
    public void Dijkstra(int src){
       Vertexs.get(src).setDist(0);
       Vertex currentVertex = Vertexs.get(src);
       for(int i=0;i<n;++i){
           ArrayList<Edge> e  = new ArrayList<>();
           for(Edge ed:Edges){
               if(ed.getInVertex() == currentVertex || ed.getOutVertex() == currentVertex)
                   e.add(ed);
           }
           for(int j=0;j<e.size();++j){
               Vertex neighborV = e.get(j).getNeighbourVertex(currentVertex);
               
               if(!currentVertex.isVisited()){
                   int tentative = currentVertex.getDist() + e.get(j).getWeight();
                   if(tentative < neighborV.getDist()){
                        neighborV.setDist(tentative);
                        neighborV.setPrevious(currentVertex);
                   }
               }
           }
           currentVertex.setVisited(true);
           currentVertex = getMinDist();
       }
    }
    public String getPath(Vertex stop){
        if(stop.isEmptyPrevious())
            return stop.getName();
        return getPath(stop.getPrevious())+" => "+stop.getName();
    }
    
    private Vertex getMinDist(){
       Vertex v = Vertexs.get(0);
       int Dist = Integer.MAX_VALUE;
       
       for(int i=0;i<n;++i){
           Vertex currentVertex = Vertexs.get(i);
           int currentDist = currentVertex.getDist();
           
           if(!Vertexs.get(i).isVisited() && currentDist < Dist){
               v = currentVertex;
               Dist = currentDist;
           }
       }
       return v;
    }
    
    public void printResult(int stop){
        System.out.println("Shortest Path is : "+Vertexs.get(stop).getDist());
        System.out.println(getPath(Vertexs.get(stop)));
    }
    
}
