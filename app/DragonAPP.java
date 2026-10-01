public class DragonAPP
{
   public static void main(String[] args)
   {
      Treasure[] treasures = {
      new Gemstone("Reid's Ruby", "Not affiliated to his ex"), 
      new Gemstone("Lincolns Kidney Stone", "It just plopped out"),
      new Relic("test", "test")};
      
      Labyrinth tester = new Labyrinth();
      tester.display();
      Relic[] relics = tester.getRelics();
      
      for(Treasure treasure : treasures)
      {
         System.out.println(treasure.calcValue());
      }
   }
}