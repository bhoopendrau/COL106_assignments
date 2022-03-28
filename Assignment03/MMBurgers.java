

public class MMBurgers implements MMBurgersInterface {
    private int currentTime = 0;
    private int totalCounters = -1;
    private int griddleSize = -1;
    private MyQueue[] counterQueues;
    private MyQueue griddleQueue = new MyQueue();
    private int[] numCounterQueues;
    private MyHeap customerHeap = new MyHeap(2);
    private int totalWait = 0;
    private int totalDone = 0;

    public boolean isEmpty(){
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return false;
    } 
    
    public void setK(int k) throws IllegalNumberException{
        if (k>0) {
            if (totalCounters == -1) {
                totalCounters = k;
                counterQueues = new MyQueue[totalCounters];
                numCounterQueues = new int[totalCounters];
                for (int i=0; i<totalCounters; i++) {
                    numCounterQueues[i] = 0;
                }
            }
        } else {
            throw new IllegalNumberException("Negative number");
        }	
    }   
    
    public void setM(int m) throws IllegalNumberException{
        if (m>0) {
            if (griddleSize == -1) {
                griddleSize = m;
            }
        } else {
            throw new IllegalNumberException("Negative number");
        }	
    } 

    public void advanceTime(int t) throws IllegalNumberException{

        while(griddleQueue.isEmpty()) {
            
        }


        while(customerHeap.size != 0) {
            Customer topElement = customerHeap.minElement();
            if (topElement.toShef<currentTime && topElement.toShef!=-1) {
                if(topElement.toGriddle<currentTime) {
                    if (topElement.toGriddle+10<=currentTime+t){
                        customerHeap.remove();
                        int wait = topElement.toGriddle+10-topElement.arriveTime;
                        totalWait = totalWait+wait;
                        totalDone++;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        currentTime = currentTime+t;
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int customerState(int id, int t) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return 0;
    } 

    public int griddleState(int t) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return 0;
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return 0;
    } 

    public int customerWaitTime(int id) throws IllegalNumberException{
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return 0;
    } 

	public float avgWaitTime(){
        //your implementation
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
        return 0;
    } 

    
}
