import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressedOne, downPressedOne, leftPressedOne, rightPressedOne;
    public boolean upPressedTwo, downPressedTwo, leftPressedTwo, rightPressedTwo;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //PLAYER ONE
        if (code == KeyEvent.VK_W) {
            upPressedOne = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressedOne = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressedOne = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressedOne = true;
        }

        //PLAYER TWO
        if (code == KeyEvent.VK_UP) {
            upPressedTwo = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressedTwo = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressedTwo = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressedTwo = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        //PLAYER ONE
        if (code == KeyEvent.VK_W) {
            upPressedOne = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressedOne = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressedOne = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressedOne = false;
        }

        //PLAYER TWO
        if (code == KeyEvent.VK_UP) {
            upPressedTwo = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressedTwo = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressedTwo = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressedTwo = false;
        }
    }
}