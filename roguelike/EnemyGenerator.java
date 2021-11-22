// this class contains a static method for creating enemies randomly
import java.util.Random;
public class EnemyGenerator{
         private static String[] descriptor =new String[]{"Poor","Old","Mystic","Repulsive","Dazzling","Twisted","Elder","Sluggish","Stinky","Weird"};
         private static ItemType[] types = new ItemType[]{ItemType.Weapon, ItemType.Armor, ItemType.Other};

        public static Enemy generate(){
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
                EnemyType type = types[rngint];
                switch (rngint){
                        case 0: name += " Sword";
                                break;
                        case 1: name += " Mail";
                                 break;
                        case 2: double rngc=rng.nextDouble();
                                 rngc=rngc*3;
                                int rngin= (int)rngc;
                                switch (rngin){
                                 case 0: name += " Ball";
                                         break;
                                 case 1: name += " Ring";
                                               break;
                                 case 2: name += " Pot";
                                        break;
                                }
                                break;
                        }//this may be the messiest block of code i have ever written.
                       //sponsored by gamestop for making me work till 10:15
               rngprep=rng.nextDouble();
               rngprep=rngprep*20;
               rngint= (int)rngprep;
               int w = rngint;
               rngprep=rng.nextDouble();
               rngprep=rngprep*50
               rngint= (int)rngprep
               int v = rngint;
               rngprep=rng.nextDouble();
               rngprep=rngprep*15;
               rngint= (int)rngprep;
               int s = rngint;
               return new Item(type, name, w, v, s);
       }
}
