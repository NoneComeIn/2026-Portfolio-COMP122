import java.util.ArrayList;

public class Chamber implements ChamberI{
   /** Chamber class made by Lincoln*/
   protected boolean reachable = false;
   private int danger;
   private Relic relic;
   /** connectedRooms A list of all connected rooms in the labyrinth. 
   Note that this is a directional graph. Connections only go one way.
   I am assuming that there are no cycles in the graph.*/
   private ArrayList<ChamberI> passages = new ArrayList<ChamberI>();
   private ArrayList<Character> path = new ArrayList<Character>();
   
   public Chamber(int danger){
      this.danger = danger;
   }
   
   
   public boolean isBlocked(){
      return danger > 100;
   }
   public boolean isLeaf(){
      return false;
   }
   
   
   public boolean getReachable(){
      return reachable;
   }
   public int getDanger(){
      return danger;
   }
   public Relic getRelic(){
      return relic;
   }
   public ChamberI[] getPassages(){
      return passages.toArray(new ChamberI[0]);
   }
   
   
   public void addConnection(ChamberI neighbour){
      passages.add(neighbour);
   }
   
   
   public void setReachable(boolean reachable){
      this.reachable = reachable;
   }
   
   
   public String toString(){
      return "".format(" is %sblocked and is %sreachable:", isBlocked() ? "" : "not ", reachable ? "" : "not ");
   }
   public String toSaveString(){
      return "true " + danger;
   }
}
