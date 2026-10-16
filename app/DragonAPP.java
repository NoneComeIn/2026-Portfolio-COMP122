import java.util.ArrayList;
import java.util.Stack;

/** Application class. Mostly for testing atm. 
@author Lincoln
@author Penwarden*/
public class DragonAPP
{
   private static Stack<Mercenary> mercenaryStack = new Stack<>();
   public static void deployMerc(Mercenary merc)
   {
      mercenaryStack.push(merc);
      System.out.println("Deployed: " + merc.getName());
   }
   public static Mercenary dismissMercenary()
   {
      if(mercenaryStack.isEmpty())
      {
         System.out.println("No mercenerys left to dismiss.");
         return null;
      }
      Mercenary dismissed = mercenaryStack.pop();
      System.out.println("Dismissed: " + dismissed.getName());
      return dismissed;
   }
   public static Mercenary lookAtTopMerc()
   {
      if(mercenaryStack.isEmpty())
      {
         return null;
      }
      else
      {
         return mercenaryStack.peek();
      }
   }
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
      
      deployMerc(mercs[0]);
      deployMerc(mercs[1]);
      
      for (Gemstone g: gems) hoard.add(g);
      for (Relic r: labyrinth.getRelics()) hoard.add(r);
      for (Mercenary m: mercs) hoard.add(m);
      
      hoard.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
      summarise(hoard);
      showStack();
   }
   public static void showStack()
   {
      System.out.println("\nMercenary Stack Order");
      if (mercenaryStack.isEmpty())
      {
         System.out.println("...empty...");
         return;
      }
      Object[] arr = mercenaryStack.toArray();
      for (int i = arr.length - 1; i >= 0; i--)
      {
         Mercenary m = (Mercenary) arr[i];
         String pos = (i == arr.length - 1)
            ? "Top"
            : "Bottom";
         System.out.println((arr.length -i) + ". " + pos + ". " + m.getName());
      }
   }
   
   
   public static void summarise(ArrayList<Entity> hoard){
      System.out.println("                                LAIR HOARD & SECURITY EVALUATION SUMMARY");
      System.out.println("|----------------------------------------------------------------------------------------------------------|");
      for(Entity e: hoard) {
         System.out.println(String.format("| %-9s | %-20s | %-30s | %-6s | %-7s | %-17s |", "", "", e.getAttribute1(), "", "", ""));
         System.out.println(String.format("| %-9s | %-20s | %-30s | %-6s | %-7s | %-17s |", e.getType(), e.getName(), e.getAttribute2(), e.getValueString(), e.getThreatString(), e.getAction()));
         System.out.println(String.format("| %-9s | %-20s | %-30s | %-6s | %-7s | %-17s |", "", "", e.getAttribute3(), "", "", ""));
         System.out.println("|----------------------------------------------------------------------------------------------------------|");
      }
   }
}