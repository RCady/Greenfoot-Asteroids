import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{

    public static int numberOfProjectiles = 0; //R12

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void gameOver()
    {
    	//((SpaceWorld) getWorld()).gameOver();
    }

    // R3
    public void overEdge()
    {
    	int xPos = getX();
        int yPos = getY();

    	int ww = getWorld().getWidth();
        int wh = getWorld().getHeight();
        
        if (xPos >= ww - 1) setLocation(1, getY());

        if (xPos <= 0) setLocation(ww - 2, getY());

        if (yPos >= wh - 1) setLocation(getX(), 1);

        if (yPos <= 0) setLocation(getX(), wh - 2);
    }


    
}
