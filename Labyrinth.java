import java.util.ArrayList;

public class Labyrinth{
   /** Labyrinth class made by Lincoln.*/
   Chamber entrance;
   
   public Labyrinth(){
      // FIX ME this hardcoded mini labyrinth is just for testing.
      Chamber leaf = new Chamber(0);
      Chamber node = new Chamber(20);
      node.passages.add(leaf);
      entrance = new Chamber(30);
      entrance.passages.add(node);
      entrance.passages.add(leaf);
   }
   
   
   
   private class Chamber{
      /** Chamber class made by Lincoln*/
      private boolean reachable;
      private int danger;
      private Treasure relic; //FIX ME use relic type.
      /** connectedRooms A list of all connected rooms in the labyrinth. Note that this is a directional graph. Connections only go one way.*/
      ArrayList<Chamber> passages;
      
      public Chamber(int danger, ArrayList<Chamber> connectedRooms){
         this.danger = danger;
         this.passages = connectedRooms;
      }
      public Chamber(int danger){
         this.danger = danger;
         this.passages = new ArrayList<Chamber>();
      }
      public Chamber(Treasure relic){ //FIX ME use relic type.
         this.relic = relic;
      }
   }
}