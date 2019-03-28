import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends AsteroidSuper
{

	int smallDimension = 30;
    int mediumDimension = 75;
    int largeDimension = 130;

    int randX;
    int randY;
    int randRot;
    int intialRotation;

    int dx;
    int dy;

    boolean isSmall;
    boolean isMedium;
    boolean isLarge;
	

	public Asteroid(int size)
	{
        int variation = Greenfoot.getRandomNumber(2);

        randRot = Greenfoot.getRandomNumber(3) - 1;
        if (randRot == 0) {randRot = randRot - 1;}

        setRotation(Greenfoot.getRandomNumber(360));
        intialRotation = getRotation();
        
        if (size == 0)
        {
            GreenfootImage asteroid = new GreenfootImage(smallDimension,smallDimension);
            asteroid.setColor(greenfoot.Color.WHITE);
            int[] asteroidX = {1, 6, 17, 29, 25, 29, 15};
            int[] asteroidY = {13, 29, 27, 29, 12, 1, 1};
            asteroid.drawPolygon(asteroidX, asteroidY, 7);
            setImage(asteroid);
            isSmall = true;

            if (dx == 0 && dy == 0) {dx = dx + 1; dy = dy - 1;}

            dx = Greenfoot.getRandomNumber(5) - 2;
            dy = Greenfoot.getRandomNumber(5) - 2;
        }

        if (size == 1)
        {
            if (variation == 0){mediumVariation0();}
            if (variation == 1){mediumVariation1();}
            isMedium = true;

            if (dx == 0 && dy == 0) {dx = dx + 1; dy = dy - 1;}

            dx = Greenfoot.getRandomNumber(4) - 2;
            dy = Greenfoot.getRandomNumber(4) - 2;
        }

        if (size == 2)
        {
            if (variation == 0){largeVariation0();}
            if (variation == 1){largeVariation1();}
            isLarge = true;

            if (dx == 0 && dy == 0) {dx = dx + 1; dy = dy - 1;}

            dx = Greenfoot.getRandomNumber(2) - Greenfoot.getRandomNumber(2);
            dy = Greenfoot.getRandomNumber(2) - Greenfoot.getRandomNumber(2);
        }
	}



    /**
     * Act - do whatever the Asteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        randX = Greenfoot.getRandomNumber(10) - 5;
        randY = Greenfoot.getRandomNumber(10) - 5;

        overEdge();

        setLocation((getX() + dx), (getY() + dy));
        setRotation(getRotation() + randRot);

        asteroidDeath();
    }

    public void asteroidDeath()
    {
        Actor b = getOneIntersectingObject(Projectile.class);
        
        if (b != null)
        {
            Greenfoot.playSound("bangLarge.wav");
            getWorld().removeObject(b); // R5a
            //SpaceWorld.playerDead = true;
            SpaceWorld.livesRemaining--;
            if (isLarge)
            {
                getWorld().addObject(new Asteroid(1), getX(), getY());
                getWorld().addObject(new Asteroid(1), getX(), getY());
                SpaceWorld.score = SpaceWorld.score + 50;

            }
            if (isMedium)
            {
                getWorld().addObject(new Asteroid(0), getX(), getY());
                getWorld().addObject(new Asteroid(0), getX(), getY());
                SpaceWorld.score = SpaceWorld.score + 100;
            }
            if (isSmall)
            {
                SpaceWorld.score = SpaceWorld.score + 200;
            }
            
            Player.numberOfProjectiles--;
            explosion();
            getWorld().removeObject(this); // R5a
            SpaceWorld.asteroidsAlive--;
        }
    }

    public void explosion()
    {
        for (int i = 0; i < 15; i++){
            getWorld().addObject(new Shrapnel(), getX() + randX, getY() + randY);
        }
    }

    public GreenfootImage largeVariation0()
    {
        GreenfootImage asteroid = new GreenfootImage(largeDimension,largeDimension);
        asteroid.setColor(greenfoot.Color.WHITE);
        int[] asteroidX = {1, 9, 32, 58, 66, 106, 123, 129, 120, 129, 129, 115, 87, 64, 33, 17, 3, 1};
        int[] asteroidY = {34, 13, 1, 1, 14, 1, 5, 18, 44, 81, 92, 116, 127, 129, 93, 129, 113, 90};
        asteroid.drawPolygon(asteroidX, asteroidY, 18);
        setImage(asteroid);
        
        return asteroid;
    }
    public GreenfootImage largeVariation1()
    {
        GreenfootImage asteroid = new GreenfootImage(largeDimension,largeDimension);
        asteroid.setColor(greenfoot.Color.WHITE);        
        int[] asteroidX = {34, 13, 1, 1, 25, 1, 5, 18, 44, 81, 92, 116, 127, 129, 93, 129, 113, 90};
        int[] asteroidY = {1, 9, 32, 58, 66, 106, 123, 129, 120, 129, 129, 115, 87, 64, 33, 17, 3, 1};
        asteroid.drawPolygon(asteroidX, asteroidY, 18);
        setImage(asteroid);
        
        return asteroid;
    }

    public GreenfootImage mediumVariation0()
    {
        GreenfootImage asteroid = new GreenfootImage(mediumDimension,mediumDimension);
        asteroid.setColor(greenfoot.Color.WHITE);
        int[] asteroidX = {1, 25, 60, 74, 74, 50, 50, 50, 15, 1, 1, 24};
        int[] asteroidY = {15, 1, 1, 20, 60, 50, 67, 74, 74, 50, 35, 15};
        asteroid.drawPolygon(asteroidX, asteroidY, 12);
        setImage(asteroid);

        return asteroid;
    }

    public GreenfootImage mediumVariation1()
    {
        GreenfootImage asteroid = new GreenfootImage(mediumDimension,mediumDimension);
        asteroid.setColor(greenfoot.Color.WHITE);
        int[] asteroidX = {15, 1, 1, 20, 66, 50, 67, 74, 74, 50, 35, 30};
        int[] asteroidY = {1, 25, 60, 74, 68, 50, 50, 50, 15, 1, 1, 24};
                            
        asteroid.drawPolygon(asteroidX, asteroidY, 12);
        setImage(asteroid);

        return asteroid;
    }
}
