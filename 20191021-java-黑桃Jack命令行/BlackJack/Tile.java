
/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile
{
    private int score;
    private int value;

    
    /*public Tile(){
      int[] score = new int[]{5,4,3,2,1};
      int[] value = new int[]{1,2,3,5,7};
    }
    public Tile(int[] score,int[] value){
      this.score = score;
      this.value = value;
    }
    public int[] getScore(){
      return score;
    }
    public void setScore(int[] score){
      this.score = score;
    }
    public int[] getValue(){
      return value;
    }
    public void setValue(int[] value){
      this.value = value;
    }*/

    /**
     *
     */
    public Tile()
    {
     this.score = 0;
     this.value = 0;
    }

    /**
     *
     * @param score
     * @param value
     */
    public Tile(int score,int value)
    {
     this.score = 0;
     this.value = 0;
    }
    public int getScore()
    {
      return score;
    } 
    public int getValue()
    {
      return value;
    }
    public void setScore(int score)
    {
      this.score = score;
    }
    public void setValue(int value)
    {
      this.value = value;
    }
    public void game()
    {
      //Tile num[] = new Tile[12,3,5,7];
      
    }
}
