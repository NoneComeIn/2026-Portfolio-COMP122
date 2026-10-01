import java.util.Random;

public class Gemstone extends Treasure
{
   private int clarityScore;
   private int weight;
   public Gemstone(String name, String origin)
   {
      super(name, origin);
      Random random = new Random();
      this.weight = random.nextInt(26);
      this.clarityScore = random.nextInt(11);
   }
   public double calcValue()
   {
      double value = (weight * clarityScore * 50) + getAvgAppraisal();
      return value;
   }
   public double getAvgAppraisal()
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
}