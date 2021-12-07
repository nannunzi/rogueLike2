// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import ansi_terminal.*;

public class Room {
    // the grid holds the room geometry
    private String[] grid;

    // the size of the room
    private int rows;
    private int cols;

    public Room() {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;

        // the actual room geometry
        // the i cells refer to where an item should be placed at
        grid  = new String[] {
            "##################                ######################    ",
            "##              ##                ##      i           ##    ",
            "##  @           ###########       ##        *         ##    ",
            "##                       ##       ##                  ##    ",
            "##              #######  ##       ##################  ##    ",
            "##              ##   ##  ##                       ##  ##    ",
            "##################   ##  ##################       ##  ##    ",
            "                     ##                  ##       ##  ##    ",
            "                     ##   *  i           ##       ##  ##    ",
            "                     ##                  ##       ##  ##    ",
            "                     ##############  ######       ##  ##    ",
            "                                 ##  ##           ##  ##    ",
            "                                 ##  ##           ##  ##    ",
            "                       ############  ###############  ######",
            "                       ##                                 ##",
            "                       ##                                 ##",
            "    #####################                  *              ##",
            "    ##                             i                      ##",
            "    ##  #################                                 ##",
            "    ##  ##             ##                                 ##",
            "    ##  ##             #################  ##################",
            "    ##  ##                            ##  ##                ",
            "    ##  ##                            ##  ##                ",
            "    ##  ##                       #######  #######           ",
            "    ##  ##                       ##            ##           ",
            "######  ####                     ##  i  *   0  ##           ",
            "##        ##                     ##            ##           ",
            "## i  *   ##                     ################           ",
            "##        ##                                                ",
            "############                                                "
        };
    }

	public Room(Scanner s){
	rows=30;
	cols = 60;

	grid = new String[] {
	s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine(), s.nextLine()
};
}
/**
Saves the current state of the room that the user is in when they decide to save.
@param w a PrintWriter which reads the status of the room into a file to be restored later
*/
	public void save(PrintWriter w){
		for (int i = 0; i<grid.length; i++){
		w.println(this.grid[i]);		
}
	}

    // returns the player's strting location in this room
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '@') {
                    return new Position(row, col);
                }
            }
        }

        return null;
    }

    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }

        return boxes;
    }

    // returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }

        return enemies;
    }
/**
Returns a set of portals from the map, since each room is different the coordinates will be unique.
@return the list of portals that are avaialble on the map
*/
public ArrayList<Portal> getPortal() {
        ArrayList<Portal> port=new ArrayList<Portal>();
         for (int row = 0; row < rows; row++) {
             for (int col = 0; col < cols; col++) {
                 if (grid[row].charAt(col) == '0') {
                     port.add(new Portal(row, col));
                 }
             }
         }
 
         return port;
     }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                } else {
                    // whatever else, just draw a blank (we DONT draw starting items from map)
                    System.out.print(' ');
                }
            }

            System.out.print("\n\r");
        }
    }

    // returns if a given cell in the map is walkable or not
    public boolean canGo(int row, int col) {
        return grid[row].charAt(col) != '#';
    }
}



