import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Shrapnel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shrapnel extends Actor
{
	int t = getImage().getTransparency();
	int SPEED = 8;

	public Shrapnel()
	{
		GreenfootImage shrapnel = new GreenfootImage(3,3);
		shrapnel.setColor(greenfoot.Color.WHITE);
		shrapnel.fill();

		setImage(shrapnel);

		int randR = Greenfoot.getRandomNumber(361);
		setRotation(randR);
	}

    /**
     * Act - do whatever the Shrapnel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    	
    	getImage().setTransparency(t);
        move(SPEED);
        if (SPEED > 4) {SPEED--;}
        if (SPEED < 5) {t = t - 8;}
        overEdge();

        if (t < 6)
        {
        	getWorld().removeObject(this);
        }
    }

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
