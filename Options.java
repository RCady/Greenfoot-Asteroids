import greenfoot.*;

/**
 * Write a description of class Options here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Options extends Actor
{
    /**
     * Act - do whatever the Options wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor b = getOneIntersectingObject(Projectile.class);

    	if (b != null)
        {
           SpaceWorld.inOptions = true;
        }
    }
}
