public class MyHeap {

    private Customer[] Heap;
    private int size;
    private int maxsize;
    private static final int FRONT = 1;
 
    // Constructor of this class
    public MyHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Customer[this.maxsize + 1];
        Heap[0] = new Customer(5,5);
    }
    // Returning the position of the parent for the node currently at pos
    private int parent(int pos) { return pos / 2; }
 
    // Returning the position of the left child for the node currently at pos
    private int leftChild(int pos) { return (2 * pos); }
 
    // Returning the position of the right child for the node currently at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    // Returning true if the passed node is a leaf node
    private boolean isLeaf(int pos)
    {
 
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
 
        return false;
    }
 
    // To swap two nodes of the heap
    private void swap(int fpos, int spos)
    {
 
        Customer tmp;
        tmp = Heap[fpos];
 
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
 
    // To heapify the node at pos
    private void minHeapify(int pos)
    {
 
        // If the node is a non-leaf node and greater than any of its child
        if (!isLeaf(pos)) {
            if (Heap[pos].toShef > Heap[leftChild(pos)].toShef
                || Heap[pos].toShef > Heap[rightChild(pos)].toShef) {
 
                // Swap with the left child and heapify the left child
                if (Heap[leftChild(pos)].toShef
                    < Heap[rightChild(pos)].toShef) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
 
                // Swap with the right child and heapify the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }
 
    // To insert a node into the heap
    public void insert(Customer element)
    {
 
        if (size >= maxsize) {
            maxsize = 2*size;
            Customer[] newArray = new Customer[maxsize];
            for (int i=0; i<2*size; i++) {
                newArray[i] = Heap[i];
            }
            Heap = newArray;
        }
 
        Heap[++size] = element;
        int current = size;
 
        while (Heap[current].toShef < Heap[parent(current)].toShef) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // remove and return the minimum element from the heap
    public Customer remove()
    {
 
        Customer popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        minHeapify(FRONT);
 
        return popped;
    }
}
