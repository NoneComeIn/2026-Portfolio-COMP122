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
      entrance = LabyrinthIO.readLabyrinthFile("DemoLabyrinth.txt"); //The big labyrinth shown in the project file.
//       entrance = LabyrinthIO.readLabyrinthFile("TestSave.txt");      //The little labyrinth I made for testing.
      updateReachable();
   }
   
   /** Recursively traverses the labyrinth and updates which nodes are reachable.
   Breaks if the graph contains a cycle.*/
   private void updateReachable(){
      ArrayList<Chamber> rooms = new ArrayList<Chamber>();
      rooms.add(entrance);
      
      while (!rooms.isEmpty()){ 
         Chamber room = rooms.get(0);
         rooms.remove(0);
         rooms.addAll(room.passages);
         
         room.setReachable(false);
      }
      traverseLabyrinth(entrance, 0);
   }
   private void traverseLabyrinth(Chamber room, int depth){
      room.setReachable(true);
      room.setDepth(depth);
      room.passages.forEach(e -> {if (!room.isBlocked()) traverseLabyrinth(e, depth + 1);});
   }
   
   
   /** Displays the nodes of the labyrinth*/
   public void display(){
      ArrayList<Chamber> rooms = LabyrinthIO.flatten(entrance);
      for (int i = 0; i < rooms.size(); i++){
         System.out.println("Room " + i+ rooms.get(i));
         if (rooms.get(i).passages.isEmpty()) System.out.println(rooms.get(i).getRelic());
         else for (Chamber c: rooms.get(i).passages) System.out.println("\t-Connected to " + rooms.indexOf(c));
      }
   }
   
   
   /** returns the relics of a labyrinth*/
   public Relic[] getRelics(){
      ArrayList<Chamber> rooms = LabyrinthIO.flatten(entrance);
      ArrayList<Relic> relics = new ArrayList<Relic>();
      for (Chamber room: rooms){ 
         if (room.isLeaf()) relics.add(room.getRelic());
      }
      return relics.toArray(new Relic[0]);
   }
}