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

            // DROP EXISTING TABLES
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("DROP TABLE IF EXISTS ou");
            statement.executeUpdate("DROP TABLE IF EXISTS assets");
            statement.executeUpdate("DROP TABLE IF EXISTS asset_list");
            statement.executeUpdate("DROP TABLE IF EXISTS listings");
            statement.executeUpdate("DROP TABLE IF EXISTS trade_history");

            // CREATE TABLES
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

            statement.executeUpdate("CREATE TABLE asset_list (" +
                    "asset_name     TEXT    NOT NULL    PRIMARY KEY" +
                    ");"
            );

            statement.executeUpdate("CREATE TABLE assets (" +
                    "asset_name     TEXT    NOT NULL, " +
                    "ou_name        TEXT    NOT NULL," +
                    "quantity       INT     NOT NULL, " +
                    "" +
                    "PRIMARY KEY(asset_name, ou_name)" +
                    "FOREIGN KEY(asset_name) REFERENCES asset_list(asset_name)" +
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
                    ");"
            );

            statement.executeUpdate("CREATE TABLE trade_history (" +
                    "id             INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "ou_name        TEXT    NOT NULL, " +
                    "asset_name     TEXT    NOT NULL, " +
                    "quantity       INT     NOT NULL, " +
                    "price          INT     NOT NULL, " +
                    "listing_type   BIT     NOT NULL, " +
                    "date_sold      TEXT    NOT NULL " +
                    ");"
            );

            // DUMMY INSERTIONS
            statement.executeUpdate("INSERT INTO users VALUES('root', 'pass', 0, 'IT');");
            statement.executeUpdate("INSERT INTO users VALUES('user', 'pass', 1, 'IT');");
            statement.executeUpdate("INSERT INTO users VALUES('joe', 'blogs', 1, 'IT');");

            statement.executeUpdate("INSERT INTO ou VALUES('IT Team', 1000);");
            statement.executeUpdate("INSERT INTO ou VALUES('Administration', 1000);");
            statement.executeUpdate("INSERT INTO ou VALUES('Management', 1000);");
            statement.executeUpdate("INSERT INTO ou VALUES('Support Staff', 1000);");
            statement.executeUpdate("INSERT INTO ou VALUES('Compute Cluster', 1000);");

            statement.executeUpdate("INSERT INTO asset_list VALUES('Logitech Mouse')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Dell Computer Tower')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Asus LCD Monitor')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Cluster CPU Hour')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Cluster GPU Hour')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Microsoft Windows Licence')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Adobe Photoshop Licence')");
            statement.executeUpdate("INSERT INTO asset_list VALUES('Autodesk Pro Licence')");

            statement.executeUpdate("INSERT INTO assets VALUES('Logitech Mouse', 'IT Team', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Dell Computer Tower', 'IT Team', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Asus LCD Monitor', 'IT Team', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Cluster CPU Hour', 'Compute Cluster', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Cluster GPU Hour', 'Compute Cluster', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Microsoft Windows Licence', 'Management', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Microsoft Windows Licence', 'Support Staff', 10);");
            statement.executeUpdate("INSERT INTO assets VALUES('Adobe Photoshop Licence', 'Support Staff', 10);");

            statement.executeUpdate("INSERT INTO listings VALUES(null, 'IT Team', 'Logitech Mouse', 5, 10, 1, '2021-04-30 10:30:00.000');");
            statement.executeUpdate("INSERT INTO listings VALUES(null, 'Compute Cluster', 'Cluster CPU Hour', 10, 1, 1, '2021-05-04 12:45:00.000');");
            statement.executeUpdate("INSERT INTO listings VALUES(null, 'Support Staff', 'Microsoft Windows Licence', 2, 25, 0, '2021-04-27 08:00:00.000');");

            statement.executeUpdate("INSERT INTO trade_history VALUES(null, 'Support Staff', 'Microsoft Windows Licence', 5, 25, 0, '2021-04-20 08:00:00.000');");

            // PRINT ALL USERS
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
}
