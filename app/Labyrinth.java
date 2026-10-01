import java.util.ArrayList;

public class Labyrinth{
   /** Labyrinth class made by Lincoln.*/
   private Chamber entrance;
   
   public static void main(String[] args){
      //FIX ME this is jsut for debug. Remove from final.
      Labyrinth tester = new Labyrinth();
      tester.display();
   }
   
   public Labyrinth(){
      // FIX ME this hardcoded mini labyrinth is just for testing.
      Chamber leaf = new Chamber(new Relic("test item", "I found it in my shoe"));
      Chamber leafUnreach = new Chamber(new Relic("The magic doom sword of destiny", "I mugged a protagonist"));
      Chamber node = new Chamber(200);
      node.passages.add(leaf);
      node.passages.add(leafUnreach);
      entrance = new Chamber(30);
      entrance.passages.add(node);
      entrance.passages.add(leaf);
      
      updateReachable();
   }
   
   /** Recursively raverses the labyrinth and updates which nodes are reachable.
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
   public void display(){
      ArrayList<Chamber> visited = new ArrayList<Chamber>();
      ArrayList<Chamber> rooms = new ArrayList<Chamber>();
      rooms.add(entrance);
      
      for (int i = 0; !rooms.isEmpty(); i++){ 
         Chamber room = rooms.get(0);
         rooms.remove(0);
         
         if (!visited.contains(room)){ //only print each room once.
            visited.add(room);
            rooms.addAll(room.passages);
            System.out.println("Room " + i+ room);
         }
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
         return "".format(" is %sblocked and is %sreachable.%s", isBlocked() ? "" : "not ", reachable ? "" : "not ", isLeaf() ? " Contains a relic." : "");
      }
   }
}