import java.util.*;

public class Gemstone extends Treasure
{
   private int clarityScore;
   private int weight;
   private double avgAppraisal;
   public Gemstone(String name, String origin)
   {
      super(name, origin);
      Random random = new Random();
      this.weight = random.nextInt(26);
      this.clarityScore = random.nextInt(11);
      int[] appraisalList = new int[10];
      int sumAppraisals = 0;
      for(int i = 0; i < 10; i++)
      {
         int randAppraisal = random.nextInt(11);
         appraisalList[i] = randAppraisal;
         sumAppraisals += randAppraisal;
      }
      this.avgAppraisal = sumAppraisals / 10;
   }
   public double calcValue()
   {
      double value = (weight * clarityScore * 50) + avgAppraisal;
      return value;
   }
   public void main(String[] args)
   {
      System.out.println(calcValue());
   }
}