public class Customer {
    int id,arriveTime,toShef,toGriddle,getFood,counterNumber;

    public Customer(int id, int arriveTime) {
        this.id = id;
        this.arriveTime = arriveTime;
        this.toShef = this.getFood = this.toGriddle = -1;
    }

    public int getStatus() {
        if (toShef == -1) {
            return 1;
        } else if (toGriddle == -1) {
            return 2;
        } else {
            return 3;
        }
    }
}
