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
public class Paddle {
    private BufferedImage iconPaddle;
    
    public Rectangle paddleRect;
    
    private int paddleX;
    private int paddleXSpeed;
    private int paddleY;
    
    private final int WIDTH = 144;
    private final int HEIGHT = 17;
    
    private final int SPEED = 10;

    public Paddle() {
        iconPaddle = loadImage("iconPaddle");
        paddleX = GamePanel.WIDTH/2 - WIDTH/2;
        paddleY = GamePanel.HEIGHT - 50;
        
        paddleRect = new Rectangle();
    }
    
    public void draw(Graphics g, GamePanel observer){
        g.drawImage(iconPaddle, paddleX, paddleY, observer);
    }
    
    public void update(){
        paddleX += paddleXSpeed;
        
        //BORDERS
        if (paddleX < 0){
            paddleX = 0;
        }
        if (paddleX > GamePanel.WIDTH - WIDTH){
            paddleX = GamePanel.WIDTH - WIDTH;
        }
        
        paddleRect.setBounds(paddleX, paddleY, WIDTH, HEIGHT);
    }
    
    public void moveRight(){
        paddleXSpeed = SPEED;
    }
    public void moveLeft(){
        paddleXSpeed = -SPEED;
    }
    
    public void stop(){
        paddleXSpeed = 0;
    }

    public void setPaddleX(int paddleX) {
        this.paddleX = paddleX;
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

    public int getPaddleX() {
        return paddleX;
    }

    public int getPaddleY() {
        return paddleY;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
    
    
}
