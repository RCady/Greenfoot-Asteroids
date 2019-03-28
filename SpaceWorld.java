import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List; // R8
import java.awt.*;
import java.awt.geom.Point2D;



/**
 * This world is supposed to be a replica of asteroids, but as I ran out of time it is not exactly the same.
 * The sounds used are the original sounds from the asteroids game.
 * The objective is to destroy the asteroids and reach a high score. 
 *
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceWorld extends World
{

    int worldWidth = getWidth();
    int worldHeight = getHeight();

    public static boolean playerDead = false;
    public static int livesRemaining = 3;

    public static int score;
    public static int asteroidsAlive = 0;
    public static int difficultyChosen = 1;

    public static int playerBoundXRight;
    public static int playerBoundXLeft;
    public static int playerBoundYTop;
    public static int playerBoundYBottom;

    public static boolean hasStarted = false;
    public static boolean inOptions = false;

    //public static Actor[] scoreImages = {};

    public static GreenfootImage[] numberImage = {
        new GreenfootImage("0.png"),
        new GreenfootImage("1.png"),
        new GreenfootImage("2.png"),
        new GreenfootImage("3.png"),
        new GreenfootImage("4.png"),
        new GreenfootImage("5.png"),
        new GreenfootImage("6.png"),
        new GreenfootImage("7.png"),
        new GreenfootImage("8.png"),
        new GreenfootImage("9.png")
    };

    boolean isDrawn = false;
    
    boolean isSpawned = false;

    Actor title = new Title();
    Actor start = new Start();
    Actor options = new Options();
    Actor difficulty = new Difficulty();
    Actor diff1 = new Diff1();
    Actor diff1Clone = new Diff1();
    Actor diff2 = new Diff2();
    Actor diff2Clone = new Diff2();
    Actor diff3 = new Diff3();
    Actor diff3Clone = new Diff3();
    Actor back = new Back();

    int newScore = 0;

    int randX;
    int randY;

    int choice;


    /**
     * Constructor for objects of class SpaceWorld.
     *
     */
    public SpaceWorld()
    {
        super(800, 600, 1);
        startMenu(); 
    }

    public void act()
    {
        playerSetup();
        optionsMenu();
        clearScreen();
        scoreBoard();
        gameStart();
        if (hasStarted)
        {
            removeObject(title);
            removeObject(start);
            removeObject(options);
        }
        reset();

        if (Greenfoot.isKeyDown("p"))
        {
            
        }

        choice = Greenfoot.getRandomNumber(2);

    }

    public void reset()
    {
        if (playerDead)
        {
            List objects = getObjects(null);
            removeObjects(objects);
            

            clearScreen();
            score = 0;

            hasStarted = false;
            isSpawned = false;
            playerSetup();
            playerDead = false;
            asteroidsAlive = 0;
        }
    }

    public void scoreBoard()
    {
        if (hasStarted)
        {
            setBackground("blackBG.jpg");
            getBackground().drawImage(getNumberImage(score), 700, 15);
        }
    }

    public void clearScreen()
    {
        if (hasStarted || inOptions)
        {
            removeObject(title);
            removeObject(start);
            removeObject(options);
        }

        if (!inOptions)
        {
            removeObject(difficulty);
            removeObject(diff1);
            removeObject(diff2);
            removeObject(diff3);
            removeObject(diff1Clone);
            removeObject(diff2Clone);
            removeObject(diff3Clone);
            removeObject(back);
            startMenu();
        }

    }

    public GreenfootImage getNumberImage(int num)
    {
        GreenfootImage built = new GreenfootImage(numberImage[num%10]);

        while ((num = num/10) > 0)
        {
            GreenfootImage additional = new GreenfootImage(numberImage[num%10]);
            GreenfootImage combined = new GreenfootImage(built.getWidth() + additional.getWidth() + 5, built.getHeight());
            combined.drawImage(additional, 0, 0);
            combined.drawImage(built, additional.getWidth() + 3, 0);
            built = new GreenfootImage(combined);
        }
        return built;
    }

    public void startMenu()
    {
            addObject(title, worldWidth / 2, 100); // R7
            addObject(start, worldWidth / 4, 450);
            addObject(options, worldWidth - (worldWidth / 4), 450);
    }

    public void optionsMenu()
    {
        if (inOptions)
        {
            addObject(difficulty, (worldWidth / 2) - 100, 450);
            addObject(diff1, worldWidth / 3, 150);
            addObject(diff2, worldWidth / 2, 150);
            addObject(diff3, worldWidth - worldWidth / 3 , 150);
            addObject(back, worldWidth / 4, 300);


            if (difficultyChosen == 1) {addObject(diff1Clone, worldWidth / 2 + 200, 450);removeObject(diff2Clone);removeObject(diff3Clone);}
            if (difficultyChosen == 2) {addObject(diff2Clone, worldWidth / 2 + 200, 450); removeObject(diff1Clone);removeObject(diff3Clone);}
            if (difficultyChosen == 3) {addObject(diff3Clone, worldWidth / 2 + 200, 450);removeObject(diff1Clone);removeObject(diff2Clone);}
        }
    }

    public void gameStart()
    {
        if (hasStarted)
        {
            difficultyLevel(difficultyChosen); //R1
            playerSetup();
        }
    }


    private void playerSetup()
    {
        if (!isSpawned)
        {
            addObject (new SpaceShip(), 375, 300);
            isSpawned = true;
        }

    }
    
    public void playerHud()
    {
        GreenfootImage hudShip = new GreenfootImage("rocket.png");

        if (livesRemaining == 3 && !isDrawn)
        {
            hudShip.rotate(270);
            getBackground().drawImage(hudShip, 700, 25);
            getBackground().drawImage(hudShip, 725, 25);
            getBackground().drawImage(hudShip, 750, 25);
            isDrawn = true;
        }
        if (livesRemaining == 2 && !isDrawn)
        {
            hudShip.rotate(270);
            getBackground().drawImage(hudShip, 725, 25);
            getBackground().drawImage(hudShip, 750, 25);
            isDrawn = true;
        }
        if (livesRemaining == 1 && !isDrawn)
        {
            hudShip.rotate(270);
            getBackground().drawImage(hudShip, 750, 25);
            isDrawn = true;
        }
        if (livesRemaining == 0 && !isDrawn)
        {
            hudShip.rotate(270);
            isDrawn = true;
        }
        
    }
    
    public void gameOver()
    {
        if (playerDead)
        {
            List objects = getObjects(null);
            removeObjects(objects);


            GreenfootImage gameOver = new GreenfootImage("gameOver.png"); // R7
            getBackground().drawImage(gameOver, 50, 100);
            playerSetup();
            playerDead = false;
        }
    }

    //R1, R4 & R10
    public void difficultyLevel(int difficulty)
    {
        
        
        
        if (asteroidsAlive == 0)
        {

            switch (difficulty)
            {
                case 1:

                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    break;

                case 2:
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    break;

                case 3:
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    spawnAsteroids();
                    break;

            }

       }
       
    }

    public void spawnAsteroids()
    {
        

        if (choice == 0)
        {
            randX = playerBoundXRight + Greenfoot.getRandomNumber(worldWidth - playerBoundXRight);
            randY = playerBoundYBottom + Greenfoot.getRandomNumber(worldHeight - playerBoundYBottom);
            addObject (new Asteroid(2), randX, randY);
        }
        if (choice == 1)
        {
            randX = playerBoundXLeft - Greenfoot.getRandomNumber(worldWidth + playerBoundXLeft);
            randY = playerBoundYTop - Greenfoot.getRandomNumber(worldHeight + playerBoundYTop);
            addObject (new Asteroid(2), randX, randY);
        }

        asteroidsAlive = asteroidsAlive + 7;
    }
}