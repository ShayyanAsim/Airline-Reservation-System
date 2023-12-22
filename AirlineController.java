import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AirlineController {
    private DatabaseConnection db;


    public AirlineController(){
        db = DatabaseConnection.getInstance();
    }

    public ResultSet browseAirports(String cityName) {
        return db.browseAirports(cityName);
    }

    public ResultSet browseAircrafts(String aircraftNumber) {
        return db.browseAircrafts(aircraftNumber);
    }

     public ResultSet browseUsers(String fullName, String email, String address) {
        return db.browseUsers(fullName,email,address);
    }


    public ResultSet browseAdmins(String username, int adminId) {
        return db.browseAdmins(username, adminId);

}
}
