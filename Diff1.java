import greenfoot.*;

/**
 * Write a description of class Diff1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Diff1 extends Options
{
    /**
     * Act - do whatever the Diff1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Actor b = getOneIntersectingObject(Projectile.class);

    	if (b != null)
        {
           SpaceWorld.difficultyChosen = 1;
        }
    }    
}
