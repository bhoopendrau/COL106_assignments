public class GrowableArray {
    public int size;
    private int capacity;
    private Object[] internalArray;

    public GrowableArray(int cap) {
        size = 0;
        capacity = cap;
        internalArray = new Object[cap];
    }

    public Object getAt(int index) {
        return internalArray[index];
    }

    public void insert(Object element) {
        if (size == capacity) {
            capacity = 2*size;
            Object[] newArray = new Object[capacity];
            for (int i=0; i<2*size; i++) {
                newArray[i] = internalArray[i];
            }
            internalArray = newArray;
        }
        internalArray[size] = element;
        size++;
    }

    public Object removeLast() {
        size--;
        return internalArray[size];
    }
    
}
