import Common.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TradingPlatformGUI extends JFrame
{
    JPanel pnlTable;
    JPanel pnlNav;
    JButton btnRefresh;
    JScrollPane scpListings;
    JTable tblListings;

    String[] columnNames = { "OU", "Asset Name", "Quantity", "Price", "Listing Type", "Date Added"};

    public TradingPlatformGUI(ResultSet user){
        super("Electronic Asset Trading Platform");

        pnlTable = new JPanel();
        btnRefresh = new JButton("Refresh");
        tblListings = new JTable();
        scpListings = new JScrollPane(tblListings);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLocation(screenSize.width/2 - 400, screenSize.height/2 - 300);
        pnlTable.setLayout(null);

        tblListings.setBounds(20,20,700,500);

        scpListings.setViewportView(tblListings);
        pnlTable.add(tblListings);

        getContentPane().add(pnlTable);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        PopulateListings();

        btnRefresh.addActionListener(e -> PopulateListings());
    }

    public void PopulateListings(){
        try (Connection connection = DriverManager.getConnection(Database.URL)) {
            PreparedStatement getUser = connection.prepareStatement(Database.GetListingsQuery);
            ResultSet rs = getUser.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            for(String col:columnNames){
                model.addColumn(col);
            }

            // Loop listings
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("ou_name"),
                        rs.getString("asset_name"),
                        rs.getString("quantity"),
                        rs.getString("price"),
                        rs.getString("listing_type"),
                        rs.getString("date_added")
                });
            }

            tblListings.setModel(model);
            setVisible(true);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
