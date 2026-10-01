public class Relic extends Treasure
{
   private Chamber currentChamber;
   public Relic(String name, String origin, Chamber chamber)
   {
      super(name, origin);
   }
   public int calcValue()
   {
      if (currentChamber.getReachable())
      {
         return 250;
      }
      else
      {
         return 10000;
      }
   }
}