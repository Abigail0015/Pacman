import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ghost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ghost extends Actor
{
    private int currentImage;
    private String ghostColor;
    
    public Ghost(String ghostColor)
    {
        this.ghostColor = ghostColor;
        setImage("images/Ghost/"+this.ghostColor+"/"+ this.ghostColor+"RightA.png");
    }
    
    /**
     * Act - do whatever the Ghost wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    
    
}
