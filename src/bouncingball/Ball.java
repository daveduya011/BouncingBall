/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncingball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ANTHONYDALEJORDAN
 */
public class Ball {
    
    private BufferedImage iconBall1;
    private BufferedImage iconBall2;
    private BufferedImage iconBall3;
    private BufferedImage iconBall4;
    
    private BufferedImage currentBall;
    
    private int ballX;
    private int ballXSpeed;
    private int ballY;
    private int ballYSpeed;
    
    public Rectangle ballRect;
    
    private final int WIDTH = 49;
    private final int HEIGHT = 49;
    
    public int SPEED = 20;
    

    public Ball() {
        SPEED = 5;
        
        iconBall1 = loadImage("iconBall1");
        iconBall2 = loadImage("iconBall2");
        iconBall3 = loadImage("iconBall3");
        iconBall4 = loadImage("iconBall4");
        ballX = 210;
        ballY = 240;
        ballXSpeed = SPEED;
        ballYSpeed = SPEED;
        
        ballRect = new Rectangle();
        loadAvatar();
    }
    public void draw(Graphics g, GamePanel observer){
        g.drawImage(currentBall, ballX, ballY, observer);
    }
    public void update(){
        checkCollision();
        ballX += ballXSpeed;
        ballY += ballYSpeed;
        
        ballRect.setBounds(ballX, ballY, WIDTH, HEIGHT);
    }
    
    public void loadAvatar(){
        
        switch (MainMenu.iconChoice){
            case 0: currentBall = iconBall1; break;
            case 1: currentBall = iconBall2; break;
            case 2: currentBall = iconBall3; break;
            case 3: currentBall = iconBall4; break;
        }
    }
    
    public void checkCollision(){
        
        //LEFT SCREEN
        if (ballX <= 0){
            ballXSpeed = SPEED;
        }
        //Right SCREEN
        if (ballX + WIDTH >= GamePanel.WIDTH){
            ballXSpeed = -SPEED;
        }
        //Top Screen
        if (ballY <= 0){
            ballYSpeed = SPEED;
            int r = (int)(Math.random() * (SPEED - 1));
            ballXSpeed = r - SPEED/2;
            SPEED++;
        }
    }
    
    public void bounceFromPaddle(){
        ballYSpeed = -SPEED;
    }
    
    //loads images
    private BufferedImage loadImage(String fileName){
        BufferedImage img;
        
        try {
            img = ImageIO.read(getClass().getResource("/images/" + fileName + ".png"));
            return img;
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }

    public int getBallX() {
        return ballX + WIDTH/2;
    }

    public int getBallY() {
        return ballY;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void reset(){
        ballY = 0;
    }
    
    
    
}
