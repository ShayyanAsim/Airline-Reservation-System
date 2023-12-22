import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crew {

    public static String browsePassengers(int flightID) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String result = "";

        try {
            connection = dbConnection.getConnection();
            String query = "SELECT PassengerName, SeatNumber FROM passengers WHERE FlightID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, flightID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String passengerName = resultSet.getString("PassengerName");
                int seatNumber = resultSet.getInt("SeatNumber");

                
                result +="Passenger: " + passengerName + ", Seat Number: " + seatNumber +"\n";
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQL Error";
        } finally {
            dbConnection.closeResources(resultSet, preparedStatement, connection);
            
        }
    }

    public static boolean crewLogin(String email, String pass) {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.getConnection();
            String query = "SELECT * FROM crew WHERE Email = ? AND Pass = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            // If there is a matching crew member, allow login
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbConnection.closeResources(resultSet, preparedStatement, connection);
        }
    }

}
