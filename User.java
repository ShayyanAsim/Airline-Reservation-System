import java.sql.ResultSet;
import java.util.List;

public class User{

    private String fullName;
    private String email;
    private String address;
    private String userPassword;
    private String membership;
    private String adminUsername;
    private int adminID;
    private String adminPassword;
    private static DatabaseConnection db;

    public User(String fn, String em, String ad, String pass){  
        this.fullName = fn;
        this.email = em;
        this.address = ad;
        this.userPassword = pass;
        this.membership = "False";
    }

    public User(String admName, String pass, int adminID){  
        this.adminUsername = admName;
        this.adminPassword = pass;

    }

    public User(String em, String pass){
        this.email = em;
        this.userPassword = pass;
    }

    // public void regUser(){
    //     db = DatabaseConnection.getInstance();
    //     db.insertUser(fullName, email, address, userPassword);
    // }

    public static boolean loginUser(String inputEmail, String inputPass){
        db = DatabaseConnection.getInstance();
        db.createConnection();
        List<String> emailList = db.extractUserEmails();
        List<String> passwordList = db.extractUserPasskeys();

        if (emailList.contains(inputEmail) && passwordList.contains(inputPass)){
            System.out.println("User found");
            return true;
        }
        System.out.println("User not found");
        return false;

    }

    public static boolean loginAdmin(String inputUsername, String inputPass){

        db = DatabaseConnection.getInstance();
        db.createConnection();
        List<String> usernameList = db.extractAdminUsernames();
        List<String> passwordList = db.extractAdminPasswords();
        
        if (usernameList.contains(inputUsername) && passwordList.contains(inputPass)){
            System.out.println("User found");
            return true;
        }
        System.out.println("User not found");
        return false;
    }

        public static boolean loginCrew(String inputUsername, String inputPass){

        db = DatabaseConnection.getInstance();
        db.createConnection();
        List<String> usernameList = db.extractCrewUsernames();
        List<String> passwordList = db.extractCrewPasswords();
        
        if (usernameList.contains(inputUsername) && passwordList.contains(inputPass)){
            System.out.println("User found");
            return true;
        }
        System.out.println("User not found");
        return false;
    }

}
