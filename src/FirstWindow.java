//import statement

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//create class and extend with JFrame
public class FirstWindow extends JFrame {
    //declare variable
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    //main method
    public static void main(String[] args) {
		/* It posts an event (Runnable)at the end of Swings event list and is
		processed after all other GUI events are processed.*/
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //try - catch block
                try {
//                    //Create object of FirstWindow
//                    FirstWindow frame = new FirstWindow();
//                    //set frame visible true
//                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FirstWindow(String username)//constructor
    {

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
                pressReleaseTime.add(new TimeKeys(pressedTime, releasedTime, keyChar));
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
        btnNewFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //call the object of FourthWindow and set visible true
                try {
                    csvGenerator(pressReleaseTime, String.valueOf(time), false, username);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dispose();
                SecondWindow frame = new SecondWindow(username);
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
        JLabel lblThisIsOld = new JLabel("il termine bug è legato ad un curioso aneddoto");
        lblThisIsOld.setForeground(Color.BLUE);
        lblThisIsOld.setBounds(90, 82, 290, 39);
        JLabel lblThisIsOld1 = new JLabel("risalente ai tempi pionieristici dell'informatica");
        lblThisIsOld1.setForeground(Color.BLUE);
        lblThisIsOld1.setBounds(90, 100, 290, 39);
        //add label to the contentPane
        contentPane.add(lblThisIsOld);
        contentPane.add(lblThisIsOld1);
    }

    public int ikiGenerator(long pressed, long released) {

        int solution = (int) (released - pressed);
        return solution;
    }

    public static double calculateSD(ArrayList<Integer> numArray) {
        new DecimalFormat("#,#", new DecimalFormatSymbols(Locale.US));
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();
        for (double num : numArray) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }

    public void csvGenerator(ArrayList<TimeKeys> timeKeys, String timestamp, Boolean window, String username) throws IOException {
        File f = new File(username + ".csv");
        if(f.exists()) {
            /* show alert */
            //username = username + timestamp;
        }
        FileWriter csvWriter = new FileWriter(username + ".csv", window);
        if (!window) {
            csvWriter.append("IKI");
            csvWriter.append(",");
            csvWriter.append("IKI_2");
            csvWriter.append(",");
            csvWriter.append("IKI_3");
            csvWriter.append(",");
            csvWriter.append("SD_IKI");
            csvWriter.append(",");
            csvWriter.append("USER_ID");
            csvWriter.append(",");
            csvWriter.append("SENTENCE_ID");
            //csvWriter.append(",");
            csvWriter.append("\n");
        }

        //sd iki
        ArrayList<Integer> sdIKI = new ArrayList<>();
//        for (int i = 0; i < timeKeys.size(); i++) {
        for (int i = 0; i < 34; i++) {
            if ((i == 0) || (i == 1) || (i == 2) || (i == 3)) {

            } else {
                sdIKI.add(ikiGenerator(timeKeys.get(i - 1).getPressedTime(), timeKeys.get(i).getReleasedTime()));
            }
        }

//        for (int i = 0; i < timeKeys.size(); i++) {
        for (int i = 0; i < 34; i++) {
            if ((i == 0) || (i == 1) || (i == 2) || (i == 3)) {

            } else {
                int iki = ikiGenerator(timeKeys.get(i - 1).getPressedTime(), timeKeys.get(i).getReleasedTime());
                csvWriter.append(String.join(",", String.valueOf(iki)));
                csvWriter.append(",");
                iki = ikiGenerator(timeKeys.get(i - 2).getPressedTime(), timeKeys.get(i).getReleasedTime());
                csvWriter.append(String.join(",", String.valueOf(iki)));
                csvWriter.append(",");
                iki = ikiGenerator(timeKeys.get(i - 3).getPressedTime(), timeKeys.get(i).getReleasedTime());
                csvWriter.append(String.join(",", String.valueOf(iki)));
                csvWriter.append(",");
                csvWriter.append(String.join(",", String.valueOf(calculateSD(sdIKI))));
//                Math.round(calculateSD(sdIKI), 2);
                csvWriter.append(",");

                csvWriter.append(String.join(",", username));
                csvWriter.append(",");

                csvWriter.append(String.join(",", "session_" + timestamp));
//                csvWriter.append(String.join(",", timestamp));

                csvWriter.append("\n");
            }

        }

        csvWriter.flush();
        csvWriter.close();
    }
}
