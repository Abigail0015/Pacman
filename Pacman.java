import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pacman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pacman extends Actor
{
    private static final int OFFSET = 5;
    private int currentImage;
    private int counter;
    private int lifes; 
    private int foodCounter;
    private int score;

    public Pacman()
    {
        setImage("images/Pacman/pacmanA.png");
        lifes = 3;

    }

    /**
     * Act - do whatever the Pacman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        handleDirection();
        ghostCrash();
        eatPoints();
        crossTunnel();
        // Add your action code here.
    }  

    public void handleDirection()
    {
        boolean up = Greenfoot.isKeyDown("up");
        boolean down = Greenfoot.isKeyDown("down");
        boolean left = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");

        if(right)
        {
            setRotation(0);
            move(+5);
            checkObstacles("right");
        }
        else if(left)
        {  
            setRotation(180);
            move(+5);
            checkObstacles("left");
        }
        else if(down)
        {
            setRotation(90);
            move(+5);
            checkObstacles("down");
        }
        else if(up)
        {
            setRotation(270);
            move(+5);
            checkObstacles("up");
        }
        moveMouth();

    }
    private void checkObstacles(String direction)
    {
        int posX = this.getX();
        int posY = this.getY();

        
        switch(direction)
        {
            case "left":
            if (getWorld().getObjectsAt(posX - 1,posY,Wall.class).size() != 0)
            {
                move(-5);
            }
            break;
            case "right":
            if (getWorld().getObjectsAt(posX + 1,posY,Wall.class).size() != 0)
            {
                move(-5);
            }
            break;
            case "down":
            if (getWorld().getObjectsAt(posX,posY + 1,Wall.class).size() != 0)
            {
                move(-5);
            }
            break;
            case "up":
            if (getWorld().getObjectsAt(posX,posY - 1,Wall.class).size() != 0)
            {
                move(-5);
            }
            break;
        }

    }

    private void ghostCrash()
    {
        Actor ghost = getOneIntersectingObject(Ghost.class);
        if(ghost != null)
        { 
            deadAnimation();
            reset();
            lifes--;
            usedLife();

        }
    }

    private void usedLife()
    {
        getWorld().removeObjects(getWorld().getObjects(Life.class));

        if (lifes == 2)
        {
            Life life = new Life();
            getWorld().addObject(life,60,690);
            life.setRotation(180);

        }
        
    }
    private void reset()
    {
        setImage("images/Pacman/pacmanA.png");
        this.setLocation(290,520);

    }
    private void eatPoints()
    {
        Item item = (Item)getOneIntersectingObject(Item.class);

        if(item != null)
        {
            score += item.getValue();
            getWorld().removeObject(item);
            if(foodCounter == 0)
            {  
                Greenfoot.playSound("sounds/pacmanEating.wav");

            }
            foodCounter =(foodCounter + 1) % 4;
        }
    }

    public int getScore()
    {
        return(score);
    }

    private void moveMouth()
    {
        if(counter == 0)
        {
            if(currentImage == 0)
            {
                setImage("images/Pacman/pacmanB.png");
            } 
            else if (currentImage == 1)
            {
                setImage("images/Pacman/pacmanC.png");
            }
            else if(currentImage == 2)
            {
                setImage("images/Pacman/pacmanA.png");
            }
            currentImage = (currentImage + 1) % 3;
        }
        counter =(counter + 1) % 6;
    }

    private void crossTunnel()
    {
        int posX = this.getX();
        int posY = this.getY();
        //getWorld().showText(String.valueOf(posX)+"," +String.valueOf(posY),50,20);
        if(posX >= 579)
        {
            this.setLocation(1, posY);
        }
        else if(posX <= 0)
        {
            this.setLocation(578, posY);
        }
    }

    private void deadAnimation()
    {

        for(int counter = 0; counter < 8; counter++)
        {
            for(int counter2 = 0; counter2 < 5; counter2++)
            {
                setImage("images/Pacman/Dead-Animation/dead"+counter+".png");
            }
        }

    }

}
