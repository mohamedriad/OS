package os2;

import java.io.IOException;

public class Os2 {

    public static void main(String[] args) throws IOException
    { Algorithims s= new Algorithims();
      s.input();
      s.Optimal();
      s.fifo();
      s.LRU();
      s.SecondChance();
      s.LFU();
      System.out.println("Enhanced Second Chance :");
      int y=s.EnhancedSecondChance(s.refString,s.NoOfFrames);
      System.out.println("TheNoOfFaults = "+y);
       
    }
    
}
