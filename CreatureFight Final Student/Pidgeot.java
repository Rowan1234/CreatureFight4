import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * TODO (153): Copy all code below public class Lapras and paste it below.
 *          You will make a few changes to the code to make it appropriate for
 *          Lapras. These are listed in order from top to bottom:
 *              - Change the constructor to say Pidgeot instead of Lapras
 *              - Pidgeot has 800 points of health
 *              - Pidgeot's type is Flying
 *              - Show text that Pidgeot has fainted when its health bar's value is 
 *                less than or equal to 0
 *                  - When Pidgeot faints, the second thing you should be checking is if getNewTwoCreature 
 *                    at 1 still has health
 *                      - You should be switching to Creature at index 1 if this is the case
 *              - Lapras's second attack...
 *                  - if used against a grass type...
 *                      - Should do two times 55 points of damage (DON'T DO THE MATH! Write the math expression)
 *                      - Should display that the attack is super effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - otherwise, if used against a rock type...
 *                      - Should do half of 55 points of damage (DON'T DO THE MATH!)
 *                      - Should display that the attack is not very effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - Delete the next otherwise if
 *                  - otherwise...
 *                      - Should do 55 points of damage
 *              - In switchCreature...
 *                      - In the else condition...
 *                          - Change player two to Lapras
 *              
 */
public class Pidgeot extends Creature
{
      /**
     * Constructor for objects of class Lapras
     * 
     * @param w is a reference to the world that Pidgeot gets added to
     * @return An object of type Pikachu
     */
    public Pidgeot(World w)
    {
        super(800, 2, "Flying");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 100, 25 );
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - makes a cast to creature world, checks if the healthbar of this creature is lower or equal to 0 and if so, show that this
     * pokemon has fainted then delay by 30 seconds, checks if the players second creatures healthbar is over 0 and if so switch to
     * creature 1 set the turn number to 2 remove the text and remove this object,checks if the players first creatures healthbar 
     * is over 0 and if so switch to creature 2 set the turn number to 2 remove the text and remove this object.
     * 
     * @param  There are no parameters
     * @return Nothing is returned
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();

        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Pidgeot has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            
            //TODO (142): If the current health of the health bar of the new two creature at index 1 in player world is greater than 0...
            if(playerWorld.getNewTwoCreature(1).getHealthBar().current > 0)
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
            else if(playerWorld.getNewTwoCreature(0).getHealthBar().current > 0)
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
            if(enemyType == "Grass")
            {
                //TODO (111): The enemy receives zero damage
                enemy.getHealthBar().add( -55 * 2 );
                
                /**
                 * TODO (112): Show text that states the attack has no effect at a x location of half the width of the world 
                 *          and a y location of half the height of the world plus 26 pixels
                 */
                getWorld().showText("The attack was super effective", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                
                //TODO (113): Delay the scenario by 30 pixels
                Greenfoot.delay(30);
            }
            //TODO (114): If the enemy type equals (ignoring case) Grass...
            else if(enemyType == "Rock")
            {
                //TODO (115): The enemy only receives half damage of the normal attack (65 points). DON'T DO THE MATH! Just type the mathematical expression you would use
                enemy.getHealthBar().add( -55/2 );
                
                /**
                 * TODO (116): Show text that states the attack was not very effective at a x location of half the width of the world 
                 *          and a y location of half the height of the world plus 26 pixels
                 */
                getWorld().showText("The attack is not very effective", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                
                //TODO (117): Delay the scenario by 30 pixels
                Greenfoot.delay(30);
            }
            //TODO (118): Otherwise...
            else
            {
                enemy.getHealthBar().add( -55 );
            }
        }

        playerWorld.setTurnNumber(1);
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
            setLocation(getX() - 1, getY() - 2);
            
            Greenfoot.delay(1);
        }
        
        setLocation(originalX, originalY);
    }
    

    /**
     * switchCreature - creates a cast to the world, creates a new creature variable, checks if the creatures fainted and if so say it has
     * fainted, if it has not play an animation then delay the scenario by 2 act cycles, set the transparency of the image and it's
     * healthbar to 0, then checks if the idx is equal to 1 and if so pikachu is the new creature for player one if not then lapras is,
     * run the switchedIn method, set the turn number to 1. 
     * 
     * @param The parameter idx is used for switching to the specific creature of the players choice
     * @return Nothing is returned
     *          
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
                
            if( idx == 0)
            {
                playerWorld.changePlayerTwo("Pikachu");
            }
            else
            {
                playerWorld.changePlayerTwo("Lapras");
            }
            switchedIn();
            
            playerWorld.setTurnNumber(1);
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
        
        while(getX() > getImage().getWidth()/2)
        {
            setLocation(getX() - 5, getY() - 5);
            
            Greenfoot.delay(2);
        }
    }   
}
