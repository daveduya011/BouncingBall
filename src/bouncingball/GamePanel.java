/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncingball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ANTHONYDALEJORDAN
 */
public class GamePanel extends JPanel {

    Paddle paddle;
    Ball ball;

    public static boolean isRunning;
    public boolean isPaused;

    public int score = 0;
    public int highScore = 0;
    
    public boolean newHighScore;

    private BufferedImage gameBg;

    public static final int WIDTH = 960;
    public static final int HEIGHT = 560;
    private float interpolation;

    public GamePanel() {
        loadImages();
        this.setSize(WIDTH, HEIGHT);

        JFrame.getFrames()[0].addKeyListener(new TAdapter());
        JFrame.getFrames()[0].addMouseMotionListener(new MListener());
        
        
        ball = new Ball();
        paddle = new Paddle();
        isPaused = true;
        isRunning = true;
        
        //loads avatar every frame show
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                ball.loadAvatar();
            }
        });
    }

    public void update() {
        if (!isPaused) {
            
            paddle.update();
            ball.update();

            if (ball.getBallX() >= paddle.getPaddleX() && ball.getBallX() <= paddle.getPaddleX() + paddle.getWIDTH()
                    && ball.getBallY() + ball.getHEIGHT() >= paddle.getPaddleY() && ball.getBallY() + ball.getHEIGHT() <= paddle.getPaddleY() + paddle.getHEIGHT() + 50) {
                ball.bounceFromPaddle();
                score++;
                System.out.println("Score: " + score);
            }

            if (ball.getBallY() >= HEIGHT) {
                gameOver();
            
            
            }
        }

    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);
        rh.add(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));

        g2.setRenderingHints(rh);

        //Background image
        g2.drawImage(gameBg, 0, 0, this);

        //paddle
        paddle.draw(g2, this);
        //ball
        ball.draw(g2, this);
        
        
        if (isPaused){
            g2.setFont(new Font("Trench Thin", 0, 50));
            g2.setColor(Color.WHITE);
            g2.drawString("Press Enter to Start", WIDTH/2 - 190, HEIGHT/2);
        }
        
        //FOR SCORE
        g2.setFont(new Font("Trench Thin", 0, 40));
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + score, 20, 100);
        
        //for Name
        g2.drawString(NameEntry.name, 20, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        doDrawing(g);
    }

    //load all images
    private void loadImages() {
        gameBg = loadImage("gameBg");
    }

    //loads images
    public BufferedImage loadImage(String fileName) {
        BufferedImage img;

        try {
            img = ImageIO.read(getClass().getResource("/images/" + fileName + ".png"));
            return img;
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

    //Mouse
    class MListener extends MouseMotionAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            int x = e.getX();
            paddle.setPaddleX(x - paddle.getWIDTH() / 2);
        }

    }

    //KEYBOARD
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (isRunning) {

                if (key == KeyEvent.VK_LEFT) {
                    paddle.moveLeft();
                    System.out.println("LEFT");
                }

                if (key == KeyEvent.VK_RIGHT) {
                    paddle.moveRight();
                    System.out.println("RIGHT");
                }
                
                if (isPaused) {
                    if (key == KeyEvent.VK_ENTER) {
                        isPaused = false;
                    }
                }

            }
            
            if (key == KeyEvent.VK_SPACE){
                System.out.println("SPACE");
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (isRunning) {

                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                    paddle.stop();
                }
            }

        }

    }

    public void gameOver() {
        ball.reset();
        
        if (score > highScore){
            highScore = score;
            newHighScore = true;
        } else {
            newHighScore = false;
        }
        isRunning = false;
        isPaused = true;
        
    }
    public void resetScore(){
        
        score = 0;
    }

}
