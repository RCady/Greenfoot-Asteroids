import greenfoot.*;

/**
 * Write a description of class AsteroidSuper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AsteroidSuper extends Actor
{
    /**
     * Act - do whatever the AsteroidSuper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }

    public void smallAsteroidMovement()
    {
    	int rand = Greenfoot.getRandomNumber(5);

    	if (rand < 2) //R14
    	{
    		move(rand * -1);
    	}
    	else
    	{
    		move(rand);
    	}


    }

    public void bigAsteroidMovement()
    {

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
