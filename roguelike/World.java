import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class World{
	private ArrayList<Room> rooms;
	private Room currentRoom;
	private int currentRoomIndex;
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
public World(Scanner s){
	int numRooms=Integer.parseInt(s.nextLine());
	currentRoomIndex=Integer.parseInt(s.nextLine()); 
	for(int i = 0; i<numRooms; i++){
		rooms.add(new Room(s));
	}
	setCurrentRoom(currentRoomIndex);
}
public Room getCurrentRoom(){
return this.currentRoom;
}
public void setCurrentRoom(int i){
	this.currentRoom=rooms.get(i);
	this.currentRoomIndex=i;	
}
public void save(PrintWriter p){
	p.println(rooms.size());
	p.println(currentRoomIndex);
	for(Room r : rooms){
		r.save(p);
	}
}

}
