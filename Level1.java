import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
import java.awt.FileDialog;
import java.io.*;
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{

    private boolean begin = true;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private int size = 20;
    private int floorSize = 2;
    Pacman pacman = new Pacman();
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(580, 720, 1);     
        Greenfoot.setSpeed(50);
        generateLevel();
        showScore();
        beginning();

    }

    public void act()
    {
                showScore();

    }

    private void generateLevel()
    {
        generateMap();
        generateCharacters();
        generateData();

    }

    private void generateMap()
    {
        try 
        {
            String link = "map.txt";
            BufferedReader br = getBuffered(link);
            String line =  br.readLine();
            char array[][] = new char[31][29];
            int counter = 0;
            while(line != null)
            {
                String[] values = line.split(",");
                for (int counter2 = 1; counter2 < values.length; counter2++) 
                {
                    array[counter][counter2] = values[counter2].charAt(0);
                }
                counter++;
                line = br.readLine();
            }
            drawMap(array);
        } 
        catch (IOException | NumberFormatException e) 
        {
            e.printStackTrace();
        }

    }  

    private BufferedReader getBuffered(String link)
    {

        FileReader reader  = null;
        BufferedReader br = null;
        try 
        {
            File file=new File(link);
            if(!file.exists())
            {
                System.out.println("File not found");
            }else
            {
                reader = new FileReader(link);
                br = new BufferedReader(reader);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return br;
    }
    private void drawMap(char array[][])
    {
        char letter;
        String pieceType;
        int posX = 20;
        int posY = 60;
        
        for(int counter = 0; counter < 31;counter ++)
        {
            for(int counter2 = 1; counter2 < 29;counter2 ++)
            {
                letter = array[counter][counter2];
                pieceType = String.valueOf(letter);
                
                if(letter != 'W' && letter != 'X' && letter != '0')
                {
                    addObject(new Wall(pieceType),posX,posY);
                }
                else if(letter == 'W')
                {
                    addObject(new Point(),posX,posY);
                }
                else if(letter == 'X')
                {
                    addObject(new SuperPoint(),posX,posY);

                }
                
                posX+=20;
            }
            posX = 20;
            posY+=20;
        }
    }
    
    private void generateCharacters()
    {
        addObject(pacman,290,520);
        addObject(new Ghost("redGhost"),290,280);
        addObject(new Ghost("blueGhost"),250,340);
        addObject(new Ghost("pinkGhost"),290,340);
        addObject(new Ghost("orangeGhost"),330,340);
         addObject(new Cherry(),100,340);
        addObject(new Strawberry(),478,340);
        
    }

    private void generateData()
    {
        Life life1 = new Life();
        addObject(life1,60,690);
        life1.setRotation(180);

        Life life2 = new Life();
        addObject(life2,100,690);
        life2.setRotation(180);

        Life life3 = new Life();
        addObject(life3,140,690);
        life3.setRotation(180);
        


    }

    private void beginning()
    {
        if(begin == true)
        {
            begin = false;
            Greenfoot.playSound("sounds/pacmanSong.wav");
            Greenfoot.delay(250);
            removeObject(getObjectsAt(140,700,Life.class).get(0)); 
        }
    }
    
    
    
    private void showScore()
    {
      int score = pacman.getScore();
      showText("SCORE: "+String.valueOf(score),290,20);
    }
    
    
}
