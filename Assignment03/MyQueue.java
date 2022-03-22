public class MyQueue {
    Customer[] queue;
    int front,tail,capacity,size;

    public MyQueue() {
        queue = new Customer[2];
        size = 0;
        front = -1;
        tail = 0;
        capacity = 2;
    }

    private Boolean capacityFilled() {
        return (tail-front+capacity)%capacity == 0;
    }

    public void enqueue(Customer element) {
        if (size == 0) {
            front =0;
            tail = 1;
            size++;
            queue[0] = element;
        } else {
            if (capacityFilled()) {
                capacity = 2*capacity;
                Customer[] newQueue = new Customer[capacity];
                for (int i=0; i<size; i++){
                    newQueue[i] = queue[(front+i)%size];
                }
                queue = newQueue;
            }
            tail = (tail+1)%capacity;
            queue[(tail-1+capacity)%capacity] = element;
            size++;
        }
        
    }

    public Customer dequeue() {
        if (size == 1) {
            front = -1;
            tail = size = 0;
            return queue[0];
        } else {
            tail = (tail-1+capacity)%capacity;
            size--;
            return queue[tail];
        }
    }
    
}
