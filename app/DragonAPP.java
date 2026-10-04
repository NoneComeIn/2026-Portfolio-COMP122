import java.util.ArrayList;

/** Application class. Mostly for testing atm. 
@author Lincoln
@author Penwarden*/
public class DragonApp
{
   public static void main(String[] args)
   {
      Labyrinth labyrinth = new Labyrinth("DemoLabyrinth.txt");
      labyrinth.display();
      
      ArrayList<Entity> hoard = new ArrayList<Entity>();
      
      Gemstone[] gems = {
         new Gemstone("Reid's Ruby", "No relation to his ex"), 
         new Gemstone("Flawless Emerald", "It just popped out"),
      };
      Mercenary[] mercs = {
         new Mercenary("Iron Golem Legion", 10, 25),
         new Mercenary("Wyrmguard Vanguard", 10, 25),
      };
      
      for (Gemstone g: gems) hoard.add(g);
      for (Relic r: labyrinth.getRelics()) hoard.add(r);
      for (Mercenary m: mercs) hoard.add(m);
      
      summarise(hoard);
   }
   
   
   public static void summarise(ArrayList<Entity> hoard){
      System.out.println("                                LAIR HOARD & SECURITY EVALUATION SUMMARY");
      System.out.println("|----------------------------------------------------------------------------------------------------------|");
      for(Entity e: hoard) {
         System.out.println("".format("| %-9s | %-20s | %-30s | %-6s | %-7s | %-17s |", "", "", e.getAttribute1(), "", "", ""));
         System.out.println("".format("| %-9s | %-20s | %-30s | %-6s | %-7s | %-17s |", e.getType(), e.getName(), e.getAttribute2(), e.getValueString(), e.getThreatString(), e.getAction()));
         System.out.println("".format("| %-9s | %-20s | %-30s | %-6s | %-7s | %-17s |", "", "", e.getAttribute3(), "", "", ""));
         System.out.println("|----------------------------------------------------------------------------------------------------------|");
      }
   }
}