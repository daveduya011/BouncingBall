/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncingball;

/**
 *
 * @author ANTHONYDALEJORDAN
 */
public class Main extends GameThread{
    
    MainMenu mainMenu;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        
        setRunning(true);
        runGameLoop();
    }

    @Override
    public void updateGame() {
        if (mainMenu.gamePanel.isRunning == true){
            mainMenu.gamePanel.update();
        } else {
            mainMenu.gamePanel.setVisible(false);
        }
    }
    
    @Override
    public void drawGame(float interpolation) {
        if (mainMenu.gamePanel.isRunning == true){
            
            mainMenu.gamePanel.setInterpolation(interpolation);
            mainMenu.gamePanel.repaint();   
        }
    }

    
    
    
    
}
