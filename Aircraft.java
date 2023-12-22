import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Aircraft {
    
    
    //IS THIS CLASS SUPPOSE TO UPDATE THE DATABASE????

    private Connection dbConnect;
    public Aircraft(Connection dbConnect) {
        this.dbConnect = dbConnect;
    }

    public void updateAircraft(int flightID, String newAircraft) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "UPDATE flights SET Aircraft = ? WHERE FlightID = ?")) {
            preparedStatement.setString(1, newAircraft);
            preparedStatement.setInt(2, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
