package Common;

import java.sql.*;


public class Database
{
    public static String URL = "jdbc:sqlite:TradingPlatform.db";
    public static String GetUserQuery = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
    public static String GetListingsQuery = "SELECT * FROM listings";

    public ResultSet ExecuteQuery(String preparedStatement){
        try (Connection c = DriverManager.getConnection(Database.URL)){
            PreparedStatement p = c.prepareStatement(preparedStatement);
            return p.executeQuery();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet DoLogin(String username, String password) {


        return ExecuteQuery(GetUserQuery);
    }


}