import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Clock;
import java.util.ArrayList;

import javax.swing.*;

public class keystrokeLogin extends JFrame{

    private JButton myFirstButton;
    private JButton mySecondButton;

    public keystrokeLogin(JButton myFirstButton, JButton mySecondButton) throws HeadlessException {
    }

    public static void main(String args[]) throws Exception {



        ArrayList<TimeKeys> pressReleaseTime = new ArrayList<>();

        Clock clock = Clock.systemDefaultZone();

        //ArrayList<ArrayList<Long>> pressedRealeasedTime = new ArrayList<ArrayList<Long>>();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField nameTextField = new JTextField();
        frame.add(nameTextField, BorderLayout.NORTH);




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


        frame.setSize(600, 300);
        frame.setVisible(true);
    }
}