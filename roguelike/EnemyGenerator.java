// this class contains a static method for creating enemies randomly
import java.util.Random;
public class EnemyGenerator{
         private static String[] descriptor =new String[]{"Poor","Old","Mystic","Repulsive","Dazzling","Twisted","Elder","Sluggish","Stinky","Weird"};
         private static EnemyType[] types = new EnemyType[]{EnemyType.Bruiser, ItemType.Theif, ItemType.Undead};

        public static Enemy generate(){
		int hp=0;
		int dam=0;
		int prot = 0; 
                Random rng = new Random();
                double rngprep= rng.nextDouble();
                rngprep = rngprep*10;
                int rngint = (int)rngprep;
                String name= "";
                name += descriptor[rngint];
                name += " ";
                double rngb=rng.nextDouble();
                rngb=rngb*3;
                rngint= (int)rngb;
		double rngc=rng.nextDouble();
                rngc=rngc*3;
	        int rngin= (int)rngc;
                switch (rngin){
                case 0: name += " Orc";
				hp = 60;
				dam = 15;
				prot = 3;	
                                  break;
                case 1: name += " Goblin";
				hp = 30;
				dam = 20;
				prot = 1;
                                  break;
                case 2: name += " Pirate";
				hp = 50;
				dam = 10;
				prot = 2
               			  break;
                EnemyType type = types[rngint];
                switch (rngint){
                        case 0: name += " Bruiser";
				prot += 1;
				hp+=5;
                                break;
                        case 1: name += " Theif";
                                hp = hp/2;
				dam += 5;
				break;
                        case 2: name += " that died last week";
                                hp-=10;
				dam=dam*2;
				break;
                        }
               rngprep=rng.nextDouble();
               rngprep=rngprep*200;
               rngint= (int)rngprep;
               int r = rngint;
		rngprep = rng.nxtDouble();
		rngprep=rngprep*500;
		rngint=(int)rngprep;
		int c = rngint;
               return new Enemy(name, r,c, hp, dam, prot);
       }
}
