import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerConnection
{
    public static void main(String[] args){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:TradingPlatform.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("drop table if exists users");
            statement.executeUpdate("drop table if exists ou");
            statement.executeUpdate("drop table if exists assets");
            statement.executeUpdate("drop table if exists listings");
            statement.executeUpdate("drop table if exists trade_history");

            statement.executeUpdate("CREATE TABLE users (" +
                    "username STRING NOT NULL PRIMARY KEY, " +
                    "password STRING NOT NULL, " +
                    "account_type INT NOT NULL, " +
                    "ou_name STRING NOT NULL" +
                    ")"
            );

            statement.executeUpdate("CREATE TABLE ou (" +
                    "ou_name STRING NOT NULL PRIMARY KEY" +
                    "credits INT NOT NULL)"
            );

            statement.executeUpdate("CREATE TABLE assets (" +
                    "asset_name STRING NOT NULL, " +
                    "ou_name STRING NOT NULL," +
                    "quantity INT NOT NULL" +
                    "" +
                    "PRIMARY KEY(asset_name, ou_name)"
            );

            statement.executeUpdate("CREATE TABLE listings (" +
                    "id INT NOT NULL IDENTITY PRIMARY KEY, " +
                    "ou_name STRING NOT NULL, " +
                    "date_added DATE NOT NULL" +
                    "asset_name STRING NOT NULL" +
                    "quantity INT NOT NULL" +
                    "price INT NOT NULL" +
                    "listing_type BIT NOT NULL)"
            );

            statement.executeUpdate("CREATE TABLE trade_history (" +
                    "invoice_number INT NOT NULL IDENTITY PRIMARY KEY, " +
                    "ou_name STRING NOT NULL" +
                    "date_sold DATE NOT NULL" +
                    "asset_name STRING NOT NULL" +
                    "quantity INT NOT NULL" +
                    "price INT NOT NULL" +
                    "listing_type BIT NOT NULL)");

            statement.executeUpdate("INSERT INTO users VALUES('root', 'pass', 0, 'IT')");
            statement.executeUpdate("INSERT INTO users VALUES('user', 'pass', 1, 'IT')");
            statement.executeUpdate("INSERT INTO users VALUES('joe', 'blogs', 1, 'IT')");

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
