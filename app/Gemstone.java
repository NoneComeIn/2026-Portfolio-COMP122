import java.util.Random;

public class Gemstone extends Treasure
{
   private int clarityScore;
   private double weight;
   private int[] appraisals;
   
   public Gemstone(String name, String origin)
   {
      super(name, origin);
      
      Random random = new Random();
      
      this.weight = 0.5 + (double)random.nextInt(26); //Use 0.5 so it is never 0
      
      this.clarityScore = 1 + random.nextInt(10); //1 - 10
      this.appraisals = new int[10];
      for (int i = 0; i < 10; i++)
      {
         this.appraisals[i] = 10 + random.nextInt(291);
      }
   }
   public int getValue()
   {
      int value = (int)((weight * clarityScore * 50) + getAvgAppraisal());
      return value;
   }
   public int getAvgAppraisal()
   {
      int total = 0;
      for(int i : appraisals)
      {
         total += i;
      }
      return total / appraisals.length;
   }
   //Lincoln did this
   @Override
   public String getType(){
      return "Gemstone";
   }
   //Lincoln did this
   @Override
   public String getAttribute1(){
      return "".format("Weight: %.1f carats", weight);
   }
   //Lincoln did this
   @Override
   public String getAttribute2(){
      return "".format("Clarity: %d", clarityScore);
   }
   //Lincoln did this
   @Override
   public String getAttribute3() {
      return "".format("Avg Appraisal: %d", getAvgAppraisal());
   }
}