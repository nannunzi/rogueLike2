// this class contains a static method for creating enemies randomly
import java.util.Random;
public class EnemyGenerator{
<<<<<<< HEAD
	private static String[] descriptor =new String[]{"Poor","Old","Mystic","Repulsive","Dazzling","Twisted","Elder","Sluggish","Stinky","Weird"};
	private static EnemyType[] types = new EnemyType[]{EnemyType.Bruiser, EnemyType.Thief, EnemyType.Undead};

	public static Enemy generate(int row, int col){
=======
         private static String[] descriptor =new String[]{"Poor","Old","Mystic","Repulsive","Dazzling","Twisted","Elder","Sluggish","Stinky","Weird"};
         private static EnemyType[] types = new EnemyType[]{EnemyType.Bruiser, EnemyType.Theif, EnemyType.Undead};

        public static Enemy generate(int ro, int col){
>>>>>>> b9a35e35ba817b8a58aa2012be378f4345610b55
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
				prot = 2;
<<<<<<< HEAD
				break;
=======
               			  break;
}
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
                int r = ro;
		int c = col;
               return new Enemy(name, r,c, hp, dam, prot);
       }
>>>>>>> b9a35e35ba817b8a58aa2012be378f4345610b55
}
				EnemyType type = types[rngint];
				switch (rngint){
					case 0: name += " Bruiser";
						prot += 1;
						hp+=5;
						break;
					case 1: name += " Thief";
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
				int r = row;
				rngprep = rng.nextDouble();
				rngprep=rngprep*500;
				rngint=(int)rngprep;
				int c = col;
				return new Enemy(name, r,c, hp, dam, prot);
		}
	}

