
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
   private String name;
   private int score;
   private Tile[] tiles = new Tile[5];
   private Tile lastTilePlayed;
   private int roundWon;

   public Player()
   {
     this.name = "";
     this.score = 0;
     this.tiles = new Tile[5];
     this.lastTilePlayed = new Tile();
     this.roundWon = 0;
    }
   public Player(String name)
   {
     this.name = name;
     this.score = 0;
     this.roundWon = 0;
     this.tiles = tiles;
     this.lastTilePlayed = lastTilePlayed;
    }
   public String getName()
   {
     return name;
    }
   public int getScore()
   {
     return score;
    }
   public Tile[] getTiles()
   {
     return tiles;
    }
   public Tile getLast()
   {
     return lastTilePlayed;
    }
   public int getRoundWon()
   {
     return roundWon;
    }
   public void setName(String name)
   {
     this.name = name;
    }
   public void setScore(int score)
   {
     this.score = score;
    }
   public void setRoundWon(int roundWon)
   {
     this.roundWon = roundWon;
    }
   public void setLast(Tile lastTilePlayed)
   {
     this.lastTilePlayed = lastTilePlayed;
    }
   public void setTiles(Tile[] tiles)
   {
     this.tiles = tiles;
    }
   /*
   public void initTile()
   {
       Tile tile;
       for(int i = 0; i < tiles.length; i ++)
       {
           switch(i)
           {
               case 0:
                 tile = new Tile(5,1);
                 tiles[i] = tile;
                 break;
               case 1:
                 tile = new Tile(4,2);
                 tiles[i] = tile;
                 break;
               case 2:
                 tile = new Tile(3,3);
                 tiles[i] = tile;
                 break;
               case 3:
                 tile = new Tile(2,5);
                 tiles[i] = tile;
                 break;
               case 4:
                 tile = new Tile(1,7);
                 tiles[i] = tile;
                 break;
           }
        }
   }
   public void initLast()
   {
       lastTilePlayed = new Tile();
   }
   */
}
