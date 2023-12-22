import java.util.List;

public class Seat{

    private String seat;
    private int seat_aval;

    public Seat (String s, int seat_tot) {  
        this.seat = s;
        if (seat_tot >0 ){
            seat_aval = seat_tot - 1;
        }
        else{
            
        }

    }

    public String getSeat() {
        return this.seat;
    }

    public void setSeat(String newSeat) {
        this.seat = newSeat;
    }
    
    public int getSeatsAvailable(){
        return this.seat_aval;
    }

    public void setSeatsAvailable(int newSeats){
        this.seat_aval = newSeats;
    }

    public int seatTaken(int seats){
        return seats -1;
    }

    public int seatRefunded(int seats){
        return seats +1;
    }

    public boolean seatChecker(int flightID, int seatNumber) {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean isSeatTaken = db.isSeatTaken(flightID, seatNumber);
    
        if (isSeatTaken) {
            System.out.println("Seat Taken");
            return false;
        }
    
        System.out.println("Seat Available");
        return true;
    }
    
    public static void main(String[] args) {
        try {
            DatabaseConnection db = DatabaseConnection.getInstance();
            db.createConnection();
            int testFlightID = 15;
            int testSeatNumber = 5;
    
            Seat seat = new Seat("", 10);
            boolean isSeatAvailable = seat.seatChecker(testFlightID, testSeatNumber);
    
            if (isSeatAvailable) {
                System.out.println("Booking the seat...");
            } else {
                System.out.println("Seat already taken. Choose another seat.");
            }
            db.bookSeatAndUpdateMaxseat(testFlightID);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}