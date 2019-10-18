import javax.swing.*;
import java.io.FileNotFoundException;

public class RegistrationViewer {
    public static void main(String[] args) throws FileNotFoundException
    {
        JFrame frame = new RegistrationFrame();
        frame.setTitle("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
