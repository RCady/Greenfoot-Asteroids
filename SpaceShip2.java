import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class SpaceShip2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceShip2 extends Player
{

    int[] thrustersX = {14, 5, 14};
    int[] thrustersY = {7, 12, 17};
    int[] edge1X = {8, 38};
    int[] edge1Y = {0, 12};

    int[] edge2X = {38, 8};
    int[] edge2Y = {12, 24};
        
    int[] baseX = {14, 14};
    int[] baseY = {3, 21};

    public static int playerLocation;

    int invulnerabilityTimer;
    int counter = 30;
    boolean isInvulnerable = false;
    boolean invulnerableDelay = false;

    GreenfootImage shipSprite = new GreenfootImage(39,25);
    GreenfootImage blankShip = new GreenfootImage(39,25);

    GreenfootImage shipOnSprite = new GreenfootImage(39,25);
    

    public SpaceShip2()
    {
        setRotation(getRotation() - 90);
        shipSprite.setColor(greenfoot.Color.RED);
        shipSprite.drawPolygon(edge1X, edge1Y, 2);
        shipSprite.drawPolygon(edge2X, edge2Y, 2);
        shipSprite.drawPolygon(baseX, baseY, 2);
        setImage(shipSprite);

        shipOnSprite.setColor(greenfoot.Color.RED);
        shipOnSprite.drawPolygon(edge1X, edge1Y, 2);
        shipOnSprite.drawPolygon(edge2X, edge2Y, 2);
        shipOnSprite.drawPolygon(baseX, baseY, 2);
        shipOnSprite.drawPolygon(thrustersX, thrustersY, 3);
    }
    
    double dx;
    double dy;
    double SPEED = 0.1;

    public int xPos;
    public int yPos;

    int imageHeight = getImage().getHeight();
    int imageWidth = getImage().getWidth();

    int timer = 0;
    int thrustTime = 0;

    boolean hasDied = false;
    boolean isDown = false;
    boolean isBlinking = false;
    //boolean thrust = false;

    GreenfootSound thrust = new GreenfootSound("thrust.wav");


    /**
     * Act - do whatever the SpaceShip wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        xPos = getX();
        yPos = getY();

        SpaceWorld.playerBoundXRight = xPos + 25;
        SpaceWorld.playerBoundXLeft = xPos - 25;

        SpaceWorld.playerBoundYTop = yPos - 25;
        SpaceWorld.playerBoundYBottom = yPos + 25;

        movement();
        gunBehavior();
        overEdge(); // R3
        collision();
        invulnerability();
    }

    public void gunBehavior()
    {
        if (!isDown)
        {
            if (Greenfoot.isKeyDown("c"))
            {
                Projectile projectile = new Projectile();
                getWorld().addObject(projectile, getX(), getY()); // R5b
                projectile.setRotation(getRotation());
                projectile.move(35);
                isDown = true;
                numberOfProjectiles++;
                Greenfoot.playSound("shoot.wav"); //R8
            }
        }
        if (!Greenfoot.isKeyDown("c") && numberOfProjectiles < 15)
        {
            isDown = false;
        }
    }
    int onTimer = 0;
    public void movement()
    {


        if (!Greenfoot.isKeyDown("right") || !Greenfoot.isKeyDown("left") || !Greenfoot.isKeyDown("up")) //R8 & R13
        {
            //setImage("rocket.png");
        }

        if (Greenfoot.isKeyDown("w") ) //R8
        {
            speedUp();
            
            setImage(shipOnSprite);

            thrust.play(); 
            isBlinking = true;
        }
        else //R8 & R9
        {
            isBlinking = false;
            thrust.stop();
            setImage(shipSprite);
        }

        setLocation( (int) (getX() + dx),(int) (getY() + dy));

        if (Greenfoot.isKeyDown("a") ) //R8
        {
           turn(-4);
        }

        if (Greenfoot.isKeyDown("d") ) //R8
        {
           turn(4);
        }

    }

    private void speedUp() {
        dx += SPEED * Math.cos(Math.PI/180*getRotation());
        dy += SPEED * Math.sin(Math.PI/180*getRotation());
    }

    public void collision()
    {
        Actor b = getOneIntersectingObject(Asteroid.class);
            if (b != null)
            {
                Greenfoot.playSound("bangLarge.wav");
                getWorld().removeObject(b);
                SpaceWorld.playerDead = true;
                hasDied = true;
                getWorld().removeObject(this);
                numberOfProjectiles--;
            }
    }

    public void invulnerability()
    {
        if (Greenfoot.isKeyDown("f") && !isInvulnerable && !invulnerableDelay)
        {
            invulnerabilityTimer = 200;
            isInvulnerable = true;
        }

        if (isInvulnerable && counter > 0)
        {
            int t1 = 255;
            int t2 = 0;

            counter--;

            if (counter > 15) {getImage().setTransparency(t1);}
            if (counter < 15) {getImage().setTransparency(t2);}
            if (counter == 0) {counter = 30;}
        }

        if (invulnerabilityTimer > 0)
        {
            invulnerabilityTimer--;
        }

        if (invulnerabilityTimer < 0){invulnerableDelay = false;}

        if (invulnerabilityTimer < 100){isInvulnerable = false;}
    }
}
