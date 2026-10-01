import java.util.ArrayList;

public class Chamber{
      /** Chamber class made by Lincoln*/
      private boolean reachable = false;
      private int danger;
      private int depth;
      private Relic relic;
      /** connectedRooms A list of all connected rooms in the labyrinth. 
      Note that this is a directional graph. Connections only go one way.
      I am assuming that there are no cycles in the graph.*/
      ArrayList<Chamber> passages = new ArrayList<Chamber>();
      
      public Chamber(int danger, ArrayList<Chamber> connectedRooms){
         this.danger = danger;
         this.passages = connectedRooms;
      }
      public Chamber(int danger){
         this.danger = danger;
      }
      public Chamber(String relicName, String relicOrigin){
         this.relic = new Relic(relicName, relicOrigin, this);
      }
      
      
      public boolean isBlocked(){
         return danger > 100;
      }
      public boolean isLeaf(){
         return passages.isEmpty();
      }
      
      
      public boolean getReachable(){
         return reachable;
      }
      public int getDanger(){
         return danger;
      }
      public int getDepth(){
         return depth;
      }
      public Relic getRelic(){
         return relic;
      }
      
      
      public void setReachable(boolean reachable){
         this.reachable = reachable;
      }
      public void setDepth(int depth){
         this.depth = depth;
      }
      
      
      public String toString(){
         return "".format(" is %sblocked and is %sreachable.%s", isBlocked() ? "" : "not ", reachable ? "" : "not ", isLeaf() ? " Contains a relic." : "");
      }
   }
