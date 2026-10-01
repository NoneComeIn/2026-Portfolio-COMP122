public class Mercenary extends Entity
{
   private int noOGuards;
   private int dailyRate;
   public Mercenary(String name, int noOGuards, int dailyRate)
   {
      super(name);
      this.dailyRate = dailyRate;
      this.noOGuards = noOGuards;
   }
   public int calcValue()
   {
      return noOGuards + dailyRate * 30;
   }
}