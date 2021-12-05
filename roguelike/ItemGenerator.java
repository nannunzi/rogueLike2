import java.util.Random;//i hate this thing
 //Dear Dr. Finlayson, may God allow you to never see this because holy crap this is ugly and i am not proud
 //of it. If you do see this, I apologize to your eyes.
 public class ItemGenerator{
         private static String[] descriptor =new String[]{"Poor","Rusted","Mystic","Fine","Dazzling","Warped","Elder","Antique","Good","Grand"};
         private static String[] materials =new String[]{"Wood","Iron","Steel","Scale","Stone","Bronze","Plastic","Titanium","Ketchup", "Tungsten"};
         private static ItemType[] types = new ItemType[]{ItemType.Weapon, ItemType.Armor, ItemType.Other};
 
        public static Item generate(){
                Random rng = new Random();
                double rngprep= rng.nextDouble();
                rngprep = rngprep*10;
                int rngint = (int)rngprep;
                String name= "";
                name += descriptor[rngint];
                name += " ";
                double rnga=rng.nextDouble();//turns out i didnt need to do this.
                rnga=rnga*10;
                rngint= (int)rnga;
                name += materials[rngint];
                name += " ";
                double rngb=rng.nextDouble();//ill fix it later
                rngb=rngb*3;
                rngint= (int)rngb;
                ItemType type = types[rngint];
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
               rngprep=rngprep*50;
               rngint= (int)rngprep;
               int v = rngint;
               rngprep=rng.nextDouble();
               rngprep=rngprep*15;
               rngint= (int)rngprep;
               int s = rngint;
                return new Item(type, name, w, v, s);
        }

}
