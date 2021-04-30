import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.sql.*;

class LoginGUI extends JFrame
{

    JButton btnLogin;
    JPanel pnlLogin;
    JTextField txtUsername;
    JTextField txtPassword;
    JLabel lblUsername;
    JLabel lblPassword;

    public LoginGUI()
    {
        super("Electronic Asset Trading Platform - Login");

        pnlLogin = new JPanel();
        btnLogin = new JButton("Login");

        txtUsername = new JTextField(15);
        txtPassword = new JTextField(15);

        lblUsername = new JLabel("Username ");
        lblPassword = new JLabel("Password ");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(300, 200);
        setLocation(screenSize.width/2 - 150, screenSize.height/2 - 100);
        pnlLogin.setLayout(null);

        txtUsername.setBounds(80, 30, 150, 20);
        txtPassword.setBounds(80, 65, 150, 20);
        btnLogin.setBounds(110, 100, 80, 20);
        lblUsername.setBounds(20, 28, 80, 20);
        lblPassword.setBounds(20, 63, 80, 20);

        pnlLogin.add(btnLogin);
        pnlLogin.add(txtUsername);
        pnlLogin.add(txtPassword);
        pnlLogin.add(lblUsername);
        pnlLogin.add(lblPassword);

        getContentPane().add(pnlLogin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        btnLogin.addActionListener(event -> {
            String user = txtUsername.getText();
            String pass = txtPassword.getText();
            Connection connection = null;

            System.out.println(user + " " + pass);

            try {
                connection = DriverManager.getConnection("jdbc:sqlite:TradingPlatform.db");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                PreparedStatement getUser = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                getUser.setString(1, user);
                getUser.setString(2, pass);
                ResultSet res = getUser.executeQuery();

                while (res.next()){
                    System.out.println(res.getString("username"));
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
        });
    }

    public static void main(String[] args){
        LoginGUI loginGui = new LoginGUI();
    }
}