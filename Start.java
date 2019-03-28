import greenfoot.*;

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends Actor
{
    /**
     * Act - do whatever the Start wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    	Actor b = getOneIntersectingObject(Projectile.class);

    	if (b != null)
        {
           SpaceWorld.hasStarted = true;
        }
    }    
}
