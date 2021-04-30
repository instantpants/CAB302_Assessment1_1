import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerConnection
{
    public enum AccountType{
        ADMIN,
        USER
    }
    public enum ListingType{
        BUY,
        SELL
    }

    public static void main(String[] args){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:TradingPlatform.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("DROP TABLE IF EXISTS ou");
            statement.executeUpdate("DROP TABLE IF EXISTS assets");
            statement.executeUpdate("DROP TABLE IF EXISTS listings");
            statement.executeUpdate("DROP TABLE IF EXISTS trade_history");

            statement.executeUpdate("CREATE TABLE users (" +
                    "username       TEXT    NOT NULL    PRIMARY KEY, " +
                    "password       TEXT    NOT NULL, " +
                    "account_type   INT     NOT NULL, " +
                    "ou_name        STRING  NOT NULL" +
                    ");"
            );

            statement.executeUpdate("CREATE TABLE ou (" +
                    "ou_name        TEXT    NOT NULL    PRIMARY KEY, " +
                    "credits        INT     NOT NULL" +
                    ");"
            );

            statement.executeUpdate("CREATE TABLE assets (" +
                    "asset_name     TEXT    NOT NULL, " +
                    "ou_name        TEXT    NOT NULL," +
                    "quantity       INT     NOT NULL, " +
                    "" +
                    "PRIMARY KEY(asset_name, ou_name)" +
                    ");"
            );

            statement.executeUpdate("CREATE TABLE listings (" +
                    "id             INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "ou_name        TEXT    NOT NULL, " +
                    "asset_name     TEXT    NOT NULL, " +
                    "quantity       INT     NOT NULL, " +
                    "price          INT     NOT NULL, " +
                    "listing_type   BIT     NOT NULL, " +
                    "date_added     TEXT    NOT NULL" +
                    //"PRIMARY KEY(_id)" +
                    ");"
            );

            statement.executeUpdate("CREATE TABLE trade_history (" +
                    "id             INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "ou_name        TEXT    NOT NULL, " +
                    "date_sold      TEXT    NOT NULL, " +
                    "asset_name     TEXT    NOT NULL, " +
                    "quantity       INT     NOT NULL, " +
                    "price          INT     NOT NULL, " +
                    "listing_type   BIT     NOT NULL" +
                    //"PRIMARY KEY(_id)" +
                    ");"
            );

            statement.executeUpdate("INSERT INTO users VALUES('root', 'pass', 0, 'IT')");
            statement.executeUpdate("INSERT INTO users VALUES('user', 'pass', 1, 'IT')");
            statement.executeUpdate("INSERT INTO users VALUES('joe', 'blogs', 1, 'IT')");

            statement.executeUpdate("INSERT INTO ou VALUES('IT Team', 1000)");
            statement.executeUpdate("INSERT INTO ou VALUES('Administration', 1000)");
            statement.executeUpdate("INSERT INTO ou VALUES('Management', 1000)");
            statement.executeUpdate("INSERT INTO ou VALUES('Support Staff', 1000)");

            statement.executeUpdate("INSERT INTO assets VALUES('Logitech Mouse', 'IT Team', 10)");
            statement.executeUpdate("INSERT INTO assets VALUES('Dell Computer Tower', 'IT Team', 10)");
            statement.executeUpdate("INSERT INTO assets VALUES('Asus LCD Monitor', 'IT Team', 10)");

            statement.executeUpdate("INSERT INTO listings VALUES(null, 'IT Team', 'Logitech Mouse', 5, 10, 1, '2021-04-30 10:30:00.000')");

            ResultSet rs = statement.executeQuery("SELECT * FROM users");

            while(rs.next()){
                System.out.print("name: " + rs.getString("username") + "\t");
                System.out.println("pass: " + rs.getString("password"));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally{
            try {
                if (connection != null)
                    connection.close();
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public ResultSet QueryDatabase(String query){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:TradingPlatform.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            return statement.executeQuery(query);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally{
            try {
                if (connection != null)
                    connection.close();
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
