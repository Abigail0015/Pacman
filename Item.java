import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Actor
{
    String itemName;
    int value;
    
    
    public void setValue(int value)
    {
        this.value = value;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
        setImage("images/Items/"+this.itemName+".png");

    }
    public int getValue()
    {
        return(value);
    }
}
