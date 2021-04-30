import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradingPlatformGUI extends JFrame
{
    public TradingPlatformGUI(){
        super("Electronic Asset Trading Platform");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800, 600);
        setLocation(screenSize.width/2 - 400, screenSize.height/2 - 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
