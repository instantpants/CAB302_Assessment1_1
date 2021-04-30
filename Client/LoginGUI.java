import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setSize(300, 200);
        setLocation(500, 280);
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

        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            String pass = txtPassword.getText();

            /*
            ''Database query here''

            if (user exists in database){
                MainMenu mainMenu = new MainMenu();
            }else{
                JOptionPane.showMessageDialog(null, "Wrong Username and or Password");
            }
            */
        });
    }
}