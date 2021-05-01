import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.sql.*;
import Common.Database;

public class LoginGUI extends JFrame
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

        btnLogin.addActionListener(e -> DoLogin());
    }

    public void DoLogin(){
        String username = txtUsername.getText();
        String password = txtPassword.getText(); // < - ENCRYPT THIS BWOIIIII
        ResultSet user = Database.DoLogin(username, password);
        System.out.println(user);
        if (user != null){
            new TradingPlatformGUI(user);
            this.dispose();
        }
    }

    public void oDoLogin(){
        String username = txtUsername.getText();
        String password = txtPassword.getText(); // < - ENCRYPT THIS BWOIIIII

        try (Connection connection = DriverManager.getConnection(Database.URL)) {
            PreparedStatement getUser = connection.prepareStatement(Database.GetUserQuery);
            getUser.setString(1, username);
            getUser.setString(2, password);
            ResultSet user = getUser.executeQuery();

            // Check user is valid
            while (user.next()) {
                Boolean usernameMatches = username.equals(user.getString("username"));
                Boolean passwordMatches = password.equals(user.getString("password"));
                if (usernameMatches && passwordMatches)
                {
                    new TradingPlatformGUI(user);
                    this.dispose(); // Closes the LoginGUI
                }
                else
                    System.out.println("Invalid Username and or Password");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        LoginGUI loginGui = new LoginGUI();
    }
}