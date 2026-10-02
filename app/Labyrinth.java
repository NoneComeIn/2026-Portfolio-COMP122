import java.util.ArrayList;
import java.util.Collections;

public class Labyrinth{
   /** Labyrinth class made by Lincoln.*/
   private ChamberI entrance;
   
   public static void main(String[] args){
      //FIX ME this is jsut for debug. Remove from final.
      Labyrinth tester = new Labyrinth();
      tester.display();
   }
   
   public Labyrinth(){
      entrance = LabyrinthIO.readLabyrinthFile("DemoLabyrinth.txt"); //The big labyrinth shown in the project file.
      System.out.println("Done reading");
//       entrance = LabyrinthIO.readLabyrinthFile("TestSave.txt");      //The little labyrinth I made for testing.
      updateReachable();
   }
   
   /** Recursively traverses the labyrinth and updates which nodes are reachable.
   Might break if the graph contains a cycle.*/
   private void updateReachable(){
      for (ChamberI room: flatten()){
         room.setReachable(false);
      }
      System.out.println("Set all unreach");
      traverseLabyrinth(entrance, 0);
   }
   private void traverseLabyrinth(ChamberI room, int depth){
      if (depth < 10 && room instanceof Chamber && !((Chamber)room).isBlocked()) 
         for (ChamberI e: ((Chamber)room).getPassages()) 
            traverseLabyrinth(e, depth + 1);
      room.setReachable(true);
   }
   
   
   /** Put all roooms into a 1D ArrayList useful for iterating through*/
   private ArrayList<ChamberI> flatten(){
      ArrayList<ChamberI> toVisit = new ArrayList<ChamberI>();
      ArrayList<ChamberI> visited = new ArrayList<ChamberI>();
      toVisit.add(entrance);
      for (int i = 0; !toVisit.isEmpty(); i++){ 
         ChamberI room = toVisit.get(0);
         toVisit.remove(0);
         
         if (!visited.contains(room)){ //only save each room once.
            visited.add(room);
            if (room instanceof Chamber) 
               Collections.addAll(toVisit, ((Chamber)room).getPassages());
         }
      }
      return visited;
   }
   
   
   /** Displays the nodes of the labyrinth*/
   public void display(){
      ArrayList<ChamberI> rooms = flatten();
      for (int i = 0; i < rooms.size(); i++){
         
         if (rooms.get(i) instanceof Relic) 
            System.out.println("\nRoom " + (char)(i+65) + " contains a " + rooms.get(i));
         else {
            System.out.println("\nRoom " + (char)(i+65) + rooms.get(i));
            for (ChamberI c: ((Chamber)rooms.get(i)).getPassages()) 
               System.out.println("\tConnected to room " + (char)(rooms.indexOf(c)+65));
         }
      }
   }
   
   
   /** returns the relics of a labyrinth*/
   public Relic[] getRelics(){
      ArrayList<Relic> relics = new ArrayList<Relic>();
      for (ChamberI room: flatten()) if (room instanceof Relic) 
         relics.add((Relic)room);
      return relics.toArray(new Relic[0]);
   }
}