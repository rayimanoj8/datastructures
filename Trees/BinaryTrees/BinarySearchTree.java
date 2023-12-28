package Trees.BinaryTrees;
public class BinarySearchTree {
    public Node root;
    public void insert(int data){
        root=insert(root,data);
    }
    public Node search(int data){
        return search(root,data);
    }
    Node insert(Node node,int data){
        if(node==null) return new Node(data);
        if(data>node.data)
            node.right=insert(node.right,data);
        else if(data<node.data)
            node.left=insert(node.left,data);
        else
            return node;
        return node;
    }
    void delete(int data){
        root=delete(root,data);
    }
    Node delete(Node node,int data){
        if(node==null)
            return null;
        if(data<node.data)
            node.left=delete(node.left,data);
        else if(data>node.data)
            node.right=delete(node.right, data);
        else{
            //node found
            if(node.left==null)
                return node.right;
            else if(node.right==null)
                return node.left;
            Node temp=successor(node.right);
            node.data=temp.data;
            node.right=delete(node.right,temp.data);
        }
        return node;
    }
    protected Node successor(Node curr) {
        while(curr.left!=null)
            curr=curr.left;
        return curr;
    }
    Node search(Node node,int data){
        if(node==null)
            return null;
        if(data>node.data)
            return search(node.right,data);
        else if(data<node.data)
            return search(node.left,data);
        else 
            return node;
    }
    public Node lcaNode(int v1,int v2){
        // used to find least common ancestor
        return lcaNode(root,v1, v2);
    }
    Node lcaNode(Node node,int v1,int v2){
        if(node==null)
            return null;
        if(v1>node.data && v2>node.data)
            return lcaNode(node.right, v1, v2);
        else if(v1<node.data && v2<node.data)
            return lcaNode(node.left, v1, v2);
        return node;
    }
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node!=null){
            System.out.print(node.data+" ");
            inOrder(node.left);
            inOrder(node.right);
        }
    }
}
