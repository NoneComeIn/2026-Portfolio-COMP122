import java.util.Random;

public class Gemstone extends Treasure
{
   private int clarityScore;
   private double weight;
   public Gemstone(String name, String origin)
   {
      super(name, origin);
      Random random = new Random();
      this.weight = (double)random.nextInt(26);
      this.clarityScore = random.nextInt(11);
   }
   public int getValue()
   {
      int value = (int)((weight * clarityScore * 50) + getAvgAppraisal());
      return value;
   }
   public int getAvgAppraisal()
   {
      Random random2 = new Random();
      int[] appraisalList = new int[10];
      int sumAppraisals = 0;
      for(int i = 0; i < 10; i++)
      {
         int randAppraisal = random2.nextInt(11);
         appraisalList[i] = randAppraisal;
         sumAppraisals += randAppraisal;
      }
      return sumAppraisals / 10;

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