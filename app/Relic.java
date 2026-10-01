public class Relic extends Treasure
{
   private boolean relicIsReachable;
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