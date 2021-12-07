// Main.java
// contains the main class for running the game
import java.util.Scanner;
import ansi_terminal.*;
import java.io.FileNotFoundException;
/**
Main runs the game, and is how everything down the line gets called
*/
public class Main {
/**
Main returns no value, and only runs the game. However, it checks to see if the user wants to start a game new or based from a previous save.
*/
    public static void main(String args[]) {
	Game game=null;
        System.out.println("would you like to play from a (s)ave or start a(n)ew?");
	Scanner save = new Scanner(System.in);
	String choos="";
	while(!(choos.equalsIgnoreCase("s")||choos.equalsIgnoreCase("n"))){
	choos=save.nextLine();
	System.out.println("Invalid answer");
}
	if (choos.equalsIgnoreCase("s")){
	System.out.println("what is the file name?(please include the .sav)");
	try {
	String filename=save.nextLine();
	game = new Game(filename);
}catch (FileNotFoundException e){
	System.out.println("file not found. lets try a new file");
	game = new Game();
}
}
	else{
	game = new Game();
}
	// put termain in raw mode
        Terminal.rawMode();

        // make and run the Gam
        game.run();

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

