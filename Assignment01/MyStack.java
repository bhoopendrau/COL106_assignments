


public class MyStack implements StackInterface {
    Object privateStack[];
    int size;

    public MyStack() {
        privateStack = new Object[1];
        size = 0;
    }

    @Override
    public void push(Object o) {
        if (o!=null) {
            if (size == privateStack.length-1) {
                Object temp[] = new Object[2*privateStack.length];
                for (int i=0; i<privateStack.length; i++) {
                    temp[i] = privateStack[i];
                }
                privateStack = temp;
            }
            privateStack[size] = o;
            size++;
            
        }
       
    }

    @Override
    public Object pop() throws EmptyStackException {
        Object returnObj;
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            returnObj = privateStack[size-1];
            privateStack[size-1] = null;
            size--;
            return returnObj;
        }  
    }

    @Override
    public Object top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return privateStack[size-1];
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String outString = "[";
        for (int i=0; i<size; i++) {
            outString = outString+privateStack[size-i-1]+", ";
        }
        if (size != 0) {
            outString = outString.substring(0,outString.length()-2);
        }
        outString = outString+"]";
        return outString;
    }
}