// Main.java
// contains the main class for running the game
import java.util.Scanner;
import ansi_terminal.*;

public class Main {
    public static void main(String args[]) {
        System.out.println("would you like to play from a (s)ave or start a(n)ew?");
	Scanner save = new Scanner(System.in);
	String choos="";
	while(!(choos.equalsIgnoreCase("s")||choos.equalsIgnoreCase("n"))){
	choos=save.nextLine();
	System.out.println("Invalid answer");
}
	if choos.equalsIgnoreCase("s"){
	System.out.println("what is the file name?(please include the .sav)");
	try {
	String filename=save.nextLine();
	Game game = new Game(filename);
}catch (fileNotFoundException e){
	System.out.println("file not found. lets try a new file");
	Game game = new Game();
}
	else{
	Game game = new Game();
}
	// put termain in raw mode
        Terminal.rawMode();

        // make and run the Gam
        game.run();

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

