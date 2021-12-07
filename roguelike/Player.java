// Player.java
import java.io.PrintWriter;
import ansi_terminal.*;
import java.util.Scanner;

public class Player extends Character {
    private Inventory items;
    private String name;
    public Player(Position start) {
        // our starting details
	 super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);
	 System.out.println("I see you are new here, what is your name?");
 	 Scanner literallyjustthis= new Scanner(System.in);
         String namae = literallyjustthis.nextLine();
	this.name = namae;
         
        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        // TODO make up your own starting equipment!
        items.addAndEquip(new Item(ItemType.Weapon, "Iron Dagger", 5, 12, 7));
        items.addAndEquip(new Item(ItemType.Armor, "Leather Armor", 15, 20, 3));
    }
	public Player(Position start, Scanner s){
	super(start.getRow(), start.getcol(), '@', Color.CYAN, 50);
	this.name=s.nextLine();
	super.setHealth(Integer.parseInt(s.nextLine()))
	this.items=new Inventory(s);
}
    @Override
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }

    public Inventory getInventory() {
        return items;
    }

	public void save(PrintWriter w){
	w.println(this.name);
	w.println(this.health);
	this.items.save(w);
}
}

