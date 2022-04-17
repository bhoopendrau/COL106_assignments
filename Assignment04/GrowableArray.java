public class GrowableArray {
    Object[] arr;
    int capacity, size;
    public GrowableArray(int cap) {
        arr = new Object[cap];
        capacity = cap;
        size = 0;
    }
    public Object getAt(int index) {
        return arr[index];
    }

    public void setAt(int index, Object element) {
        arr[index] = element;
    }

    public void add(Object element) {
        if (size == capacity-1) {
            Object[] arr2 = new Object[2*capacity];
            for (int i=0; i<arr.length; i++) {
                arr2[i] = arr[i];
            }
            arr = arr2;
            capacity = 2*capacity;
        }
        arr[size] = element;
        size++;
    }

    public int size() {
        return size;
    }

    public void print() {
        for (int i=0; i<size; i++) {
            System.out.println(arr[i]);
        }
    }
}