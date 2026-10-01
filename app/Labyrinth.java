import java.util.ArrayList;

public class Labyrinth{
   /** Labyrinth class made by Lincoln.*/
   Chamber entrance;
   
   public static void main(String[] args){
      //FIX ME this is jsut for debug. Remove from final.
   }
   
   public Labyrinth(){
      // FIX ME this hardcoded mini labyrinth is just for testing.
      Chamber leaf = new Chamber(new Relic("test item", "I found it in my shoe"));
      Chamber node = new Chamber(20);
      node.passages.add(leaf);
      entrance = new Chamber(30);
      entrance.passages.add(node);
      entrance.passages.add(leaf);
   }
   
   /** Traverses the labyrinth and updates which nodes are reachable.
   Breaks if the graph contains a cycle.*/
   private void updateReachable(){
      ArrayList<Chamber> rooms = new ArrayList<Chamber>();
      rooms.add(entrance);
      
      while (!rooms.isEmpty()){ 
         Chamber room = rooms.get(0);
         rooms.remove(0);
         rooms.addAll(room.passages);
         
         room.reachable = false;
      }
      traverseLabyrinth(entrance, 0);
   }
   private void traverseLabyrinth(Chamber room, int depth){
      room.reachable = true;
      room.depth = depth;
      room.passages.forEach(e -> {if (!room.isBlocked()) traverseLabyrinth(e, depth + 1);});
   }
   
   
   /** Displays the nodes of the labyrinth*/
   public void displayLabyrinth(){
      ArrayList<Chamber> rooms = new ArrayList<Chamber>();
      rooms.add(entrance);
      
      while (!rooms.isEmpty()){ 
         Chamber room = rooms.get(0);
         rooms.remove(0);
         rooms.addAll(room.passages);
         
         System.out.println(room);
      }
   }
   
   
   
   
   private class Chamber{
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
      public Chamber(Relic relic){
         this.relic = relic;
      }
      
      
      public boolean isBlocked(){
         return danger > 100;
      }
      public boolean isLeaf(){
         return passages.isEmpty();
      }
      public String toString(){
         return "".format("is %sblocked, is %sreachable%s.", isBlocked() ? "" : "not ", reachable ? "" : "not ", isLeaf() ? ", and contains a relic" : "");
      }
   }
}