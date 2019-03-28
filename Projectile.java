import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends SpaceShip
{

    public Projectile()
    {
        setImage("projectile.png");
    }

    int timer = 25;
    boolean hasDied = false;

    /**
     * Act - do whatever the Projectile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        move(12);
        overEdge();
        collision();
        projectileTime();
    }

    public void projectileTime()
    {
        timer--;

        if (timer == 0)
        {
            getWorld().removeObject(this); // R5a
            numberOfProjectiles--;
        }
    }

    public void collision()
    {

        if (!hasDied)
        {
            Actor d = getOneIntersectingObject(BigAsteroid.class);
            
            if (d != null)
            {
                hasDied = true;
                Greenfoot.playSound("bangLarge.wav");
                getWorld().removeObject(d); // R5a
                getWorld().removeObject(this); // R5a
                numberOfProjectiles--;
                SpaceWorld.score = SpaceWorld.score + 10;
                SpaceWorld.asteroidsAlive--;
            }
            
        }
        
    }
}
