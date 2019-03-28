import greenfoot.*;

/**
 * Write a description of class BigAsteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BigAsteroid extends AsteroidSuper
{
        
    int speed = 0;

    public BigAsteroid()
    {
        speed = Greenfoot.getRandomNumber(3); // R2
        if (speed == 0)
        {
            speed = speed + 1;
        }
    }

    /**
     * Act - do whatever the BigAsteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        asteroidMovement(); // R2
        overEdge(); // R3
        death();

    }

    public void death()
    {
        
    	Actor b = getOneIntersectingObject(Projectile.class);
        
        
        if (b != null)
        {
            Greenfoot.playSound("bangLarge.wav");
            getWorld().removeObject(b); // R5a
            SpaceWorld.playerDead = true;
            //SpaceWorld.livesRemaining--;            
            getWorld().removeObject(this); // R5a
            SpaceWorld.asteroidsAlive--;
        }
        
    }

    // R2
    public void asteroidMovement()
    {
        setRotation(getRotation() + speed);

        setLocation(getX(), getY() + speed);
    }
}
