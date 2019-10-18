//import statement
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//create class and extend with JFrame
public class FourthWindow extends JFrame
{
    //declare variable
    private JPanel contentPane;
    /**
     * Launch the application.
     */
    //main method
    public static void main(String[] args)
    {
		/* It posts an event (Runnable)at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                //try - catch block
                try
                {
                    //Create object of FourthWindow
                    String username = "prova";
                    FourthWindow frame = new FourthWindow(username);
                    //set frame visible true
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FourthWindow(String username) //constructor
    {
        //set frame title
        ArrayList<TimeKeys> pressReleaseTime = new ArrayList<>();

        Date date = new Date();
        long time = date.getTime();

        Clock clock = Clock.systemDefaultZone();
        //set frame title
        setTitle("Old Frame");
        //set default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //set bounds of the frame
        setBounds(100, 100, 450, 300);
        //create object of JPanel
        contentPane = new JPanel();
        //set border
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //set ContentPane
        setContentPane(contentPane);
        //set null
        contentPane.setLayout(null);

        JTextField nameTextField = new JTextField();
        nameTextField.setSize(new Dimension(180, 195));


        KeyListener keyListener = new KeyListener() {
            Long pressedTime;
            Long releasedTime;
            char keyChar;

            public void keyPressed(KeyEvent keyEvent) {
                pressedTime = clock.millis();
                //printIt("Pressed", keyEvent);
            }

            public void keyReleased(KeyEvent keyEvent) {
                releasedTime = clock.millis();
                pressReleaseTime.add( new TimeKeys(pressedTime, releasedTime, keyChar));
                printIt("Released", keyEvent);
            }

            public void keyTyped(KeyEvent keyEvent) {
                keyChar = keyEvent.getKeyChar();
                printIt("Typed", keyEvent);
            }

            private void printIt(String title, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                System.out.println(title + " : " + keyText + " / " + keyEvent.getKeyChar());
            }

        };
        nameTextField.addKeyListener(keyListener);
        nameTextField.setBounds(100, 150, 239, 39);
        //contentPane.addKeyListener(keyListener);
        contentPane.add(nameTextField, BorderLayout.CENTER);


        //create object of JButton and set label on it
        JButton btnNewFrame = new JButton("Avanti");
        //add actionListener
        btnNewFrame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //call the object of FourthWindow and set visible true
                dispose();
                try {
                    FirstWindow w1 = new FirstWindow(username);
                    w1.csvGenerator(pressReleaseTime, String.valueOf(time), true, username);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FourthWindow frame = new FourthWindow(username);
                frame.setVisible(true);
                //set default close operation
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        //set font of the Button
        btnNewFrame.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        //set bounds of the Button
        btnNewFrame.setBounds(180, 195, 78, 25);
        //add Button into contentPane
        contentPane.add(btnNewFrame);

        //set Label in the frame
        JLabel lblThisIsOld = new JLabel("4");
        lblThisIsOld.setForeground(Color.BLUE);
        lblThisIsOld.setBounds(90, 82, 290, 39);
        JLabel lblThisIsOld1 = new JLabel("");
        lblThisIsOld1.setForeground(Color.BLUE);
        lblThisIsOld1.setBounds(90, 100, 290, 39);
        //add label to the contentPane
        contentPane.add(lblThisIsOld);
        contentPane.add(lblThisIsOld1);
    }
}