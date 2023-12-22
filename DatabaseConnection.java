import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Connection dbConnect;
    private DatabaseConnection() {
    }
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/FLIGHTDATABASE", "root", "");
            dbConnect.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return dbConnect;
    }
    public List<String> getFlightDetails() {
    List<String> flightDetailsList = new ArrayList<>();

    try {
        String query = "SELECT Origin, Destination, SeatPrice, Maxseat, FlightDate, FlightTime FROM flights";
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String origin = resultSet.getString("Origin");
                String destination = resultSet.getString("Destination");
                double seatPrice = resultSet.getDouble("SeatPrice");
                int maxSeat = resultSet.getInt("Maxseat");
                Date flightDate = resultSet.getDate("FlightDate");
                Time flightTime = resultSet.getTime("FlightTime");

                String flightDetails = String.format("Origin: %s, Destination: %s, Seat Price: %.2f, Max Seats: %d, Flight Date: %s, Flight Time: %s",
                        origin, destination, seatPrice, maxSeat, flightDate, flightTime);

                flightDetailsList.add(flightDetails);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return flightDetailsList;
}

public List<Flight> getFlights() {
    List<Flight> flightDetailsList = new ArrayList<>();

    try {
        String query = "SELECT Origin, Destination, SeatPrice, Maxseat, FlightDate, FlightTime FROM flights";
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String origin = resultSet.getString("Origin");
                String destination = resultSet.getString("Destination");


                Flight flight =  new Flight(destination, origin);

                flightDetailsList.add(flight);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return flightDetailsList;
}

    public void insertUser(String fullname, String email, String address, String passkey, String member) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }

        try {
            Statement statement = dbConnect.createStatement();
            String query = String.format(
                "INSERT INTO users (Fullname, Email, Address, Passkey, Membership) VALUES ('%s', '%s', '%s', '%s', '%s')",
                fullname, email, address, passkey, member
            );
            statement.executeUpdate(query);
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    

   
    
        public ResultSet browseAirports(String cityName) {
            ResultSet resultSet = null;
            try {
                String query = "SELECT DISTINCT Origin, Destination FROM flights WHERE Origin = ? OR Destination = ?";
                try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)) {
                    preparedStatement.setString(1, cityName);
                    preparedStatement.setString(2, cityName);
                    resultSet = preparedStatement.executeQuery();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        }
    
        public ResultSet browseAircrafts(String aircraftNumber) {
            ResultSet resultSet = null;
            try {
                String query = "SELECT DISTINCT Aircraft FROM flights WHERE Aircraft = ?";
                try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)) {
                    preparedStatement.setString(1, aircraftNumber);
                    resultSet = preparedStatement.executeQuery();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        }
    
        public ResultSet browseUsers(String fullName, String email, String address) {
            ResultSet resultSet = null;
            try {
                String query = "SELECT Fullname, Email, Address FROM users WHERE Fullname = ? OR Email = ? OR Address = ?";
                try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)) {
                    preparedStatement.setString(1, fullName);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, address);
                    resultSet = preparedStatement.executeQuery();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        }

        public String adminBrowseUsers() {
            String result = "";
            try {
                String query = "SELECT Fullname, Email, Address FROM users";
                PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                    
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        result += resultSet.getString("Fullname") + " ";
                        result += resultSet.getString("Email")+ " ";
                        result += resultSet.getString("Address");
                        result += "\n";    
                    }
                    
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
        public String adminBrowseAircrafts() {
            String result = "";
            try {
                String query = "SELECT Aircraft FROM flights";
                PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                    
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        
                        result += resultSet.getString("Aircraft");
                        result += "\n";    
                    }
                    
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    
        public ResultSet browseAdmins(String username, int adminId) {
            ResultSet resultSet = null;
            try {
                String query = "SELECT Username, AdminID FROM admins WHERE Username = ? OR AdminID = ?";
                try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setInt(2, adminId);
                    resultSet = preparedStatement.executeQuery();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        }
    
    
     
    
    ////////////////////////////////////////////////////////////////////////FOR EXTRACTION>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public List<String> extractUserEmails() {
        List<String> emails = new ArrayList<>();

        try {
            String query = "SELECT Email FROM users";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String email = resultSet.getString("Email");
                    emails.add(email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }





    public List<String> extractUserPasskeys() {
        List<String> passkeys = new ArrayList<>();

        try {
            String query = "SELECT Passkey FROM users";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String passkey = resultSet.getString("Passkey");
                    passkeys.add(passkey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passkeys;
    }





    public List<String> extractAdminUsernames() {
        List<String> usernames = new ArrayList<>();

        try {
            String query = "SELECT Username FROM admins";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String username = resultSet.getString("Username");
                    usernames.add(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernames;
    }





    public List<String> extractAdminPasswords() {
        List<String> adminPasswords = new ArrayList<>();

        try {
            String query = "SELECT AdminPass FROM admins";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String adminPass = resultSet.getString("AdminPass");
                    adminPasswords.add(adminPass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminPasswords;
    }
        public List<String> extractCrewUsernames() {
        List<String> usernames = new ArrayList<>();

        try {
            String query = "SELECT Email FROM crew";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String username = resultSet.getString("Email");
                    usernames.add(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernames;
    }





    public List<String> extractCrewPasswords() {
        List<String> adminPasswords = new ArrayList<>();

        try {
            String query = "SELECT Pass FROM crew";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String adminPass = resultSet.getString("Pass");
                    adminPasswords.add(adminPass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminPasswords;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////END OF EXTRACTION\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\






































































































    public void insertPassengers(int FlightID, String PassengerName, int SeatNumber, String cancel) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }
        try {
            Statement statement = dbConnect.createStatement();
            String query = String.format(
                "INSERT INTO passengers (FlightID, PassengerName, SeatNumber, Refund) VALUES ('%d', '%s', '%d', '%s')",
                FlightID, PassengerName, SeatNumber, cancel
            );
            statement.executeUpdate(query);
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePassengers(int FlightID, String name, int seat) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }
        try {
            String query ="DELETE FROM passengers WHERE FlightID = ? AND PassengerName = ? AND SeatNumber = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
            preparedStatement.setInt(1, FlightID);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, seat);
            
                
            
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCrew(int flightID, String crewName, int CrewNumber) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "UPDATE flights SET Crew = ?, CrewNumber = ? WHERE FlightID = ?")) {
            preparedStatement.setString(1, crewName);
            preparedStatement.setInt(2, CrewNumber);
            preparedStatement.setInt(3, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
            System.out.println("Crew member set successfully for flight ID: " + flightID);}
            catch (SQLException e) {
            e.printStackTrace();
        }
                }
    public void modifyFlightPlan(int flightID, String newOrigin, String newDestination) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "UPDATE flights SET Origin = ?, Destination = ? WHERE FlightID = ?")) {
            preparedStatement.setString(1, newOrigin);
            preparedStatement.setString(2, newDestination);
            preparedStatement.setInt(3, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCrew(int flightID, String newCrew) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "UPDATE flights SET Crew = ? WHERE FlightID = ?")) {
            preparedStatement.setString(1, newCrew);
            preparedStatement.setInt(2, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectAircraft(int flightID, String newAircraft) {
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

    public void addFlight(int flightID, String origin, String destination, String crew, String aircraft) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "INSERT INTO flights (FlightID, Origin, Destination, Crew, Aircraft) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, flightID);
            preparedStatement.setString(2, origin);
            preparedStatement.setString(3, destination);
            preparedStatement.setString(4, crew);
            preparedStatement.setString(5, aircraft);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFlight(int flightID) {
        try (PreparedStatement preparedStatement = dbConnect.prepareStatement(
                "DELETE FROM flights WHERE FlightID = ?")) {
            preparedStatement.setInt(1, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightCrew(int flightID, String newCrew) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }

        try {
            PreparedStatement preparedStatement = dbConnect.prepareStatement(
                    "UPDATE flights SET Crew = ? WHERE FlightID = ?");
            preparedStatement.setString(1, newCrew);
            preparedStatement.setInt(2, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightOrigin(int flightID, String newOrigin) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }

        try {
            PreparedStatement preparedStatement = dbConnect.prepareStatement(
                    "UPDATE flights SET Origin = ? WHERE FlightID = ?");
            preparedStatement.setString(1, newOrigin);
            preparedStatement.setInt(2, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightDestination(int flightID, String newDestination) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }

        try {
            PreparedStatement preparedStatement = dbConnect.prepareStatement(
                    "UPDATE flights SET Destination = ? WHERE FlightID = ?");
            preparedStatement.setString(1, newDestination);
            preparedStatement.setInt(2, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightSeatPrice(int flightID, double newSeatPrice) {
        if (dbConnect == null) {
            System.out.println("Connection not established.");
            return;
        }

        try {
            PreparedStatement preparedStatement = dbConnect.prepareStatement(
                    "UPDATE flights SET SeatPrice = ? WHERE FlightID = ?");
            preparedStatement.setDouble(1, newSeatPrice);
            preparedStatement.setInt(2, flightID);
            preparedStatement.executeUpdate();
            dbConnect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlight(int flightID, String attribute, String newValue) {
        switch (attribute.toLowerCase()) {
            case "origin":
                updateFlightOrigin(flightID, newValue);
                break;
            case "destination":
                updateFlightDestination(flightID, newValue);
                break;
            case "seatprice":
                updateFlightSeatPrice(flightID, Double.parseDouble(newValue));
                break;
            default:
                System.out.println("Invalid attribute.");
        }
    }

    public void close() {
        try {
            if (dbConnect != null) {
                dbConnect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCrewFlightID(String crewEmail) {
        int id =0;

        try {
            String query = "SELECT CrewNumber FROM crew WHERE Email = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
            preparedStatement.setString(1, crewEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);

            String query2 = "SELECT FlightID FROM flights WHERE CrewID = ?";
            PreparedStatement preparedStatement2 = dbConnect.prepareStatement(query2);
            preparedStatement2.setInt(1, id);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            resultSet2.next();
            id = resultSet2.getInt(1);


            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public List<String> getFlightIDs() {
        List<String> flightDetailsList = new ArrayList<>();

        try {
            String query = "SELECT FlightID FROM flights";
            try (PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String id = resultSet.getString("FlightID");


                    String flightDetails = id;

                    flightDetailsList.add(flightDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flightDetailsList;
    }

    public String getNameFromEmail(String email) {
        String name;

        try {
            String query = "SELECT Fullname FROM users WHERE Email = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            name = resultSet.getString(1);


            
        } catch (SQLException e) {
            name = "";
            e.printStackTrace();
        }

        return name;
    }

    public List<String> getFlightsToCancel(List<String> ids) {
    List<String> flightDetailsList = new ArrayList<>();

    try {
        for (int i = 0; i < ids.size(); i ++) {
            String query = "SELECT Origin, Destination, SeatPrice, Maxseat, FlightDate, FlightTime FROM flights WHERE FlightID = ?";
            PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf(ids.get(i)));
            ResultSet resultSet = preparedStatement.executeQuery() ;

                while (resultSet.next()) {
                    String origin = resultSet.getString("Origin");
                    String destination = resultSet.getString("Destination");
                    double seatPrice = resultSet.getDouble("SeatPrice");
                    int maxSeat = resultSet.getInt("Maxseat");
                    Date flightDate = resultSet.getDate("FlightDate");
                    Time flightTime = resultSet.getTime("FlightTime");

                    String flightDetails = String.format("Origin: %s, Destination: %s, Seat Price: %.2f, Max Seats: %d, Flight Date: %s, Flight Time: %s",
                            origin, destination, seatPrice, maxSeat, flightDate, flightTime);

                    flightDetailsList.add(flightDetails);
            }
        }
        
        }
     catch (SQLException e) {
        e.printStackTrace();
    }

    return flightDetailsList;
}

public List<String> getFlightIDsToCancel(String name) {
    List<String> flightIDsList = new ArrayList<>();

    try {
        String query = "SELECT FlightID FROM passengers WHERE PassengerName = ? AND Refund = ?";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, "yes");
        ResultSet resultSet = preparedStatement.executeQuery() ;

            while (resultSet.next()) {

                String flightID = Integer.toString(resultSet.getInt("FlightID"));


                flightIDsList.add(flightID);
            }
        }
     catch (SQLException e) {
        e.printStackTrace();
    }

    return flightIDsList;
}

public void bookSeatAndUpdateMaxseat(int flightID) {
    try {
        String query = "SELECT Maxseat FROM flights WHERE FlightID = ?";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setInt(1, flightID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int maxseat = resultSet.getInt("Maxseat");
            Seat seat = new Seat("", maxseat);
            int updatedMaxseat = seat.seatTaken(maxseat);
            String updateQuery = "UPDATE flights SET Maxseat = ? WHERE FlightID = ?";
            PreparedStatement updateStatement = dbConnect.prepareStatement(updateQuery);
            updateStatement.setInt(1, updatedMaxseat);
            updateStatement.setInt(2, flightID);
            updateStatement.executeUpdate();
            dbConnect.commit();
            System.out.println("Seat booked successfully. Updated Maxseat: " + updatedMaxseat);
        } else {
            System.out.println("Flight not found");
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
}

///////////////////////////////////////////////////////////SEAT CHECKER/////////////////////////////////////////////////////////////////////////////////////////////////////////////////



public boolean isSeatTaken(int flightID, int seatNumber) {
    try {
        String query = "SELECT * FROM passengers WHERE FlightID = ? AND SeatNumber = ?";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setInt(1, flightID);
        preparedStatement.setInt(2, seatNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next(); 
    } catch (SQLException e) {
        e.printStackTrace();
        return false; 
    }
}

public int numSeats(int flightID) {
    try {
        String query = "SELECT Maxseat FROM flights WHERE FlightID = ?";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setInt(1, flightID);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("Maxseat"); 
    } catch (SQLException e) {
        e.printStackTrace();
        return -1; 
    }
}

public int getSeat(int flightID, String name) {
    try {
        String query = "SELECT SeatNumber FROM passengers WHERE FlightID = ? AND PassengerName = ?";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setInt(1, flightID);
        preparedStatement.setString(2, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("SeatNumber"); 
    } catch (SQLException e) {
        e.printStackTrace();
        return -1; 
    }
}

public double getPrice(int flightID) {
    try {
        String query = "SELECT SeatPrice FROM flights WHERE FlightID = ? ";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setInt(1, flightID);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getDouble("SeatPrice"); 
    } catch (SQLException e) {
        e.printStackTrace();
        return -1; 
    }
}

public String isMember(String email) {
    try {
        String query = "SELECT Membership FROM users WHERE email = ? ";
        PreparedStatement preparedStatement = dbConnect.prepareStatement(query);
        preparedStatement.setString(1, email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getString("Membership"); 
    } catch (SQLException e) {
        e.printStackTrace();
        return ""; 
    }
}

}

    
