

public class AdminController {

    private DatabaseConnection dbConnection;

    public AdminController() {
        dbConnection = DatabaseConnection.getInstance();
    }

    public void selectCrew(int flightID, String newCrew,  int CrewNumber) {
        dbConnection.createConnection();
        dbConnection.selectCrew(flightID, newCrew, CrewNumber);
    }

    public void selectAircraft(int flightID, String newAircraft) {
        dbConnection.createConnection();
        dbConnection.selectAircraft(flightID, newAircraft);
    }

    public void modifyFlightPlan(int flightID, String attribute, String newValue) {
        dbConnection.createConnection();
        dbConnection.updateFlight(flightID, attribute, newValue);
    }

    public void addFlight(int flightID, String origin, String destination, String crew, String aircraft) {
        dbConnection.createConnection();
        dbConnection.addFlight(flightID, origin, destination, crew, aircraft);
    }

    public void removeFlight(int flightID) {
        dbConnection.removeFlight(flightID);
    }

    public void updateFlightCrew(int flightID, String newCrew) {
        dbConnection.updateFlightCrew(flightID, newCrew);
    }

    public String browseUsers() {
        dbConnection.createConnection();
        return dbConnection.adminBrowseUsers();
    }

    public String browseAircrafts() {
        dbConnection.createConnection();
        return dbConnection.adminBrowseAircrafts();
    }

}
