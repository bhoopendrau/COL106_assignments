
// Tree node
class Node {
    private int id;
    public Node[] children;
    public Node boss;
    private int level,size;
  
    public Node(int id) {
        this.id = id;
        this.children = new Node[1];
        this.size = 0;
        this.boss = null;
        this.level = 1;
    }
  
    public Node(int id, Node boss) {
          this.id = id;
          this.children = new Node[1];
          this.size = 0;
          this.boss = boss;
          this.level = boss.getLevel()+1;
      }
  
    public int getLevel() {
          return level;
    }
  
    public int getId() {
        return id;
    }

    public int childrenSize() {
        return size;
    }
  
  
    // adding employee to boss
    // O(1)
    public void addChild (Node node) {
        // Increasing array size when childArray filled
        if (size == children.length) {
            Node[] newChildren = new Node[2*children.length];
            for (int i=0; i<children.length; i++) {
                newChildren[i] = children[i];
            }
            children = newChildren;
        }
        //adding node to array
        children[size] = node;
        size++;
    }
  
    // removing employee
    // O(n)
    public void removeChild(Node node) {
        Boolean foundNode = false;
        for (int i=0; i<size; i++) {
            if (foundNode) {
                if (i==size-1) {
                    size--;
                } else {
                    children[i] = children[i+1];
                }
            }
            if (children[i] == node) {
                if (i==size-1) {
                    size--;
                } else {
                    children[i] = children[i+1];
                }
                foundNode = true;
            }
        }
    }
  
  }