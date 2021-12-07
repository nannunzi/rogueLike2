import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
A World is a collection of 3 different rooms that the player can travel around using portals.
*/
public class World{
	private ArrayList<Room> rooms;
	private Room currentRoom;
	private int currentRoomIndex;
/**
Constructs a world with the original map, along with 2 other maps when necessary.
*/
public World()
{
	this.rooms = new ArrayList<Room>();
	rooms.add(new Room());
try{
	rooms.add(new Room(new Scanner(new FileReader("Room2.txt"))));
	rooms.add(new Room(new Scanner(new FileReader("Room3.txt"))));
}catch(FileNotFoundException f){}
	this.currentRoom=rooms.get(0);
	this.currentRoomIndex=0;
}
/**
Constructs a new room based on input from a scanner object
*/
public World(Scanner s){
	int numRooms=Integer.parseInt(s.nextLine());
	currentRoomIndex=Integer.parseInt(s.nextLine()); 
	for(int i = 0; i<numRooms; i++){
		rooms.add(new Room(s));
	}
	setCurrentRoom(currentRoomIndex);
}
/**
Gets the info about which room the user is currently in.
@return the current room of the user
*/
public Room getCurrentRoom(){
return this.currentRoom;
}
/**
Sets the room that the user is in through the ArrayList of Rooms.
*/
public void setCurrentRoom(int i){
	this.currentRoom=rooms.get(i);
	this.currentRoomIndex=i;	
}
/**
Will save the current state of the room when the user saves the game.
*/
public void save(PrintWriter p){
	p.println(rooms.size());
	p.println(currentRoomIndex);
	for(Room r : rooms){
		r.save(p);
	}
}

}
