
public class Flight{

    private String destination;
    private String origin;

    public Flight(String dst, String org){  
        this.destination = dst;
        this.origin = org;

    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String newDestination) {
        this.destination = newDestination;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String newOrigin) {
        this.origin = newOrigin;
    }

}
