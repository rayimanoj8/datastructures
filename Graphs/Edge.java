package Graphs;

public class Edge implements Comparable<Edge>{
    int src;
    int dest;
    int wt;
    public Edge(int s,int d,int w){
        this.src = s;
        this.dest = d;
        this.wt = w;
    }
    public Edge(int s,int d){
        this.src = s;
        this.dest = d;
        this.wt = 0;
    }
    @Override
    public int compareTo(Edge e) {
        return this.wt - e.wt;
    }
    @Override
    public String toString(){
        return "["+src+", "+dest+", "+wt+"]";
    }
}