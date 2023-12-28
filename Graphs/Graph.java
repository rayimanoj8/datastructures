package Graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Heaps.MinHeap;
import Queues.QueueL;
import Queues.Queue;
import Stacks.StackL;

public class Graph {
    int V;
    ArrayList<Edge> graph[];
    public Graph(int V) {
        this.V = V;
        graph = new ArrayList[V];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void createGraph() {
        add(6,7,1);
        add(2,8,2);
        add(5,6,2);
        add(1,0,4);
        add(5,2,4);
        add(6,8,6);
        add(3,2,7);
        add(8,7,7);
        add(7,0,8);
        add(2,1,8);
        add(4,3,9);
        add(4,5,10);
        add(7,1,11);
        add(5,3,14);
        add(7,6,1);
        add(8,2,2);
        add(6,5,2);
        add(0,1,4);
        add(2,5,4);
        add(8,6,6);
        add(2,3,7);
        add(7,8,7);
        add(0,7,8);
        add(1,2,8);
        add(3,4,9);
        add(5,4,10);
        add(1,7,11);
        add(3,5,14);
    }

    void add(int src, int dest, int wt) {
        graph[src].add(new Edge(src, dest, wt));
    }

    public void getNeighbours(int ind) {
        for (int i = 0; i < graph[ind].size(); i++) {
            Edge e = graph[ind].get(i);
            System.out.println(e.dest + " ");
        }
    }

    public void bfs() {
        /*
         * Time Complexity is O( V + E );
         * V = no.of Vertices;
         * E = no.of Edges;
         */
        QueueL<Integer> q = new QueueL<>();
        boolean vis[] = new boolean[V];
        for (int b = 0; b < V; b++) {
            if (vis[b] == false) {
                q.enque(b);
                while (!q.isEmpty()) {
                    int curr = q.deque();
                    if (vis[curr] == false) {
                        System.out.print(curr + " ");
                        for (int i = 0; i < graph[curr].size(); i++) {
                            Edge e = graph[curr].get(i);
                            q.enque(e.dest);
                        }
                        vis[curr] = true;
                    }
                }
            }
        }
    }

    public void dfs() {
        boolean b[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (b[i] == false) {
                dfs(i, b);
            }
        }
    }

    public void dfs(int curr, boolean vis[]) {
        /*
         * Time Complexity is O( V + E );
         * V = no.of Vertices;
         * E = no.of Edges;
         */
        System.out.print(curr + " ");
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (vis[e.dest] == false)
                dfs(e.dest, vis);
        }
    }

    public void printAllPath(String path, boolean vis[], int curr, int tar) {
        /*
         * Time Complexity is O(V*V)
         */
        if (curr == tar) {
            System.out.println(path);
            return;
        }
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                vis[e.dest] = true;
                printAllPath(path + e.dest, vis, e.dest, tar);
                vis[e.dest] = false;
            }
        }
    }

    public boolean isCycleDirected() {
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                if (isCycleDirected(vis, 0, new boolean[V]))
                    return true;
            }
        }
        return false;
    }

    boolean isCycleDirected(boolean vis[], int curr, boolean rec[]) {
        /*
         * Time Complexity is O( V + E )
         */
        vis[curr] = true;
        rec[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (rec[e.dest])// cycle exists
                return true;
            else if (!vis[e.dest]) {
                if (isCycleDirected(vis, e.dest, rec)) {
                    return true;
                }
            }
        }
        rec[curr] = false;
        return false;
    }

    public void topologincalSort() throws Exception {
        if (isCycleDirected())
            throw new Exception("Cycle detected");
        /*
         * Time Complexity O( V + E )
         */
        boolean vis[] = new boolean[V];
        StackL<Integer> stack = new StackL<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topologincalSort(vis, i, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public StackL<Integer> topologincalSort(boolean return_) {
        /*
         * Time Complexity O( V + E )
         * 
         * @warning Topological sort Does not Work with the Cycled graphs
         */
        boolean vis[] = new boolean[V];
        StackL<Integer> stack = new StackL<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topologincalSort(vis, i, stack);
            }
        }
        return stack;
    }

    public void topologincalSort(boolean vis[], int curr, StackL<Integer> stack) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest])
                topologincalSort(vis, e.dest, stack);
        }
        stack.push(curr);
    }

    public boolean isCycleUndirected() {
        /*
         * Time Complexity O( V + E )
         */
        boolean vis[] = new boolean[V];
        return isCycleUndirected(vis, 0, -1);
    }

    boolean isCycleUndirected(boolean vis[], int curr, int par) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (vis[e.dest] && par != e.dest)
                return true;
            else if (!vis[e.dest] && isCycleUndirected(vis, e.dest, curr))
                return true;
        }
        return false;
    }

    class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int n, int d) {
            this.node = n;
            this.dist = d;
        }

        @Override
        public String toString() {
            return "Pair(" + node + ", " + dist + ")";
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    public void dijkstra(int src) {
        Queue<Pair> pq = new MinHeap<>();
        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (src != i)
                dist[i] = Integer.MAX_VALUE;
        }
        boolean vis[] = new boolean[V];
        pq.enque(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair curr = pq.deque();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    if (dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.enque(new Pair(v, dist[v]));
                    }
                }
            }
        }
        for (int i : dist)
            System.out.print(i + " ");
    }

    public void bellmanFord(int src) {
        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (src != i)
                dist[i] = Integer.MAX_VALUE;
        }
        for (int k = 0; k < V - 1; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < graph[i].size(); j++) {
                    Edge e = graph[i].get(j);
                    int u = e.src;
                    int v = e.dest;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                int u = e.src;
                int v = e.dest;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + e.wt < dist[v]) {
                    System.out.println("negative weight cycle detected");
                }
            }
        }
        for (int i : dist)
            System.out.print(i + " ");
    }

    public void prims() {
        Queue<Pair> pq = new MinHeap<>();
        boolean vis[] = new boolean[V];
        pq.enque(new Pair(0, 0));
        int minCost = 0;
        while (!pq.isEmpty()) {
            Pair curr = pq.deque();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                minCost += curr.dist;
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    if (!vis[e.dest]) {
                        pq.enque(new Pair(e.dest, e.wt));
                    }
                }
            }
        }
        System.out.println("Minimum Cost Of MST --> " + minCost);
    }

    public void kosarajuAlgo() {
        /*
         * step - 1 :- get stack of topsort
         * step - 2 :- transpose the graph
         * step - 3 :- perform dfs
         */
        // Step1
        StackL<Integer> stack = this.topologincalSort(true);
        // Step2
        Graph g2 = new Graph(V);
        // ArrayList<Edge> transpose[] = new ArrayList[V];
        // for(int i=0;i<V;i++)
        // transpose[i] = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                g2.add(e.dest, e.src, 0);
                // transpose[e.dest].add(new Edge(e.dest,e.src));
            }
        }

        // Step3
        boolean vis[] = new boolean[V];
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!vis[curr]) {
                g2.dfs(curr, vis);
                System.out.println();
            }
        }
    }

    public Queue<Edge> krushkals(){
        Queue<Edge> pq = new MinHeap<>();
        boolean vis[] = new boolean[V];
        for(int i=0;i<V;i++){
            for(Edge e: graph[i]){
                pq.enque(e);
            }
        }
        // int minCost = 0;
        // while(!pq.isEmpty()){
        //     Edge curr = pq.deque();
        //     pq.deque();
        //     if(!vis[curr.src] || !vis[curr.dest]){
        //         minCost;
        //     }
        // }
        return pq;
    }
}