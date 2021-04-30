import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TradingPlatformGUI extends JFrame
{
    JPanel pnlTable;
    JPanel pnlNav;
    JButton btnRefresh;
    JTable tblListings;


    String[] columnNames = { "OU", "Asset Name", "Quantity", "Price", "Listing Type", "Date Added"};

    public TradingPlatformGUI(){
        super("Electronic Asset Trading Platform");

        pnlTable = new JPanel();
        btnRefresh = new JButton("Refresh");
        tblListings = new JTable();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLocation(screenSize.width/2 - 400, screenSize.height/2 - 300);
        pnlTable.setLayout(null);

        getContentPane().add(pnlTable);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        btnRefresh.addActionListener(e -> {
            PopulateListings();
        });
    }

    public void PopulateListings(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:TradingPlatform.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet listings = statement.executeQuery("SELECT * FROM listings");

            while (listings.next()) {

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
