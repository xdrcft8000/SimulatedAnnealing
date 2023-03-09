//21012040 - Tayyeb Rafique
//Class for SAprogram.java

public class Ranking{
  private int[] R;
  private int kScore;
  private int[] newR;
  private int newKscore;
  private Tournament T;
  private int bestKemenyScore;
  private int [] bestRanking;


  //make a new ranking the size of competitors numbered 1 to max size
  public Ranking(Tournament T){
    this.T = T;
    this.R = new int[T.size()];
     for (int i= 0; i<T.size(); i++){
       R[i] = i;
     }
    }

  //calculates kemeny score from scratch
  //for each winner of a matchup in the tournament, the ranking is check to see
  //if the winner is placed before the loser
  //if the winner is first, the loop stops checking the ranking.
  //if the loser is first, that means the ranking disagrees with the result,
  //so the weight of the result is added to the kemeny score
  public int calcKem(){
    kScore = 0;
    for (int i=0;i<R.length;i++){
      for (int j=0;j<R.length;j++){
        if (T.weight(i,j) > 0){
          for (int k=0;k<R.length;k++){
            if (R[k] == i){
              break;
            }
            if (R[k] == j){
              kScore = kScore+T.weight(i,j);
              break;
            }
          }
        }
      }
    }
    bestKemenyScore = kScore;
    return kScore;
  }

  //swaps two adjacent competitors
  public int swapADJ(int index1){
    newR = R.clone();
    int index2 = index1 + 1;
    newR[index1] = R[index2];
    newR[index2] = R[index1];
    int newKS = updateKemenyADJ(index1);

    return newKS;
  }

  //subtracts the weight of the first swapped competitor's loss, and adds the
  //weight of the second competitors loss.
  public int updateKemenyADJ(int index1){
    newKscore = kScore - (T.weight(R[index1+1],R[index1]));
    newKscore = newKscore + (T.weight(R[index1],R[index1+1]));
    return newKscore;
  }

  public int[] getRank(){
    return R;
  }

  public void setKemeny(){
    kScore=newKscore;
    R = newR.clone();
  }

  public int getKemeny(){
    return kScore;
  }

  //keeps track of the best score and ranking achieved thus far
  public boolean storeBest(){
    if (bestKemenyScore>kScore){
      bestKemenyScore = kScore;
      bestRanking = R.clone();
      return true;
    }
    else{
      return false;
    }
  }

  public void restoreBest(){
    if (bestKemenyScore<kScore){
      kScore = bestKemenyScore;
      R = bestRanking.clone();
    }
  }

  //unused debug method for calculating the kemeny score of a given ranking
  public int calcKem(int [] R){
    int testkScore = 0;
    for (int i=0;i<R.length;i++){
      for (int j=0;j<R.length;j++){
        if (T.weight(i,j) > 0){
          for (int k=0;k<R.length;k++){
            if (R[k] == i){
              testkScore = testkScore+T.weight(i,j);
              break;
            }
            if (R[k] == j){
              break;
            }
          }
        }
      }
    }
    return testkScore;
  }

  //unused methods for a subrank-reversal of size 3
  public int swap3(int index1){
    newR = R.clone();
    int index2 = index1 + 2;
    newR[index1] = R[index2];
    newR[index2] = R[index1];
    int newKS = updateKemeny3(index1);

    return newKS;
  }

  //unused
  public int updateKemeny3(int index1){
    newKscore = kScore - (T.weight(R[index1+1],R[index1]) + T.weight(R[index1+2],R[index1+1]) + T.weight(R[index1+2],R[index1]) );
    newKscore = newKscore + (T.weight(R[index1+1],R[index1+2]) + T.weight(R[index1],R[index1+1]) + T.weight(R[index1],R[index1+2]));
    return newKscore;
  }

}
