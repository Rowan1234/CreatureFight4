import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends Creature
{
    /**
     * Pikachu - sets up some basic principles about Pikachu
     * 
     * @param w is a reference to the world that Pikachu gets added to
     * @return An object of type Pikachu
     */
    public Pikachu(World w)
    {
        super(650, 2, "Electric");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 100, 25 );
    }
    
    /**"creature
     * Act - checks if pikachu has fainted if so say it, checks if the health bar of the new "creature" is still greater than 0 and if so
     * swith the creature, set the turnumber, remove the message that says pikachu fainted, and remove the pikachu object. Also checks if player
     * two creatures healthbar is still greater than 0, switch the creature, set the turn number to 2, remove the message that says 
     * pikachu fainted, and remove the pikachu object.
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();

        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Pikachu has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            
            //TODO (142): If the current health of the health bar of the new two creature at index 1 in player world is greater than 0...
            if(playerWorld.getNewOneCreature(1).getHealthBar().current > 0)
            {
                //TODO (143): Call the switchCreature method using a value of 1 as the parameter
                switchCreatures(1);
                //TODO (144): Set the turn number in player world to 2
                playerWorld.setTurnNumber(2);
                //TODO (145): Clear the text (using an empty String, "") at the location that it stated Pikachu had fainted
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                //TODO (146): Remove this object from the world
                getWorld().removeObject(this);
            }
            //TODO (147): Otherwise, if the current health of the health bar of the new two creature at index 2 in player world is greater than 0...
            else if(playerWorld.getPlayerTwo().getHealthBar().current > 0)
            {
                //TODO (148): Call the switchCreature method using a value of 2 as the parameter
                switchCreatures(2);
                //TODO (149): Set the turn number in player world to 2
                playerWorld.setTurnNumber(2);
                //TODO (150): Clear the text (using an empty String, "") at the location that it stated Pikachu had fainted
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                //TODO (151): Remove this object from the world
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
        Creature enemy = playerWorld.getPlayerOne();
        String enemyType = enemy.getType();
        
        //TODO (109): Make a call to the attackAnimation method
        attackAnimation();

        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            //TODO (110): If the enemy type equals (ignoring case) Rock...
            if(enemyType == "Rock")
            {
                //TODO (111): The enemy receives zero damage
                enemy.getHealthBar().add( 0 );
                
                /**
                 * TODO (112): Show text that states the attack has no effect at a x location of half the width of the world 
                 *          and a y location of half the height of the world plus 26 pixels
                 */
                getWorld().showText("The attack has no effect", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                
                //TODO (113): Delay the scenario by 30 pixels
                Greenfoot.delay(30);
            }
            //TODO (114): If the enemy type equals (ignoring case) Grass...
            if(enemyType == "Grass")
            {
                //TODO (115): The enemy only receives half damage of the normal attack (65 points). DON'T DO THE MATH! Just type the mathematical expression you would use
                enemy.getHealthBar().add( -65/2 );
                
                /**
                 * TODO (116): Show text that states the attack was not very effective at a x location of half the width of the world 
                 *          and a y location of half the height of the world plus 26 pixels
                 */
                getWorld().showText("The attack was not very effective", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                
                //TODO (117): Delay the scenario by 30 pixels
                Greenfoot.delay(30);
            }
            //TODO (118): Otherwise...
            else
            {
                //TODO (119): Take the line from below that takes away 65 points of health and place it inside this else
                enemy.getHealthBar().add( -65 );
            }
        }

        playerWorld.setTurnNumber(1);
    }

    /**
     * attackAnimation - creates an animation for the pikachu
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
            setLocation(getX() - 1, getY() - 2);
            
            Greenfoot.delay(1);
        }
        
        setLocation(originalX, originalY);
    }
    

    /**
     * switchCreature - creates a cast to the world, creates a new creature variable, checks if the creatures fainted and if so say it has
     * fainted, if it has not play an animation then delay the scenario by 2 act cycles, set the transparency of the image and it's
     * healthbar to 0, then checks if the idx is equal to 1 and if so Lapras is the new creature for player one if not then Pidgeot is,
     * run the switchedIn method, set the turn number to 1. 
     * 
     * @param The parameter idx is used for switching to the specific creature of the players choice
     * @return Nothing is returned         
     */
    public void switchCreatures(int idx)
    {
        CreatureWorld playerWorld = ( (CreatureWorld)getWorld() );
        
        Creature switchCreature =  playerWorld.getNewOneCreature(idx);
        
        if(switchCreature.getHealthBar().current == 0)
        {
            JOptionPane.showMessageDialog(null, "the creature you've chosen has fainted, you must choose something else");
        }
        else
        {
            while( getX() < getWorld().getWidth() - 1)
            {
                setLocation(getX() + 5, getY() + 5);
                
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
                
            getHealthBar().getImage().setTransparency(0);
                
            if( idx == 1)
            {
                playerWorld.changePlayerTwo("Lapras");
            }
            else
            {
                playerWorld.changePlayerTwo("Pidgeot");
            }
            switchedIn();
            
            playerWorld.setTurnNumber(1);
        }
    }

    /**
     * TODO (137): Declare a method called switchedIn that will be accessed by
     *          other classes, has no return type, and no parameters
     *          
     * TODO (138): Set the transparency of the image of Pikachu and the
     *          transparency of the image of the health bar to full
     *          
     * TODO (139): Declare a loop that will repeat while the x location of Pikachu
     *          is greater than the width of the world minus half the width of the image of Pikachu
     *          
     *      TODO (140): Set the location of Pikachu to the current x location minus 5
     *               and the current y location
     *               
     *      TODO (141): Delay the scenario by two cycles
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        
        getHealthBar().getImage().setTransparency(255);
        
        while(getX() > getImage().getWidth()/2)
        {
            setLocation(getX() - 5, getY() - 5);
            
            Greenfoot.delay(2);
        }
    }
}
