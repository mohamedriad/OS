package os2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class Algorithims{
  //int[] refString={7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
    int[] refString;
    int[] pages;
    int[] ar; // arr for optimal algorithim
    int[][] printMat;
    int NoOfFrames;
    int SizeOfrefString;
    int[] indexAr;
     //================
    int t1;
    int[] temp4;
    int[] temp5;
    int tempa;
    int[] tempee;
    int[] Freq;
    int[][] outpute;
    int h;
    int nofaee;
    //==================
    void fillrefString()
    {
        Random r=new Random();
        for(int i=0;i<SizeOfrefString;i++)
        {
            refString[i]=r.nextInt(99);
            
        }
    
    }
    private void print2DMat(){
        // print 2D Mat
      for(int rows=0;rows<NoOfFrames+1;rows++)
      {    
          for(int coloumns=0;coloumns<SizeOfrefString;coloumns++)
          {
              if(this.printMat[rows][coloumns] == (-99))
                  System.out.print(" -");
              else System.out.print(" "+this.printMat[rows][coloumns]);
          
          }
          System.out.println("");
      
      }
      //======================
     }
    public void input()
    {
        System.out.print("Enter the Size of the refString: ");
        Scanner sc=new Scanner(System.in);
        SizeOfrefString=sc.nextInt();
        System.out.print("Enter the No Of Frames : ");
        NoOfFrames=sc.nextInt();
        refString=new int[SizeOfrefString];
        pages=new int[NoOfFrames];
        indexAr=new int[NoOfFrames];
        fillrefString();
        printMat=new int[NoOfFrames+1][SizeOfrefString];
        
        ar = new int[NoOfFrames]; // temp array for optimal algorithm for allocating furthest one
        // filling the pages with negative numbers and the optimal array with negative numbers too
        for(int i=0;i<NoOfFrames;i++)
        {   ar[i]=-88;
            pages[i]=-99;
        }
        //===================
        temp4=new int[NoOfFrames];
        temp5=new int[NoOfFrames];
        tempee=new int[NoOfFrames];
        for(int i=0;i<NoOfFrames;i++)
            tempee[i]=-1;
        Freq= new int[99];
        for(int i=0;i<NoOfFrames;i++)
            Freq[i]=0;
        outpute= new int[NoOfFrames][SizeOfrefString];
        for(int i=0;i<NoOfFrames;i++)
        {
            for(int j=0;j<SizeOfrefString;j++)
            {
                outpute[i][j]=-1;
            }
        }
        
    }
    // optimal algorithm with its methods
    public void Optimal()
    { int FaultCounter=0;
        for(int i=0;i<SizeOfrefString;i++)
    {
        if(i<NoOfFrames)
        {
         pages[i]=refString[i];
         FaultCounter++;
        }
        else if(refStringNotExistinPages(refString[i]) && i>=NoOfFrames)
        {
            FaultCounter++;
            int FurthestEntry=getFurthestEntryFromrefArray(i);
            if(FurthestEntry==(-2))
            {
              this.pages[1]=this.refString[i];
             // System.out.println("we reached the final number");
              break;
              
            }
            // check the number inside the page array
            for(int j=0;j<NoOfFrames;j++)
            {  
                if(pages[j]==FurthestEntry)
                {
                  pages[j]=refString[i];
                 //  System.out.println(" I replaced the furthest with the current string");
                }
            }
            //====================
           
            
        }
         // filling 2d mat part for printing
           this.printMat[0][i]=refString[i];
           int col=0;
            for(int x=1;x<=NoOfFrames;x++)
            {  
                this.printMat[x][i]=this.pages[col];
                col++;
            }
         //==============================
         
        
     } 
      System.out.println("Optimal Algorithim:");
      print2DMat();
      System.out.println("No OF Faults: "+FaultCounter);
      System.out.println("=========================");
     // bring every thing back to the way it was 
      for(int i=0;i<NoOfFrames;i++)
        {   ar[i]=-88;
            pages[i]=-99;
        }
    //===============================
    }
    private boolean refStringNotExistinPages(int i)
    { 
        for(int z=0;z<NoOfFrames;z++)
        {
           if(i==this.pages[z])
           {
             return false;
           }
        }
        return true;
    }
    private int getFurthestEntryFromrefArray(int i)
    {   // when we return -2 this means that we reached the last element
        i++;
      if(i==this.SizeOfrefString)
          return -2;
      for(int k=0;k<NoOfFrames;k++)
          this.ar[k]=-88;
      int j=0;
      for(;i<this.SizeOfrefString;i++)
      {
         if(!(refStringNotExistinPages(refString[i])) && refStringNotExistsinTempArr(refString[i])) // if the number exists in the pages and if it didn't exist in the temp arr so we don't enter again if the no is repeated then do the following
         {  ar[j]=refString[i];
             
             if(j==(NoOfFrames-1))
            {
                return ar[j];
            }
             j++;
         }
      }
      if(j!=NoOfFrames) // which means i didn't fill the whole ar!
      {
       // implement method to return the number that already existed in the frame but didn't exist in the temp arr
          return NumInFrameButNotInar();
      }
      // when we return -1 this means that there is no optimal soln
        System.out.println("i returned -1 and it's not suppoused to happen");
       return -1;
    }
    private boolean refStringNotExistsinTempArr(int i)
    {
        for(int z=0;z<NoOfFrames;z++)
        {
           if(i==this.ar[z])
           {
             return false;
           }
        }
        return true;
        
     }
    private int NumInFrameButNotInar(){
      for(int i=0;i<NoOfFrames;i++)
        {
              if(PageNotExistsInar(this.pages[i])) // pages[i] not exists in ar return true which means it didn't exist in an optimal soln
              {
               return pages[i];
              }
        }
       // System.out.println("return in NumInFrameButNotINar that is impossible to happen");
        return -1;
        
    }
    private boolean PageNotExistsInar(int page)
    {
         for(int i=0;i<NoOfFrames;i++)
         {
             if(this.ar[i]==page)
                 return false;
         }
         return true;
     }
    //===============================
    //FiFo algorithm...uses refStringNotExistsinPages()
    public void fifo()
    {   int FaultCounter=0;
        int pointer=0;
        for(int i=0;i<SizeOfrefString;i++)
        {
            if(refStringNotExistinPages(refString[i]))
            {   FaultCounter++;
            if(pointer==NoOfFrames)
                    pointer=0;
                pages[pointer]=refString[i];
                pointer++;
                
            }
           // filling 2d mat part for printing
           this.printMat[0][i]=refString[i];
           int col=0;
            for(int x=1;x<=NoOfFrames;x++)
            {  
                this.printMat[x][i]=this.pages[col];
                col++;
            }
         //==============================
        } 
      System.out.println("FiFo Algorithim:");
      print2DMat();
      System.out.println("No OF Faults: "+FaultCounter);
      System.out.println("=========================");
      // bring every thing back to the way it was 
      for(int i=0;i<NoOfFrames;i++)
        {   ar[i]=-88;
            pages[i]=-99;
        }
    //===============================
    }
    //================================
    // LRU Algorithm,,,,with 2 checks of the optimal
    public void LRU()
    {  int FaultCounter=0;
        for(int i=0;i<SizeOfrefString;i++)
    {
        if(i<NoOfFrames)
        {
         pages[i]=refString[i];
         FaultCounter++;
        }
        else if(refStringNotExistinPages(refString[i])) // if it didn;t already exisit in the frames
        {  FaultCounter++;
           int FurthestEntry= GetFurthestPreviousEntryFromrefString(i);
           
           for(int j=0;j<NoOfFrames;j++)
            {  
                if(pages[j]==FurthestEntry)
                {
                  pages[j]=refString[i];
                 //  System.out.println(" I replaced the furthest with the current string");
                }
            }
        
        }
         // filling 2d mat part for printing
           this.printMat[0][i]=refString[i];
           int col=0;
            for(int x=1;x<=NoOfFrames;x++)
            {  
                this.printMat[x][i]=this.pages[col];
                col++;
            }
         //==============================
         
        
     } 
      System.out.println("LRU Algorithim:");
      print2DMat();
      System.out.println("No OF Faults: "+FaultCounter);
      System.out.println("=========================");
     // bring every thing back to the way it was 
      for(int i=0;i<NoOfFrames;i++)
        {   ar[i]=-88;
            pages[i]=-99;
        }
    //===============================
    
    }
    private int GetFurthestPreviousEntryFromrefString(int i)
    {   i--;
        // clean that array
         for(int k=0;k<NoOfFrames;k++)
          this.ar[k]=-88;
         //================
      int j=0;
      for(;i>=0;i--)
      {
         if(!(refStringNotExistinPages(refString[i])) && refStringNotExistsinTempArr(refString[i])) // if the number exists in the pages and if it didn't exist in the temp arr so we don't enter again if the no is repeated then do the following
         {  ar[j]=refString[i];
             
             if(j==(NoOfFrames-1))
            {
                return ar[j];
            }
             j++;
         }
      }
      if(j!=NoOfFrames)
      {
          return NumInFrameButNotInar();
      }
      return -1;
     }
   //===============================
    //SecondChance algorithm with its methods
    public void SecondChance()
    {   int FaultCounter=0;
        int pointer=0;
        int IndexWithZero=-99;
        //fill with 1's
        for(int y=0;y<NoOfFrames;y++)
            this.indexAr[y]=1;
        //================
        for(int i=0;i<SizeOfrefString;i++)
        {
            if(i<NoOfFrames)
            {  FaultCounter++;
                pages[i]=refString[i];
            }
            if(refStringNotExistinPages(refString[i]) && i>=NoOfFrames)
            {   FaultCounter++;
                
               if(pointer==NoOfFrames)
                    pointer=0;
                 IndexWithZero= getIndexWithZero(pointer);
                  pages[IndexWithZero]=refString[i];
                  this.indexAr[IndexWithZero]=1;
                    pointer=IndexWithZero;
                    pointer++;
                
            }
            else
            { 
               // when the number exist you make its index to 1
                int NumberThatExists= GetTheIndexOfThatNumber(refString[i]);
                indexAr[NumberThatExists]=1;
            }
           
           // filling 2d mat part for printing
           this.printMat[0][i]=refString[i];
           int col=0;
            for(int x=1;x<=NoOfFrames;x++)
            {  
                this.printMat[x][i]=this.pages[col];
                col++;
            }
         //==============================
        } 
      System.out.println("SecondChance Algorithim:");
      print2DMat();
      System.out.println("No OF Faults: "+FaultCounter);
      System.out.println("=========================");
      // bring every thing back to the way it was 
      for(int i=0;i<NoOfFrames;i++)
        {   ar[i]=-88;
            pages[i]=-99;
        }
    //===============================
    }
    private int getIndexWithZero(int pointer)
    {   int x=pointer;
           while(true)
           {    
                      if(x==NoOfFrames)
                           x =0;
                        if(indexAr[x]==1 )
                       {   
                           indexAr[x]=0;
                           
                       }
                        else
                          {    
                              return x;
                          }
                         x++;
                        
                        
               
              if(pointer==100) // it will never happen 
                  break;
           } 
                
        System.out.println("This is not suppoused to happen in GetIndexWithZero");
        return 300;
    }
    private int GetTheIndexOfThatNumber(int i)
    {
        for(int a=0;a<NoOfFrames;a++)
        {
         if(i==this.pages[a])
             return a;
        }
        System.out.println("NotSupposed to hapen in GetTheIndexOfThatNumber");
        return 900;
    }
    //===============================
    //LFU Algorithm with its methods
   int check3(int l)
   {
      int i=0;
      for( i=l;i>=0;i--)
      {
          if(refString[i]==t1)
          {
                
              return i;
  
          }
      }
      return i ;
  }
   int min1()
   {
      int mine=100;
      int z=0;
      for(int i=0;i<NoOfFrames;i++)
      {
          if(temp4[i]<mine)
          {
//              elrakmfetemp4=temp4[i];
             // FreqofLRU=Freq[temp[i]];
              mine=temp4[i];
              z=i;
          }
      }
      return z;
  }
  int Frqcheck()
   {
      int min1=100;
      int min2=100;
      int z=0;
      int d=0;
      for(int i=0;i<NoOfFrames;i++)
      {
          if(temp5[i]<min1)
          {
           min1=temp5[i];   
           z=i;
           tempa=tempee[i];
           d=1;
          }
          if(temp5[i]==min1&&d==0)
              {
                    if(temp4[i]<temp4[z])  
                    {
                        z=i;
                        tempa=tempee[i];
                        min1=temp5[i];
                    }
              }
          d=0;
      }
     return z;  
  }
   boolean checkee(int k)
    {
     for(int i=0;i<NoOfFrames;i++)
        {
     if(tempee[i]==refString[k])  
     {
         
         return true;
         
     }
        }
     return false;
    }
    void LFU()
    {
      int c5=0;
      int d4=0;
      while(c5<SizeOfrefString)
      {
          if(checkee(c5))
          {
              Freq[refString[c5]]++;
              for(int i=0;i<NoOfFrames;i++)
               {
              outpute[i][c5]=tempee[i];
               } 
               d4=1;  
          }
          if(c5<NoOfFrames)
          {
              tempee[c5]=refString[c5];
              Freq[refString[c5]]++;
               for(int i=0;i<NoOfFrames;i++)
               {
              outpute[i][c5]=tempee[i];
               } 
               d4=1;
               nofaee++;
          } 
          
          if(d4==0)
          {
            for(int i=0;i<NoOfFrames;i++)
              {
                  t1=tempee[i];
              
           int x=check3(c5);
           if(x>=0)
           {
               temp4[i]=x;
           }
              } 
             h=min1(); // el i bta3t LRU 
             for(int i=0;i<NoOfFrames;i++)
              {
                
               temp5[i]=Freq[tempee[i]];
           
              } 
             int o=Frqcheck();
             Freq[tempa]--;
             tempee[o]=refString[c5];
             Freq[refString[c5]]++;
             for(int i=0;i<NoOfFrames;i++)
               {
              outpute[i][c5]=tempee[i];
               } 
           nofaee++;
               } 
          
          c5++;
          d4=0;
      }
       System.out.println("The counters :");
      for (int i = 0; i < 99; i++) {
         
          System.out.print(Freq[i]+"  ");
      }
      System.out.println("  ");
      
      for(int i=0;i<NoOfFrames;i++)
      {
          for(int j=0;j<SizeOfrefString;j++)
          {
              System.out.print(outpute[i][j]+"  ");
            
          }
          System.out.println();
       
      }
      System.out.println("no of faults :"+nofaee);
       System.out.println("------------------------------------------------------------------------------------------------");
  }
   //================================
    //enhaned scnd chance 
    public static int[] uniqueNumbers(int[]refStr,int nof)
    {
        List unique;
        unique = new ArrayList<>();
        int count=0;
        for (int i = 0; i < refStr.length; i++) {
            boolean b=false;
            for (int j = 0; j < 10; j++) {
                if((int)unique.get(j)==refStr[i])
                {
                    b=true;
                }
                if(!b)
                {
                    unique.add(refStr[i]);
                    count++;
                }
            }
        }
        int ret[]= new int[unique.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i]=(int)unique.get(i);
        }
    return ret;
    }
    public static int EnhancedSecondChance(int[] refArr, int numberOfFrames) {
    int pageFaults = 0;
    int[] frames = new int[numberOfFrames];
    Map<Integer, String> dualBits = new HashMap();
    /*if (numberOfFrames >= refArr.length) {
        return uniqueNumbers(refArr, numberOfFrames);
    }*/
    for (int l = 0; l < refArr.length; l++) {
        if (!dualBits.containsKey(refArr[l])) {
            dualBits.put(refArr[l], "11");
        }
    }
    int i = 0;
    char c='0';
    for (int j = 0; j < frames.length; i++) {
        boolean dummyfound = false;
        for (int k = 0; k < j; k++) {
            if (frames[k] == refArr[i]){
                dummyfound = true;
            }
        }
        c='0';
        if(j%3==1)
            c='1';
        dualBits.replace(refArr[i], "1"+c);
        if (dummyfound)
            continue;
        frames[j] = refArr[i];

        pageFaults++;
        j++;
    }
    System.out.println();
    for (int p : frames)
        System.out.print(p + " ");
    System.out.println();

    for (; i < refArr.length; i++) {
        boolean inFrames = false;
        int replacmentInt = 0;
        c='0';
        if((i%3)==1)
            c='1';
        for (int j = 0; j < frames.length; j++)
            if (refArr[i] == frames[j])
                inFrames = true;
        if (inFrames) {
            continue;
        }
        boolean doubleZeroFound=false;
        for (int j = 0; j < frames.length; j++) {
            if (dualBits.get(frames[j]).equals("00") ) {
                replacmentInt = j;
                doubleZeroFound=true;
                break;
            }
        }
        if(!doubleZeroFound){
            boolean  zeroOneFound=false;
        for (int j=0;j<frames.length;j++){
            if (dualBits.get(frames[j]) .equals("01")) {
                replacmentInt = j;
                zeroOneFound=true;
                break;
            }
            else{
                dualBits.replace(frames[j],"0"+dualBits.get(frames[j]).charAt(1));
            }
        }
        if(!zeroOneFound)
        for (int j = 0; j < frames.length; j++) {
            if (dualBits.get(frames[j]).equals("00")) {
                replacmentInt = j;
                break;
            }
            }
        }
        dualBits.replace(refArr[i], "1"+c);
        frames[replacmentInt] = refArr[i];
        pageFaults++;
        for (int p : frames)
            System.out.print(p + " ");
        System.out.println();
    }
    return pageFaults;
}
    
}