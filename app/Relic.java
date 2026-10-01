public class Relic extends Treasure
{
   private boolean relicIsReachable; // FIX THIS - Change to chamber.reachable()
   public Relic(String name, String origin, Chamber chamber)
   {
      super(name, origin);
   }
   public int calcValue()
   {
      if (relicIsReachable)
      {
         return 250;
      }
      else
      {
         return 10000;
      }
   }
}