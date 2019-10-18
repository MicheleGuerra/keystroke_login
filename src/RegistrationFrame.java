import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegistrationFrame extends JFrame {
    private static final int FRAME_WIDTH = 250;
    private static final int FRAME_HEIGHT = 200;
    private File userData;
    static JTextField userNameField;
    static JPasswordField passwordField;

    public RegistrationFrame() throws FileNotFoundException {
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createComponents() throws FileNotFoundException {
        userNameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        JLabel userNameLabel = new JLabel("User Name");
        JLabel passwordLabel = new JLabel("Password");

        JButton registrationButton = new JButton("Registation");
        JButton exitButton = new JButton("Exit");

        JPanel panel = new JPanel();
        panel.add(userNameField);
        panel.add(userNameLabel);
        panel.add(passwordField);
        panel.add(passwordLabel);
        panel.add(registrationButton);
        panel.add(exitButton);
        add(panel);

        ActionListener exitListener = new ClickListener1();
        ActionListener registrationListener = new ClickListener2();
        exitButton.addActionListener(exitListener);
        registrationButton.addActionListener(registrationListener);
    }

    public class ClickListener1 implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

    public class ClickListener2 implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            //File inputFile = new File("USERDATA.txt");

            String userNameInput = userNameField.getText();
            String passwordInput = passwordField.getText();

            try {
//                Scanner in = new Scanner(new File("USERDATA.txt"));
                FileWriter fw = new FileWriter("USERDATA.txt",true);
                fw.write(userNameInput + "," + passwordInput + "\n");
                fw.close();
                dispose();
                FirstWindow frame = new FirstWindow("registration_" + userNameInput);
                frame.setVisible(true);

            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "User Database Not Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}