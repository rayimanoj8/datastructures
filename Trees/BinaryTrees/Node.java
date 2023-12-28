package Trees.BinaryTrees;
/*
 * this node works with 
 * 1. binary search tree
 * 2. avl tree
 * 3. red black tree
 */
class Node {
    boolean red=true;
    boolean black=false;
    int data;
    Node left,right,parent;
    int height;
    boolean color;
    Node(int data){
        this.data=data;
        this.height=1;
        color=red;
    }
    public boolean isOnLeft() {
        return this==this.parent.left;
    }
    void moveDown(Node newParent){
        if(parent!=null){
            if(isOnLeft())
                parent.left=newParent;
            else
                parent.right=newParent;
        }
        newParent.parent=parent;
        parent=newParent;
    }
    Node sibling(){
        if(parent==null)
            return null;
        if(isOnLeft())
            return parent.right;
        return parent.left;
    }
    boolean hasRedChild(){
        return (left!=null && left.color) ||(right!=null && right.color);
    }  
}
