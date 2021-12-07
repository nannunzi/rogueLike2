// Game.java
// contains logic for running the Game
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import ansi_terminal.*;

public class Game {
    private World world;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;
    private ArrayList<Portal> portal;

    public Game() {
	Terminal.cookedMode();
        world  = new World();
	player = new Player(world.getCurrentRoom().getPlayerStart());
        boxes = world.getCurrentRoom().getBoxes();
        enemies = world.getCurrentRoom().getEnemies();
	portal= world.getCurrentRoom().getPortal();
	Terminal.rawMode();
    }
    public Game(String sav)throws FileNotFoundException{
		Scanner read = new Scanner(new FileReader(sav));
		world = new World(read);
		player = new Player(world.getCurrentRoom().getPlayerStart());
    }

    // prints a help menu to the left of the map
    private void showHelp() {
        String[] cmds = {"Commands:",
                         "---------",
                         "Move: Arrow Keys",
                         "Pickup an item: p",
			 "Enter portal: f",
                         "Drop an item: d",
                         "List items: l",
                         "Equip weapon: w",
                         "Equip armor: a",
			 "Save: s",
                         "Quit: q"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, world.getCurrentRoom().getCols());
            System.out.print(cmds[row]);
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    private void setStatus(String mesg) {
        // clear anything old first
        Terminal.warpCursor(world.getCurrentRoom().getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(world.getCurrentRoom().getRows(), 0);
        System.out.print(mesg);
    }

    // code for when the player tries to pickup an item
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
    private void drop() {
        if (checkForBox() == null) {
            Item dropped = player.getInventory().drop();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }
/**
This will warp the user to another toom when they are standing on a portal
*/
    private void warp() {
        Portal thing = checkForPortal();
        if (thing == null) {
            setStatus("No portal here");
            Terminal.pause(1.25);
        } else {
		Terminal.cookedMode();
             	int whoop=0;
		Scanner useless=new Scanner(System.in);
		while(!(whoop==1||whoop==2||whoop==3))
		{
			setStatus("Where do you wish to warp to? Room (1), (2), or (3)? press enter with your choice.");	
			whoop=useless.nextInt();
			if (whoop==1||whoop==2||whoop==3){
			portal.remove(thing);
			this.world.setCurrentRoom(whoop-1);
			enemies=world.getCurrentRoom().getEnemies();
			boxes=world.getCurrentRoom().getBoxes();
			portal=world.getCurrentRoom().getPortal();
			redrawMapAndHelp();
			}
			else{
			setStatus("invalid room");
			}
		
                }
		Terminal.rawMode();
            Terminal.pause(1.25);
        }   
    }
/**
This saves the game that the user has currently started, saving various locations of objects on the map.
@throws IOException if the input to the file fails
*/
	public void save()throws IOException
	{
	String fileName= this.player.getName()+".sav";
	PrintWriter p = new PrintWriter(new FileWriter(fileName));
	world.save(p);
}

    // handle the key which was read - return false if we quit the game
    private boolean handleKey(Key key) {
        switch (key) {
            case p:
                pickup();
                break;

            case l:
                player.getInventory().print();
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
                redrawMapAndHelp();
                break;
	    case f: 
		warp();
		//redrawMapAndHelp();
		break;
	    case s:
		try{ 
		this.save();
		
		}catch(IOException e){
		System.out.println("couldnt save. looks like youre on your own.");}
		break;

            // handle movement
            case LEFT: player.move(0, -1, world.getCurrentRoom());
                break;
            case RIGHT: player.move(0, 1, world.getCurrentRoom());
                break;
            case UP: player.move(-1, 0, world.getCurrentRoom());
                break;
            case DOWN: player.move(1, 0, world.getCurrentRoom());
                break;

            // and finally the quit command
            case q:
                return false;
        }

        return true;
    }

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        world.getCurrentRoom().draw();
        showHelp();
    }

    // returns a Box if the player is on it -- otherwise null
    private Box checkForBox() {
        Position playerLocation = player.getPosition();

        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
            }
        }

        return null;
    }
/**
Checks to see if the player is on a portal, if so the process of warping them to another room begins.
@return the portal that the user is standing on
*/
    private Portal checkForPortal() {
        Position playerLocation = player.getPosition();

        for (Portal p : portal) {
            if (playerLocation.equals(p.getPosition())) {
                return p;
            }
        }

        return null;
    }

    // check for battles and return false if player has died
    private boolean checkBattles() {
        Position playerLocation = player.getPosition();

        // look for an enemy that is close
        Enemy opponent = null;
        for (Enemy enemy : enemies) {
            if (playerLocation.isAdjacent(enemy.getPosition())) {
                opponent = enemy;
            }
        }

        // now do the battle
        if (opponent != null) {
            opponent.setBattleActive();
            return player.fight(opponent, world.getCurrentRoom(), enemies);
        }

        return true;
    }

    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();

        boolean playing = true;
        while (playing) {
            // draw the entities
            for (Box box : boxes) {
                box.draw();
            }
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
	    for (Portal portal : portal){
		portal.draw();
	    }
            player.draw();

            // read a key from the user
            Terminal.warpCursor(world.getCurrentRoom().getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(world.getCurrentRoom());
            }

            // check for battles
            if (checkBattles() == false) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName());
            }
        }
    }
}

