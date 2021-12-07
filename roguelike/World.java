import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class World{
	private ArrayList<Room> rooms;
	private Room currentRoom;
public World()
{
	this.rooms = new ArrayList<Room>();
	rooms.add(new Room());
	this.currentRoom=rooms.get(0);
}
public World(Scanner s){
	int numRooms=Integer.parseInt(s.nextLine()); 
}
public Room getCurrentRoom(){
return this.currentRoom;
}
public void setCurrentRoom(int i){
	this.currentRoom=rooms.get(i);
}
public void save(PrintWriter p){
	p.println(rooms.size());
	for(Room r : rooms){
		r.save(p);
	}
}

}
