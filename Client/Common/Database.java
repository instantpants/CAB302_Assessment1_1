package Common;

import java.sql.*;


public class Database
{
    public static String URL = "jdbc:sqlite:TradingPlatform.db";
    public static String GetUserQuery = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
    public static String GetListingsQuery = "SELECT * FROM listings";

    public ResultSet ExecuteQuery(String preparedStatement, String[] args){
        try (Connection c = DriverManager.getConnection(Database.URL)){
            PreparedStatement p = c.prepareStatement(preparedStatement);
            for (int i = 0; i < args.length; i++) {
                p.setString(i+1, args[i]);
            }
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