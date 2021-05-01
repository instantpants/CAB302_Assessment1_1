package Common;

import java.sql.*;


public class Database
{
    public static String URL = "jdbc:sqlite:TradingPlatform.db";
    public static String GetUserQuery = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
    public static String GetListingsQuery = "SELECT * FROM listings";
    private static Connection connection;

    public static void init() {
        try {
            connection = DriverManager.getConnection(Database.URL);
        }
        catch (SQLException e) {
            System.out.println("Database: " + e.getMessage());
        }
    }

    public static ResultSet ExecuteStatement(PreparedStatement p, String[] args) throws SQLException{
        for (int i = 0; i < args.length; i++) {
            p.setString(i+1, args[i]);
        }
        return p.executeQuery();
    }

    public static ResultSet DoLogin(String username, String password) {
        if (connection == null) {
            init();
        }
        try {
            ResultSet user = ExecuteStatement(connection.prepareStatement(GetUserQuery), new String[]{ username, password});

            // Check user is valid
            while (user.next()) {
                Boolean usernameMatches = username.equals(user.getString("username"));
                Boolean passwordMatches = password.equals(user.getString("password"));
                if (usernameMatches && passwordMatches) {
                    System.out.println(user);
                    return user; // Successful Login
                }
            }
        }
        catch (SQLException e) {
            System.out.println("DoLogin: " + e.getMessage());
        }
        System.out.println("Invalid Username and or Password");
        return null; // Failed Login
    }
}