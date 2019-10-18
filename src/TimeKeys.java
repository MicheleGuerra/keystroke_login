import java.awt.event.KeyEvent;

public class TimeKeys {
    private long pressedTime;
    private long releasedTime;
    private char keyChar;

    public TimeKeys(long pressedTime, long releasedTime, char keyChar) {
        this.pressedTime = pressedTime;
        this.releasedTime = releasedTime;
        this.keyChar = keyChar;
    }

    public long getPressedTime() {
        return pressedTime;
    }

    public void setPressedTime(long pressedTime) {
        this.pressedTime = pressedTime;
    }

    public long getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(long releasedTime) {
        this.releasedTime = releasedTime;
    }

    public char getKeyChar() {
        return keyChar;
    }

    public void setKeyChar(char keyChar) {
        this.keyChar = keyChar;
    }
}
