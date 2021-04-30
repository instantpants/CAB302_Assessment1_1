package Common;

import java.sql.*;


public class Database
{
    public static String URL = "jdbc:sqlite:TradingPlatform.db";
    public static String GetUserQuery = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";
    public static String GetListingsQuery = "SELECT * FROM listings";
}