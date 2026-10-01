public class Relic extends Treasure
{
   private Chamber currentChamber;
   public Relic(String name, String origin, Chamber chamber)
   {
      super(name, origin);
      this.currentChamber = chamber;
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
   public String toString(){//This method is property of Lincoln
      return "".format("\tContains a $%d Relic: %s\n\t\t\"%s\"", calcValue(), name, origin);
   }
}