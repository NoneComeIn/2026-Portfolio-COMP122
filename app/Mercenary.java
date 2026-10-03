

/** A data class that represents mercenaries guarding the dragon's hoard
@author Penwarden
@author Lincoln*/
public class Mercenary extends Entity
{
   private int numGuards;
   private int dailyRate;
   public Mercenary(String name, int noOGuards, int dailyRate)
   {
      super(name);
      this.dailyRate = dailyRate;
      this.numGuards = noOGuards;
   }
   public int getValue()
   {
      return (numGuards + dailyRate) * 30;
   }
   //Lincoln did this
   @Override
   public int getThreat() {
      return 0;
   }
   //Lincoln did this
   @Override
   public String getType(){
      return "Mercenary";
   }
   //Lincoln did this
   @Override
   public String getAttribute1(){
      return "".format("Guard count: ", numGuards);
   }
   //Lincoln did this
   @Override
   public String getAttribute2(){
      return "".format("Daily Rate:", dailyRate);
   }
}