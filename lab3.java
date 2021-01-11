import java.lang.Object;
import java.util.Random;
import java.math.BigDecimal;

public class lab3
{
   public static void main(String[] args)
   {
      int[][] array = genDataSet();
      float percent, p, average, max, min;
      int i;

      percent = anon_db_average(array, 1000, .001, .01);
      System.out.println(percent);

      max = percent;
      min = percent;
      average = percent;

      for (i = 0; i <1000; i++)
      {
         percent = anon_db_average(array, 1000, .001, .01);
         System.out.println(percent);
         if (percent > max)
         {
            max = percent;
         }
         if (percent < min)   
         {
            min = percent;
         }
         average = average + percent;
      }

      average = average/1000;
      System.out.println(max);
      System.out.println(min);
      System.out.println(average);

      /*percent = db_average(array, 1000);
      System.out.println(percent);*/


   }  

   public static int[][] genDataSet()
   {
      int[][] dataset = new int[1000][2];
      Random rand = new Random();
      int senBit, i;

      //place ids in column 0 and get random ints for sensitive data in col 2
      for (i = 0; i < dataset.length; i++)
      {
         dataset[i][0] = i;
         senBit = rand.nextInt(2);
         dataset[i][1] = senBit;
      }

      return dataset;
   }

   public static BigDecimal round(float d, int decimalPlace) 
   {
      BigDecimal bd = new BigDecimal(Float.toString(d));
      bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
      return bd;
   }

   public static float db_average(int[][] D, int X)
   {
      int posVals = 0;
      float percentage = 0;
      int i;

      //loop through first X users
      for (i = 0; i < X; i++)
      {
         //check if positive
         if (D[i][1] == 1)
         {
            posVals++;
         }
      }

      percentage = (float)((float)posVals/(float)X) * (float)100.0;
 
      BigDecimal bd;
      bd = round(percentage, 2);
      
      return bd.floatValue();
   }

   public static int[][] randomize_DB(int[][] D_in)
   {
      Random rand = new Random();
      int heads, tails, flip;
      int i; 
      heads = 0;
      tails = 1;

      //loop through dataset
      for (i = 0; i < D_in.length; i++)
      {
         //flip coin
         flip = rand.nextInt(2);
         if (flip == tails)
         {
            //flip coin
            flip = rand.nextInt(2);
            if (flip == heads)
            {
               D_in[i][1] = 1;
            }
            else
            {
               D_in[i][1] = 0;
            }
         }
      }
    
      return D_in;
   }

   public static float anon_db_average(int[][] D, int X, double GS, double epsilon)
   {
      Random rand = new Random();
      return db_average(D,X)+ rand.nextDouble(GS, epsilon);
   }
}
