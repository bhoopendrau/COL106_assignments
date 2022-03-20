
// Creating Node class which contain Employee Node as data
class BinaryNode {
    Node data;
    BinaryNode left,right;
    public int height;

    public BinaryNode(Node node) {
        data = node;
        left = right = null;
    }

    public int getId() {
        return data.getId();
    }
}
  
 public class MySearchTree {
    BinaryNode root; //root node

    int height(BinaryNode N) {
      if (N == null)
        return 0;
      return N.height;
    }
  
    int max(int a, int b) {
      return (a > b) ? a : b;
    }
  
    //Right rotate function
    BinaryNode rightRotate(BinaryNode y) {
      BinaryNode x = y.left;
      BinaryNode T2 = x.right;
      x.right = y;
      y.left = T2;
      y.height = max(height(y.left), height(y.right)) + 1;
      x.height = max(height(x.left), height(x.right)) + 1;
      return x;
    }
  
    //Left rotate function
    BinaryNode leftRotate(BinaryNode x) {
      BinaryNode y = x.right;
      BinaryNode T2 = y.left;
      y.left = x;
      x.right = T2;
      x.height = max(height(x.left), height(x.right)) + 1;
      y.height = max(height(y.left), height(y.right)) + 1;
      return y;
    }
  
    // Get balance factor of node
    int getBalanceFactor(BinaryNode N) {
      if (N == null)
        return 0;
      return height(N.left) - height(N.right);
    }
  
    // Insert a node
    // O(log(n))
    Node insertNode(Node item) {
      root = insertNode(root,item);
      return root.data;
    }

    BinaryNode insertNode(BinaryNode node, Node item) {
  
      // Find the position and insert the node
      if (node == null)
        return (new BinaryNode(item));
      if (item.getId() < node.getId())
        node.left = insertNode(node.left, item);
      else if (item.getId() > node.getId())
        node.right = insertNode(node.right, item);
      else
        return node;
  
      // Update the balance factor of each node
      // And, balance the tree
      node.height = 1 + max(height(node.left), height(node.right));
      int balanceFactor = getBalanceFactor(node);
      if (balanceFactor > 1) {
        if (item.getId() < node.left.getId()) {
          return rightRotate(node);
        } else if (item.getId() > node.left.getId()) {
          node.left = leftRotate(node.left);
          return rightRotate(node);
        }
      }
      if (balanceFactor < -1) {
        if (item.getId() > node.right.getId()) {
          return leftRotate(node);
        } else if (item.getId() < node.right.getId()) {
          node.right = rightRotate(node.right);
          return leftRotate(node);
        }
      }
      return node;
    }
  
    BinaryNode nodeWithMimumValue(BinaryNode node) {
      BinaryNode current = node;
      while (current.left != null)
        current = current.left;
      return current;
    }
  

    // Delete a node
    //O(log(n))
    Node deleteNode(int id) {
      root =  deleteNode(root,id);
      return root.data;
    }
    BinaryNode deleteNode(BinaryNode privRoot, int id) {
      // Find the node to be deleted and remove it
      if (privRoot == null)
        return privRoot;
      if (id < privRoot.getId())
        privRoot.left = deleteNode(privRoot.left, id);
      else if (id > privRoot.getId())
        privRoot.right = deleteNode(privRoot.right, id);
      else {
        if ((privRoot.left == null) || (privRoot.right == null)) {
          BinaryNode temp = null;
          if (temp == privRoot.left)
            temp = privRoot.right;
          else
            temp = privRoot.left;
          if (temp == null) {
            temp = privRoot;
            privRoot = null;
          } else
            privRoot = temp;
        } else {
          BinaryNode temp = nodeWithMimumValue(privRoot.right);
          privRoot.data = temp.data;
          privRoot.right = deleteNode(privRoot.right, temp.getId());
        }
      }
      if (privRoot == null)
        return privRoot;
  
      // Update the balance factor of each node and balance the tree
      privRoot.height = max(height(privRoot.left), height(privRoot.right)) + 1;
      int balanceFactor = getBalanceFactor(privRoot);
      if (balanceFactor > 1) {
        if (getBalanceFactor(privRoot.left) >= 0) {
          return rightRotate(privRoot);
        } else {
          privRoot.left = leftRotate(privRoot.left);
          return rightRotate(privRoot);
        }
      }
      if (balanceFactor < -1) {
        if (getBalanceFactor(privRoot.right) <= 0) {
          return leftRotate(privRoot);
        } else {
          privRoot.right = rightRotate(privRoot.right);
          return leftRotate(privRoot);
        }
      }
      return privRoot;
    }

    //search a node 
    //O(log(n))
    Node get(int id) {
      BinaryNode current = root;
      while (current != null) {
        if (current.getId() == id) {
            break;
        }
        current = current.getId() < id ? current.right : current.left;
      }
      return current.data;
    }

}
