import greenfoot.*;

/**
 * Write a description of class Back here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Back extends Options
{
    /**
     * Act - do whatever the Back wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor b = getOneIntersectingObject(Projectile.class);

    	if (b != null)
        {
           SpaceWorld.inOptions = false;
        }
    }    
}
