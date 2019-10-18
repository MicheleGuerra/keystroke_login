import javax.swing.JFrame;
import java.io.*;

public class LoginViewer
{
    public static void main(String[] args) throws FileNotFoundException
    {
        JFrame frame = new LoginFrame();
        frame.setTitle("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}