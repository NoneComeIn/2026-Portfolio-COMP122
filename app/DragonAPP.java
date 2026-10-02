public class DragonApp
{
   public static void main(String[] args)
   {
      /** Mostly for testing atm. Not tracking who does what*/
      Treasure[] treasures = {
      new Gemstone("Reid's Ruby", "Not affiliated to his ex"), 
      new Gemstone("Lincolns Kidney Stone", "It just plopped out")
      };
      
      Labyrinth tester = new Labyrinth("DemoLabyrinth.txt");
      tester.display();  //PENWARDEN!!! I made this display method SUPER BEEFY!!! Your little relic loop is STUPID in comparison.
      Relic[] relics = tester.getRelics();
      
      for(Treasure treasure : treasures)
      {
         System.out.println(treasure.calcValue());
      }
      for(Relic relic : relics)
      {
         System.out.println(relic.getValue());
      }
   }
}