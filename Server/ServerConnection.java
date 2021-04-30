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

            statement.executeUpdate("create table users (" +
                    "username STRING NOT NULL PRIMARY KEY, " +
                    "password STRING NOT NULL, " +
                    "account_type INT NOT NULL, " +
                    "ou_name STRING NOT NULL" +
                    ")"
            );

            statement.executeUpdate("create table ou (" +
                    "ou_name STRING NOT NULL PRIMARY KEY" +
                    "credits INT NOT NULL)"
            );

            statement.executeUpdate("create table assets (" +
                    "asset_name STRING NOT NULL, " +
                    "ou_name STRING NOT NULL," +
                    "quantity INT NOT NULL" +
                    "" +
                    "PRIMARY KEY(asset_name, ou_name)"
            );

            statement.executeUpdate("create table listings (" +
                    "id INT NOT NULL IDENTITY PRIMARY KEY, " +
                    "ou_name STRING NOT NULL, " +
                    "date_added DATE NOT NULL" +
                    "asset_name STRING NOT NULL" +
                    "quantity INT NOT NULL" +
                    "price INT NOT NULL" +
                    "listing_type BIT NOT NULL)"
            );

            statement.executeUpdate("create table trade_history (" +
                    "invoice_number INT NOT NULL IDENTITY PRIMARY KEY, " +
                    "ou_name STRING NOT NULL" +
                    "date_sold DATE NOT NULL" +
                    "asset_name STRING NOT NULL" +
                    "quantity INT NOT NULL" +
                    "price INT NOT NULL" +
                    "listing_type BIT NOT NULL)");

            statement.executeUpdate("insert into users values('root', 'pass', 0, 'IT')");
            statement.executeUpdate("insert into users values('user', 'pass', 1, 'IT')");
            statement.executeUpdate("insert into users values('joe', 'blogs', 1, 'IT')");

            ResultSet rs = statement.executeQuery("select * from users");

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
