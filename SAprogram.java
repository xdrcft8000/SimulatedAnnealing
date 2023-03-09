//21012040 - Tayyeb Rafique


import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

public class SAprogram {



  public static void main(String[] args) {
    //Start Timer
    long startTime = System.nanoTime();

    //initialise size variables and counter for reader file
    int y = 2;
    int size = 0;

    //declare new Tournament to fill
    Tournament T = new Tournament();

    //Readfile
    try {
      File myObj = new File(args[0]);
      Scanner myReader = new Scanner(myObj);
      size = Integer.parseInt(myReader.nextLine());
      T.Tournamentu(size);
      while (myReader.hasNextLine()){
        String data = myReader.nextLine();

        //Store competitor names in hashmap
        if (y>1 && y<size+2){
          String[] name = data.split(",");
          T.setName(Integer.parseInt(name[0])-1,name[1]);
        }

        //Store tournment results in a 2D matrix
        if (y>size+2){
          int[] edge = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();
          for(int i= 0; i<3; i++){
          }
          T.setT(edge[1]-1,edge[2]-1,edge[0]);
        }
        y++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    //Create an initial ranking
    Ranking R = new Ranking(T);

    //Score the initial ranking
    int initKemeny = R.calcKem();

    Random rand = new Random();

    //SA algorithm starts here (TL declared later on as 5000)
    int num_non_improve = 0;
    int num_non_improve_limit = 80;
    double ti = 1;
    double a = 0.9;

    //outer loop
    while(num_non_improve < num_non_improve_limit){

      //inner loop
      for (double tl= 5000; tl>0; tl--){

        //choose an random index in the ranking
        int index = rand.nextInt(T.size()-1);

        //swap two adjacent competitors in the ranking and get the new score
        int newKs = R.swapADJ(index);

        //compare the old and new scores to get deltaC
        int deltaC = newKs - R.getKemeny();

        //if no change in kScore, the swap stays, but num_non_improve increased
        if (deltaC == 0){
          R.setKemeny();
          ++num_non_improve;
        }

        //if the change is negative, the new ranking is better, so it replaces the current ranking
        else if(deltaC < 0){
          R.setKemeny();
          num_non_improve = 0;
        }

        //if the change is positive, the new ranking is worse, and we
        //accept it based of a function of deltaC and current temp
        //as well as incrementing num_non_improve
        else if ( Math.random()<(Math.exp(-deltaC/ti))){
          R.storeBest();
          R.setKemeny();
          ++num_non_improve;
        }

        if (num_non_improve >= num_non_improve_limit){
          break;
        }
      }

      //decay tempreture
      ti *= a;
    }


    R.restoreBest();
    int [] bestRank = R.getRank();

    long endTime = System.nanoTime();


    System.out.println("Runtime: "+((endTime-startTime)/1000000) + "ms");
    System.out.println("Final Kemeny Score of this ranking: " + R.getKemeny());
    System.out.print("Position | Competitor No. |       Name      |");
    System.out.println(" ");
    for(int i = 0; i<T.size(); i++){
      System.out.print("    "+(i+1)+"    " + "       "+(bestRank[i]+1)+"        "  +T.getName(bestRank[i]));
      System.out.println(" ");
    }
    System.out.println("Please open 'GRAPHS.html' to see full size graphs.");
  }
}
