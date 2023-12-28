package Trees.BinaryTrees;
/*
 * this java code is implementation of redblack trees extracted from various sources 
 * made easy to uderstand with diagrams
 * @author manoj_rayi_369
 */
public class RedBlackTree extends BinarySearchTree {
    private boolean red = true;
    private boolean black = false;
    public Node root;

    public Node search(int data) {
        return search(root, data);
    }

    public Node search(Node node, int data) {
        if (node == null)
            return null;
        if (data > node.data)
            return search(node.right, data);
        else if (data < node.data)
            return search(node.left, data);
        return node;
    }

    public void insert(int data) {
        root = insert(root, data);
        root.color = black;
    }

    public void delete(int data) {
        if (root.data == data && root.left == null && root.right == null) {
            root = null;
            return;
        }
        delete(search(root, data));
    }

    boolean ll = false;
    boolean rr = false;
    boolean lr = false;
    boolean rl = false;

    Node insert(Node node, int data) {
        if (node == null)
            return new Node(data);
        boolean f = false;
        if (data > node.data) {
            node.right = insert(node.right, data);
            node.right.parent = node;
            if (node != root) {
                if (node.color == red && node.right.color == red) {
                    f = true;
                }
            }
        } else if (data < node.data) {
            node.left = insert(node.left, data);
            node.left.parent = node;
            if (node != root) {
                if (node.color == red && node.left.color == red) {
                    f = true;
                }
            }
        }
        if (this.ll) {
            node = leftRotate(node);
            node.color = black;
            node.left.color = red;
            ll = false;
        }
        if (this.rr) {
            node = rightRotate(node);
            node.color = black;
            node.right.color = red;
            rr = false;
        }
        if (this.lr) {
            node.left = leftRotate(node.left);
            node.left.parent = node;
            node = rightRotate(node);
            node.color = black;
            node.right.color = red;
            lr = false;
        }
        if (this.rl) {
            node.right = rightRotate(node.right);
            node.right.parent = node;
            node = leftRotate(node);
            node.color = black;
            node.left.color = red;
            rl = false;
        }
        if (f) {
            if (node.parent.right == node) {
                if (node.parent.left == null || node.parent.left.color == black) {
                    if (node.left != null && node.left.color == red) {
                        this.rl = true;
                    } else if (node.right != null && node.right.color == red) {
                        this.ll = true;
                    }
                } else {
                    node.color = black;
                    node.parent.left.color = black;
                    if (node.parent != root)
                        node.parent.color = red;
                }
            } else if (node.parent.left == node) {
                if (node.parent.right == null || node.parent.right.color == black) {
                    if (node.left != null && node.left.color == red) {
                        this.rr = true;
                    } else if (node.right != null && node.right.color == red) {
                        this.lr = true;
                    }
                } else {
                    node.parent.right.color = black;
                    node.color = black;
                    if (node.parent != root)
                        node.parent.color = red;
                }
            }
        }
        return node;
    }

    Node leftRotate(Node node) {
        Node x = node.right;
        Node t2 = x.left;
        x.left = node;
        node.right = t2;
        node.parent = x;
        if (t2 != null)
            t2.parent = node;
        return x;
    }

