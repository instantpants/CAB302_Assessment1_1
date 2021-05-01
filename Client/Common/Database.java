package Common;

import java.sql.*;


public class Database
{
    public static String URL = "jdbc:sqlite:TradingPlatform.db";
    public static String GetUserQuery = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
    public static String GetListingsQuery = "SELECT * FROM listings";

    public static ResultSet PrepareStatement(Connection c, String preparedStatement, String[] args) throws SQLException{
        PreparedStatement p = c.prepareStatement(preparedStatement,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        for (int i = 0; i < args.length; i++) {
            p.setString(i+1, args[i]);
        }
        return p.executeQuery();
    }

    public static ResultSet DoLogin(String username, String password) {
        try (Connection connection = DriverManager.getConnection(Database.URL))
        {
            ResultSet user = PrepareStatement(connection, GetUserQuery, new String[]{ username, password} );

            // Check user is valid
            while (user.next()) {
                Boolean usernameMatches = username.equals(user.getString("username"));
                Boolean passwordMatches = password.equals(user.getString("password"));
                if (usernameMatches && passwordMatches)
                    return user; // Successful Login
                else
                    System.out.println("Invalid Username and or Password");
            }
        }
        catch (SQLException e) {
            System.out.println("DoLogin: " + e.getMessage());
        }
        return null; // Failed Login
    }
}