import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int SCREEN_WIDTH = tileSize * maxScreenCol; // 768 pixels
    public final int SCREEN_HEIGHT = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 16;

    //BALL
    int ballWidth = 32;
    int ballHeight = 32;

    int ballX = SCREEN_WIDTH/2 - ballWidth/2;
    int ballY = SCREEN_HEIGHT/2 - ballHeight/2;

    int ballSpeedX = 4;
    int ballSpeedY = 4;

    //PLAYERS
    int paddleWidth = 16;
    int paddleHeight= 64;

    int playerOnePaddleX = 16;
    int playerOnePaddleY = 20;

    int playerTwoPaddleX = SCREEN_WIDTH - 32;
    int playerTwoPaddleY = 40;

    int paddleSpeed = 5;

    // SYSTEM
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            // Pause the game loop for 16 milliseconds to regulate the frame rate
            try {
                Thread.sleep(FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        paddleControl();
        paddleCollision();
        ballSimulation();
    }


    private void ballSimulation() {
        // Update the ball's position based on its current speed in both X and Y directions
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Check for collisions with the screen boundaries
        if (ballX < 0) {
            // Ball hit the left wall, reset its position and change horizontal direction
            ballX = 0;
            ballSpeedX = -ballSpeedX; // Reverse the horizontal direction
        } else if (ballX + ballWidth > SCREEN_WIDTH) {
            // Ball hit the right wall, reset its position and change horizontal direction
            ballX = SCREEN_WIDTH - ballWidth;
            ballSpeedX = -ballSpeedX; // Reverse the horizontal direction
        }

        if (ballY < 0) {
            // Ball hit the top wall, reset its position and change vertical direction
            ballY = 0;
            ballSpeedY = -ballSpeedY; // Reverse the vertical direction
        } else if (ballY + ballHeight > SCREEN_HEIGHT) {
            // Ball hit the bottom wall, reset its position and change vertical direction
            ballY = SCREEN_HEIGHT - ballHeight;
            ballSpeedY = -ballSpeedY; // Reverse the vertical direction
        }

        // You can add more collision detection logic here, such as collision with paddles.
    }



    private void paddleCollision() {

        //PLAYER ONE
        if (playerOnePaddleY <= 0) {
            playerOnePaddleY = 0;
        }
        if (playerOnePaddleY >= SCREEN_HEIGHT - paddleHeight) {
            playerOnePaddleY = SCREEN_HEIGHT - paddleHeight;
        }

        //PLAYER TWO
        if (playerTwoPaddleY <= 0) {
            playerTwoPaddleY = 0;
        }
        if (playerTwoPaddleY >= SCREEN_HEIGHT - paddleHeight) {
            playerTwoPaddleY = SCREEN_HEIGHT - paddleHeight;
        }

    }


    private void paddleControl() {

        //PLAYER ONE
        if (keyH.upPressedOne) {
            playerOnePaddleY -= paddleSpeed;
        }
        if (keyH.downPressedOne) {
            playerOnePaddleY += paddleSpeed;
        }

        //PLAYER TWO
        if (keyH.upPressedTwo) {
            playerTwoPaddleY -= paddleSpeed;
        }
        if (keyH.downPressedTwo) {
            playerTwoPaddleY += paddleSpeed;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.WHITE);
        paddleDrawing(g2D);
        boardDrawing(g2D);
        ballDrawing(g2D);
        g2D.dispose();
    }

    private void ballDrawing(Graphics2D g2D) {
        g2D.fillOval(ballX, ballY, ballWidth, ballHeight);
    }


    private void boardDrawing(Graphics2D g2D) {

        g2D.drawOval(SCREEN_WIDTH/2 - 128, SCREEN_HEIGHT/2 -128, 256, 256);

        for (int i = 4; i < SCREEN_HEIGHT; i += 16) {
            g2D.fillRect(SCREEN_WIDTH/2, i, 4, 8);
        }

    }

    private void paddleDrawing(Graphics2D g2D) {

        //PLAYER ONE
        g2D.fillRect(playerOnePaddleX, playerOnePaddleY, paddleWidth, paddleHeight);

        //PLAYER TWO
        g2D.fillRect(playerTwoPaddleX, playerTwoPaddleY, paddleWidth, paddleHeight);

    }
}