    Node rightRotate(Node node) {
        Node x = node.left;
        Node t2 = x.right;
        x.right = node;
        node.parent = x;
        node.left = t2;
        if (t2 != null)
            t2.parent = node;
        return x;
    }
    void swap(Node v,Node u){
        int temp=v.data;
        v.data=u.data;
        u.data=temp;
    }
    private Node BSTReplace(Node v) {
        if (v.left == null && v.right == null)
            return null;
        if (v.left != null && v.right != null)
            return successor(v.right);
        if (v.left == null)
            return v.right;
        return v.left;
    }
    void delete(Node v) {
        Node u = BSTReplace(v);
        boolean uvBlack = (u == null || u.color == black) && v.color == black;
        //uvBlack is true when double black exist
        Node parent = v.parent;
        // checking if there is a double black uvBlack is true when there is double
        // black
        if (u == null) {
            // v is leaf node
            if (v == root)
                root = null;
            else {
                if (uvBlack) {
                    // fixing double black at v
                    fixDB(v);
                } else {
                    // v is leaf node and red node
                    if (v.sibling() != null) {
                        v.sibling().color = red;
                    }
                }
                if (v.isOnLeft())
                    parent.left = null;
                else
                    parent.right = null;
            }
            v = null;
            return;
        }
        if (v.left == null || v.right == null) {
            // v has a single child
            if (v == root) {
                // v is root
                v.data = u.data;
                v.left = v.right = null;
                u = null;
            } else {
                // detach v and atatch u to the parent
                    /*
                          10B                       10B
                        /   \                      /   \
                       7B     18B --> into -->    7B    18B
                            /
                           15R       */
                if (v.isOnLeft())
                    parent.left = u;
                else
                    parent.right = u;
                v = null;
                u.parent = parent;
                // check for double black
                if (uvBlack)
                // double black exist
                    fixDB(u);
                else
                    u.color = black;
            }
            return;
        }
/* 
IF V HAS TWO CHILDREN AND SWAP THE VALUE OF V AND V THEN DELETE U;
         10B                                    10B
       /    \                                 /     \
      5R     30R(v)                          5R      38R  
     /   \   /   \      ---> into --->      / \     /   \
    2B   9B 25B  40B                       2B  9B  25B 40B
             /                                          /
            38R(u)                                    38R(u)
 IN THE ABOVE EXAMPLE WE WILL SWAP THE VALUES AND THEN DELETED THE SWAPPED VALUE NODE
 WE SIMPLY DELETE U NODE WHICH IS LEAF OF THE TREE;           
 */
        swap(u, v);
        delete(u);
    }
    void fixDB(Node x){
        //if DB is at root just return
        if(x==root)
            return ;
        Node sibling=x.sibling(),parent=x.parent;
        if(sibling==null){
            // if sibling is null then fix parent node
            fixDB(parent);
        }else{
/* 
sibling color is red then swap colors of parent and sibling
rotate parent towards DB
EX: DELETE(19)
           10B                                 10B
         /     \                             /     \
       5B      20B                          5B     30B
     /   \      /  \     --> into -->      /  \    /  \
    1B   7B  (19B) 30R                    1B  7B 20R  40B
                  / \                            / \  
                25B  40B                      (19B) 25B

HERE WE HAVE TO DELETE NODE 19 THEREFORE IT IS DB
1. SWAP THE COLOURS OF PARENT AND SIBLING
2. SO WE ROTATE IT'S PARENT INTO DB'S DIRECTION
3. STILL NODE 19 IS DOUBLE BLACK REAPPLY CASES
THOSE () DENOTE IT IS A DOUBLE BLACK NODE
                 */
            if(sibling.color==red){
                parent.color=red;
                sibling.color=black;
                if(x.isOnLeft()) rotateLeft(parent);
                else rotateRight(parent);
                // apply further cases at DB
                fixDB(x);
            }else{
                if(sibling.hasRedChild()){
/*
SIBLING HAS A RED CHILD 
CASE 1. RED CHILD IS NEAR TO DB
CASE 2. RED CHILD IS FAR TO DB

EXAMPLE : DELETE(5)
       10B                                   10B                                     25B
      /   \                                 /   \                                   /   \
     (5)B  30B                           (5)B   25B                               10B    30B 
       \   /  \        ---> INTO -->        \   /  \     ---> APPLY CASE 2 -->   /  \    /  \
      7R  25R  40B                          7B 20B  30R                         5B  20B 28B  40B
         /  \                                       /  \                         \   
       20B   28B                                  25B  40B                       7R

CASE 1: 
    1. SWAP COLORS OF DB'S SIBILNG AND SIBLING CHILD NEAR TO DB
    2. ROTATE SIBLING IN OPPOSITE DIRECTION TO DDE
    3. APPLY CASE 2
CASE 2:
    1. SWAP COLOR OF PARENT AND SIBLING
    2.ROTATE PARENT IN DB'S DIRECTION
    3. CHANGE COLOUR OF RED CHILD TO BLACK

**APPLY CASES ACCORDING TO THE DIRECTION OF DB AND SIBLINGS ETC**
    */
                    if(sibling.left!=null && sibling.left.color==red){
                        if(sibling.isOnLeft()){
                            sibling.left.color=sibling.color;
                            sibling.color=parent.color;
                            rotateRight(parent);
                        }else{
                            sibling.left.color=parent.color;
                            rotateRight(sibling);
                            rotateLeft(parent);
                            
                        }
                    }else{
                        if(sibling.isOnLeft()){
                            sibling.right.color=parent.color;
                            rotateLeft(sibling);
                            rotateRight(parent);
                        }else{
                            sibling.right.color=sibling.color;
                            sibling.color=parent.color;
                            rotateLeft(parent);
                        }
                    }
                    parent.color=black;
                }else{
/* 
SIBLING IS BLACK AND BOTH ITS CHILDREN ARE BLACK OR NULL
EXAMPLE 1: DELETE(5)
        10B                            10B
       /  \      ---> INTO --->           \
      5B  15B                             15R
 
 EXAMPLE 2: DELETE(2)
          10B                               10B                           10B  
         /   \                             /   \                         /   \
     (2B)     20B     ---> INTO --->     (7B)   20B     --> INTO -->    7R    20R  
     /  \    /   \                       /     /   \                   /     /   \
    1B  7B  15B  30B                    1B    15B  30B                1B    15B  30B 

1. ADD BLACK TO ITS PARENT
    1. IF PARENT IS RED THEN PARENT BECOMES BLACK
    2. IF PARENT IS BLACK THEN PARENT IS NOW DOUBLE BLACK
2. MAKE SIBLING RED    
    */
                    sibling.color=red;
                    if(parent.color==black)
                        fixDB(parent);
                    else
                        parent.color=black;
                }
            }
        }
    }
    void rotateLeft(Node x){
        Node newParent=x.right;
        if(x==root)
            root=newParent;
        x.moveDown(newParent);
        x.right=newParent.left;
        if(newParent.left!=null)
            newParent.left.parent=x;
        newParent.left=x;
    }
    void rotateRight(Node x){
        Node newParent=x.left;
        if(x==root)
            root=newParent;
        x.moveDown(newParent);
        x.left=newParent.right;
        if(newParent.right!=null)
            newParent.right.parent=x;
        newParent.right=x;
    }
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + (node.color ? "R" : "B") + " ");
            inOrder(node.left);
            inOrder(node.right);
        }
    }
}
