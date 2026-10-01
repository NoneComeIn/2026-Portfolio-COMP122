public class Relic extends Treasure
{
   public Relic(String name, String origin, Chamber currentChamber)
   {
      super(name, origin);
      Chamber currentChamber = new Chamber(new Relic("The magic doom sword of destiny", "I mugged a protagonist", this));
   }
}