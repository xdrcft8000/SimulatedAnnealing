//21012040 - Tayyeb Rafique
//Class for SAprogram.java

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;


public class Tournament{

  private int[][] tournament;
  private Ranking rank;
  private int size;
  private HashMap<Integer,String> competitorNames;

  public Tournament(){
    competitorNames = new HashMap<Integer, String>();
  }

  public void Tournamentu(int size){
    this.size = size;
    tournament = new int[size][size];
  }

  public void setT(int i, int j, int weight){
    tournament[i][j] = weight;
  }

  public int weight(int i, int j){
    return tournament[i][j];
  }

  public int size(){
    return size;
  }

  public void setName(int index, String name){
    competitorNames.put(index,name);
  }

  public String getName(int index){
    return competitorNames.get(index);
  }

  public void printT(){
    for (int i= 0; i<size; i++){
      System.out.println(" ");
      for (int j= 0; j<size; j++){
        if (tournament[i][j] <10 ){
          System.out.print(tournament[i][j]+"  ");
        }
        else{
          System.out.print(tournament[i][j]+" ");
        }
      }
    }
    System.out.println(" ");
  }
}





// public void setRank(Ranking rank){
  //   this.rank = rank;
  // }
  // public igetRank(){
    //   return rank;
    // }
