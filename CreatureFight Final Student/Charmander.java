import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charmander extends Creature
{
    /**
     * Constructor for objects of class Charmander
     * 
     * @param w is a reference to the world that Charmander gets added to
     * @return An object of type Charmander
     */
    public Charmander(World w)
    {
        super(700, 1, "Fire");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 300, w.getHeight() - 50 );
    }
    
    /**
     * Act - makes a cast to creature world, checks if the healthbar of this creature is lower or equal to 0 and if so, show that this
     * pokemon has fainted then delay by 30 seconds, checks if the players second creatures healthbar is over 0 and if so switch to
     * creature 1 set the turn number to 1 remove the text and remove this object,checks if the players first creatures healthbar 
     * is over 0 and if so switch to creature 2 set the turn number to 1 remove the text and remove this object.
     * 
     * @param  There are no parameters
     * @return Nothing is returned
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();

        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Charmander has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            
            //TODO (68): If the current health of the health bar of the new one creature at index 1 in player world is greater than 0...
            if(playerWorld.getNewOneCreature(1).getHealthBar().current > 0)
            {
                //TODO (69): Call the switchCreature method using a value of 1 as the parameter
                switchCreature(1);
                //TODO (70): Set the turn number in player world to 1
                playerWorld.setTurnNumber(1);
                //TODO (71): Clear the text (using an empty String, "") at the location that it stated Charmander had fainted
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                //TODO (72): Remove this object from the world
                getWorld().removeObject(this);
            }
            else if(playerWorld.getNewOneCreature(2).getHealthBar().current > 0)
            {
                //TODO (73): Otherwise, if the current health of the health bar of the new one creature at index 2 in player world is greater than 0...
                
                //TODO (74): Call the switchCreature method using a value of 2 as the parameter
                switchCreature(2);
                //TODO (75): Set the turn number in player world to 1
                playerWorld.setTurnNumber(1);
                //TODO (76): Clear the text (using an empty String, "") at the location that it stated Charmander had fainted
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                //TODO (77): Remove this object from the world
                getWorld().removeObject(this);    
                }
        }
    }
    
    /**
     * attack takes away health from the enemy Creature using one of
     * two predefined attacks
     * 
     * @param idx is the index of the attack option selected
     * @return Nothing is returned
     */
    public void attack(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature enemy = playerWorld.getPlayerTwo();
        String enemyType = enemy.getType();
        
        //TODO (39): Make a call to the attackAnimation method
        attackAnimation();

        if( idx == 0 )
        {
            enemy.getHealthBar().add( -25 );
        }
        else
        {
            //TODO (40): If the enemy type equals (ignoring case) Water...
            if(enemyType == "water")
            {
                //TODO (41): The enemy only receives half damage of the normal attack (70 points). DON'T DO THE MATH! Just type the mathematical expression you would use
                enemy.getHealthBar().add(70/2);
                
                /**
                 * TODO (42): Show text that states the attack was not very effective at a x location of half the width of the world 
                 *          and a y location of half the height of the world plus 26 pixels
                 */
                getWorld().showText("The attack was not very effective", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                
                //TODO (43): Delay the scenario by 30 pixels
                Greenfoot.delay(30);
            }
            
            //TODO (44): Otherwise...
            else
            {
                //TODO (45): Take the line from below that takes away 70 points of health and place it inside this else
                enemy.getHealthBar().add( -70 );
            }
        }

        playerWorld.setTurnNumber(2);
    }
    
    /**
     * attackAnimation - makes a basic moving animation by increasing th x and y by a bit 15 times then seting it's position back to
     * where it started off.  
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void attackAnimation()
    {
        int originalX = getX();
        
        int originalY = getY();
        
        for(int i = 0; i < 15; i++)
        {
            setLocation(getX() + 1, getY() - 1);
            
            Greenfoot.delay(1);
        }
        
        setLocation(originalX, originalY);
    }

    /**
     * switchCreature - creates a cast to the world, creates a new creature variable, checks if the creatures fainted and if so say it has
     * fainted, if it has not play an animation then delay the scenario by 2 act cycles, set the transparency of the image and it's
     * healthbar to 0, then checks if the idx is equal to 1 and if so golem is the new creature for player one if not then ivysaur is,
     * run the switchedIn method, set the turn number to 2. 
     * 
     * @param The parameter idx is used for switching to the specific creature of the players choice
     * @return Nothing is returned         
     */
    public void switchCreature(int idx)
    {
        CreatureWorld playerWorld = ( (CreatureWorld) getWorld() );
        
        Creature switchCreature =  playerWorld.getNewOneCreature(idx);
        
        if(switchCreature.getHealthBar().current == 0)
        {
            JOptionPane.showMessageDialog(null, "the creature you've chosen has fainted, you must choose something else");
        }
        else
        {
            while(getX() > 0)
            {
                setLocation(getX() - 5, getY() - 5);
                
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
                
            getHealthBar().getImage().setTransparency(0);
            
            if(idx == 1)
            {
                playerWorld.changePlayerOne("Golem");
            }
            else
            {
                playerWorld.changePlayerOne("Ivysaur");
            }
            switchedIn();
            
            playerWorld.setTurnNumber(2);
        }
    }

    /**
     * switchIn - sets transparnecy of the image and the healtbar of the creature to full and play another simple animation 
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        
        getHealthBar().getImage().setTransparency(255);
        
        while(getX() < getImage().getWidth()/2)
        {
            setLocation(getX() + 5, getY() + 5);
            
            Greenfoot.delay(2);
        }
    }
}
