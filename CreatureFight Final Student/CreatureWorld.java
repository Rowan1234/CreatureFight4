/**
 * Name: Rowan N. Mullin
 * Teachers name: Mr. Hardman
 * Date last modified: 12/13/2018
 * class: software developement
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreatureWorld extends World
{
    //TODO (5): Change the data type of these two variables from Creature to String
    public String playerOneCreature;
    private String playerTwoCreature;
    
    private int turnNumber;
    
    private Menu oneFightMenu;
    private Menu oneSwitchMenu;
    private Menu twoFightMenu;
    private Menu twoSwitchMenu;
    
    private boolean playerOneMenusAdded;
    private boolean playerTwoMenusAdded;
    
    private String playerOneName = "1";
    private String playerTwoName = "2";
    
    //TODO (80): Declare two Creature instance arrays, one for the collection of playerOneCreatures and one for the collection of playerTwoCreatures
    private Creature[] playerOneCreatures;
    private Creature[] playerTwoCreatures;
    
    /**
     * Default constructor for objects of class CreatureWorld.
     * 
     * @param There are no parameters
     * @return an object of class CreatureWorld
     */
    public CreatureWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 400, 1);
        
        //TODO (6): Change these two variables to store the names of the playerOne and playerTwo Creatures, Charmander and Pikachu respectively
        playerOneCreature = "Charmander";
        playerTwoCreature = "Pikachu";
        
        /**
         * TODO (81): Initialize the playerOneCreatures array to a new Creature array that stores a 
         *          new Charmander object, a new Golem object, and a new Ivysaur object
         */
        playerOneCreatures = new Creature[] {new Charmander(this), new Golem(this), new Ivysaur(this) };
        
        /**
         * TODO (154): Initialize the playerTwoCreatures array to a new Creature array that stores a 
         *          new Pikachu object, a new Lapras object, and a new Pidgeot object
         */
        playerTwoCreatures = new Creature[] {new Pikachu(this), new Lapras(this), new Pidgeot(this)};

        prepareCreatures();

        turnNumber = 0;
        
        playerOneMenusAdded = false;
        playerTwoMenusAdded = false;
        
        Greenfoot.start();
    }
    
    /**
     * act - creates 2 new boolean variables, checks if turnNumber is equal to 0 and if so gets the players to create their names and
     * then sets the turnNumber to 1, if the turnNumber is equal to 1 say it's player one's turn and then remove the message, else say
     * it's player two's turn and then remove the message, if the player one menus are not added check if player one creature is 
     * charmander and if so add his fight and switch menu, if if player one creature is golem and if so add golems fight and switch menu
     * if it's neither then add Ivysaurs fight and switch menu, checks if the player two menus have been added and if so if pikachu was
     * chosen load in his fight and switch menus, if lapras was chosen load in his menus if the 3rd option was chosen load in pidgeots
     * menus, checks if player one or two has lost the match and if one of the players has won then remove all objects and say the other
     * player won the match.
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        List allObjects = getObjects(null);
        
        //TODO (7): Declare two local boolean variables called playerOneLose and playerTwoLose that are both initialized to true
        boolean playerOneLose = true;
        
        boolean playerTwoLose = true;
        
        if( turnNumber == 0 )
        {
            playerOneName = JOptionPane.showInputDialog( "Please enter your name, Player One:", null );
            playerTwoName = JOptionPane.showInputDialog( "Please enter your name, Player Two:", null );
            turnNumber = 1;
        }
        else if( turnNumber == 1 )
        {
            showText( "Your turn, " + playerOneName, getWidth()/2, getHeight()/2 );
            showText( "", getWidth()/2, getHeight()/2 + 26 );
        }
        else
        {
            showText( "Your turn, " + playerTwoName, getWidth()/2, getHeight()/2 );
            showText( "", getWidth()/2, getHeight()/2 + 26 );
        }

        if( playerOneMenusAdded == false )
        {
            //TODO (95): If playerOneCreature equals (ignoring case) "Charmander"...
            if(playerOneCreature == "Charmander")
            {
                //TODO (96): Place the oneFightMenu and oneSwitchMenu initializations for Charmander inside this if statement
                oneFightMenu = new Menu(" Fight ", " Scratch \n Flamethrower ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu(" Switch ", " Golem \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            //TODO (97): Otherwise, if playerOneCreature equals (ignoring case) "Golem"...
            else if(playerOneCreature == "Golem")
            {
                //TODO (98): Set oneFightMenu to a new Fight menu that has options for Tackle and Earthquake
                oneFightMenu = new Menu(" Fight ", " Tackle \n Earthquake ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                //TODO (99): Set oneSwitchMenu to a new Switch menu that has options to change to Charmander or Ivysaur
                oneSwitchMenu = new Menu(" Switch ", " Charmander \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            //TODO (100): Otherwise...
            else
            {
                //TODO (101): Set oneFightMenu to a new Fight menu that has options for Tackle and Razor Leaf
                oneFightMenu = new Menu(" Fight ", " Tackle \n Razor Leaf ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                //TODO (102): Set oneSwitchMenu to a new Switch menu that has options to change to Charmander or Golem
                oneSwitchMenu = new Menu(" Switch ", " Charmander \n Golem ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
             }
                
            
            addObject(oneFightMenu, 173, getHeight() - 100 );
            addObject(oneSwitchMenu, 241, getHeight() - 100);
            
            playerOneMenusAdded = true;
        }

        if( playerTwoMenusAdded == false )
        {
            //TODO (168): If playerTwoCreature equals (ignoring case) "Pikachu"...
            if(playerTwoCreature == "Pikachu")
            {
                //TODO (169): Place the twoFightMenu and twoSwitchMenu initializations for Pikachu inside this if statement
                twoFightMenu = new Menu(" Fight ", " Tackle \n Thunderbolt ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu(" Switch ", " Lapras \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            
            }
            //TODO (170): Otherwise, if playerTwoCreature equals (ignoring case) "Lapras"...
            else if( playerTwoCreature == "Lapras")
            {
                //TODO (171): Set twoFightMenu to a new Fight menu that has options for Tackle and Hydro Pump
                twoFightMenu = new Menu(" Fight ", "Tackle \n Hydro Pump", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                
                //TODO (172): Set twoSwitchMenu to a new Switch menu that has options to change to Pikachu or Pidgeot
                twoSwitchMenu = new Menu(" Switch ", " Pikachu \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            //TODO (173): Otherwise...
            else
            {
                //TODO (174): Set twoFightMenu to a new Fight menu that has options for Tackle and Wing Attack
                twoFightMenu = new Menu(" Fight ", "Tackle \n Wing Attack", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                //TODO (175): Set twoSwitchMenu to a new Switch menu that has options to change to Pikachu or Golem
                twoSwitchMenu = new Menu(" Switch ", " Pikachu \n Lapras ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            
            addObject(twoFightMenu, 131, 75);
            addObject(twoSwitchMenu, 199, 75);
            
            playerTwoMenusAdded = true;
        }
        
        //TODO (176): Declare a for loop that runs while playerOneLose is true AND index is less than the length of the playerOneCreatures array
        for(int i = 0; playerOneLose == true && i < 3; i++)
        {
            //TODO (177): If the player one creature at the current index of the array's current health is greater than 0...
            if(playerOneCreatures[i].getHealthBar().current > 0)
            {
                //TODO (178): Set playerOneLose to false
                playerOneLose = false;
            }
        }
        //TODO (179): Declare a for loop that runs while playerTwoLose is true AND index is less than the length of the playerTwoCreatures array
        for(int i = 0; playerTwoLose == true && i < 3; i++)
        {
            //TODO (180): If the player two creature at the current index of the array's current health is greater than 0...
            if(playerTwoCreatures[i].getHealthBar().current > 0)
            {
                //TODO (181): Set playerTwoLose to false
                playerTwoLose = false;
            }
        }
        //TODO (8): Change the condition of this if statement to check if playerOneLose is false
        //TODO (182): Change the condition of this if statement to check if playerOneLose is true
        if( playerOneLose == true )
        {
            removeObjects(allObjects);
            showText(playerTwoName + " wins!!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
        
        //TODO (9): Change the condition of this if statement to check if playerTwoLose is false
        //TODO (183): Change the condition of this if statement to check if playerTwoLose is true
        if( playerTwoLose == true )
        {
            removeObjects(allObjects);
            showText(playerOneName + " wins!!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }      
    }

    /**
     * prepareCreatures adds the creatures for both players to the world
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void prepareCreatures()
    {
        //TODO (10): Delete the next two lines inside the prepareCreatures method
        
        //TODO (82): Declare a loop that will allow you to access every element of the playerOneCreatures array
        for(int i = 0; i < 3; i++)
        {
            //TODO (83): If the loop index is 0...
            if(i == 0)
            {
                /**
                 * TODO (84): Add the object stored at the current index of the playerOneCreatures array
                 *          at an x location of playerOneCreatures[i].getImage().getWidth()/2 and
                 *          a y location of getHeight() - playerOneCreatures[i].getImage().getHeight()/2
                 */
                addObject( playerOneCreatures[i], playerOneCreatures[i].getImage().getWidth()/2,getHeight() - playerOneCreatures[i].getImage().getHeight()/2);
            }
            //TODO (85): Otherwise...
            else
            {
                //TODO (86): Set the transparency of the image of the Creature that is stored at the current index of the playerOneCreatures array to 0
                playerOneCreatures[i].getImage().setTransparency(0);
                
                /**
                 * TODO (87): Add the object stored at the current index of the playerOneCreatures array
                 *          at an x location of 0 and a y location of
                 *          getHeight() - playerOneCreatures[i].getImage().getHeight()/2
                 */
                addObject(playerOneCreatures[i], 0, getHeight() - playerOneCreatures[i].getImage().getHeight()/2);
            }
        }
        //TODO (155): Declare a loop that will allow you to access every element of the playerTwoCreatures array
        for(int i = 0; i < 3; i++)
        {
            //TODO (156): If the loop index is 0...
            if(i == 0)
            {
                /**
                 * TODO (157): Add the object stored at the current index of the playerTwoCreatures array
                 *          at an x location of getWidth() - playerTwoCreatures[j].getImage().getWidth()/2
                 *          and a y location of playerTwoCreatures[j].getImage().getHeight()/2
                 */
                addObject(playerTwoCreatures[i], getWidth() - playerTwoCreatures[i].getImage().getWidth()/2, playerTwoCreatures[i].getImage().getHeight()/2);
            }   
            else
            //TODO (158): Otherwise...
            {
                //TODO (159): Set the transparency of the image of the Creature that is stored at the current index of the playerTwoCreatures array to 0
                playerTwoCreatures[i].getImage().setTransparency(0);
                
                /**
                 * TODO (160): Add the object stored at the current index of the playerTwoCreatures array
                 *          at an x location of the width of the world and a y location of playerTwoCreatures[j].getImage().getHeight()/2
                 */
                addObject(playerTwoCreatures[i], getWidth(), playerTwoCreatures[i].getImage().getHeight()/2);
            }  
        } 
    }
    
    /**
     * getPlayerOne returns player one's current creature for
     * use in other parts of the code or for the user's information
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public Creature getPlayerOne()
    {
        //TODO (11): Declare a local Creature variable called currentPlayerOne initialized to null
        Creature currentPlayerOne = null;

        //TODO (88): If playerOneCreature equals (ignoring case) Charmander...
        if(playerOneCreature == "Charmander")
        {
            //TODO (89): Initialize currentPlayerOne to the first element of the playerOneCreatures array
            currentPlayerOne = playerOneCreatures[0];
        }
        //TODO (90): If playerOneCreature equals (ignoring case) Golem...
        else if(playerOneCreature == "Golem")
        {
            //TODO (91): Initialize currentPlayerOne to the second element of the playerOneCreatures array
            currentPlayerOne = playerOneCreatures[1];
        }
        //TODO (92): Otherwise...
        else
        {
            //TODO (93): Initialize currentPlayerOne to the third element of the playerOneCreatures array
            currentPlayerOne = playerOneCreatures[2];
        }
        
        
        //TODO (12): Change this to return the current player one
        return currentPlayerOne;
    }
    
    /**
     * getPlayerTwo returns player two's current creature for
     * use in other parts of the code or for the user's information
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public Creature getPlayerTwo()
    {
        //TODO (13): Declare a local Creature variable called currentPlayerTwo initialized to null
        Creature currentPlayerTwo = null;

        //TODO (161): If playerTwoCreature equals (ignoring case) Pikachu...
        if(playerTwoCreature == "Pikachu")
        {
            //TODO (162): Initialize currentPlayerTwo to the first element of the playerTwoCreatures array
            currentPlayerTwo = playerTwoCreatures[0];
        }
        //TODO (163): If playerTwoCreature equals (ignoring case) Lapras...
        else if(playerTwoCreature == "Lapras")
        {
            //TODO (164): Initialize currentPlayerTwo to the second element of the playerTwoCreatures array
            currentPlayerTwo = playerTwoCreatures[1];
        }
        //TODO (165): Otherwise...
        else
        {
            //TODO (166): Initialize currentPlayerTwo to the third element of the playerTwoCreatures array
            currentPlayerTwo = playerTwoCreatures[2];
        }    
        
        //TODO (14): Change this to return the current player two
        return currentPlayerTwo;
    }
    
    /**
     * getTurnNumber returns a number that represents which player's
     * turn it is (either player 1 or player 2)
     * 
     * @param There are no parameters
     * @return An integer that represents which player's turn it is
     */
    public int getTurnNumber()
    {
        return turnNumber;
    }

    /**
     * setTurnNumber changes the turn number to the appropriate
     * number for which player's turn it is
     * 
     * @param turn is the number that represents which player's turn it is
     * @return Nothing is returned
     */
    public void setTurnNumber( int turn )
    {
        turnNumber = turn;
    }

    /**
     * changeplayerOne - makes the player one creature = to creature, removes the fight menus for the current creature and sets the
     * menus added to false
     * 
     * @param creature is the string that represents which creature the player has chosen 
     * @return Nothing is returned 
     */
    protected void changePlayerOne(String creature)
    {
        playerOneCreature = creature;
        
        removeObject(oneFightMenu);
        
        removeObject(oneSwitchMenu);
        
        playerOneMenusAdded = false;
    }

    /**
     * changeplayertwo - makes the player two creature = to creature, removes the fight menus for the current creature and sets the
     * menus added to false
     * 
     * @param creature is the string that represents which creature the player has chosen 
     * @return Nothing is returned
     */
    protected void changePlayerTwo(String creature)
    {
        playerTwoCreature = creature;
        
        removeObject(twoFightMenu);
        
        removeObject(twoSwitchMenu);
        
        playerTwoMenusAdded = false;
    }

    /**
     * getNewOneCreature - returns the playeronecreature
     * 
     * @param index is the number that represents which creature is the player one creature  
     * @return Nothing is returned
     */
    public Creature getNewOneCreature(int index)
    {
        return playerOneCreatures[index];
    }

    /**
     * getNewTwoCreature - returns the playertwocreature
     *          
     * @param index is the number that represents which creature is the player two creature  
     * @return Nothing is returned
     */
     public Creature getNewTwoCreature(int index)
    {
        return playerTwoCreatures[index];
    }
    
}
