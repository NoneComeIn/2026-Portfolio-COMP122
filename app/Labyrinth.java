import java.util.ArrayList;
import java.util.Collections;

public class Labyrinth{
   /** Labyrinth class made by Lincoln.*/
   private ChamberI entrance;
   
   public static void main(String[] args){
      //FIX ME this is jsut for debug. Remove from final.
      Labyrinth tester = new Labyrinth();
      LabyrinthIO.saveFile("TestSave2.txt", tester.flatten());
      tester.display();
   }
   
   public Labyrinth(){
      entrance = LabyrinthIO.readFile("DemoLabyrinth.txt"); //The big labyrinth shown in the project file.
//       entrance = LabyrinthIO.readFile("TestSave.txt");      //The little labyrinth I made for testing.
      updateReachable();
   }
   
   /** Recursively traverses the labyrinth and updates which nodes are reachable.
   Might break if the graph contains a cycle.*/
   private void updateReachable(){
      for (ChamberI room: flatten()){
         room.setPath(null);
      }
      traverseLabyrinth(0, entrance, new Path(-1, new ArrayList<String>()));
   }
   private void traverseLabyrinth(int depth, ChamberI room, Path pathSoFar){
      if (depth > 10) {
         System.out.println("Error too much recursion");
         return;
      }
      if (pathSoFar.isOpen() || (!pathSoFar.isOpen() && !room.getReachable())) 
         room.setPath(pathSoFar);
      if (room instanceof Chamber) 
         for (ChamberI nextRoom: ((Chamber)room).getPassages()) 
            traverseLabyrinth(depth + 1, nextRoom, pathSoFar.addStep(room.getRoomName(), ((Chamber)room).getDanger()));
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
      for (ChamberI room: rooms){
         System.out.println(room);
         System.out.println(room.getPath());
         if (room instanceof Relic) 
            System.out.println("\tContains a " + ((Relic)room).getRelic());
         else {
            System.out.println(((Chamber)room).getPassagesAsString());
            System.out.println("\tDanger: " + ((Chamber)room).getDanger());
         }
         System.out.println();
      }
   }
   
   
   /** returns the relics (leaf nodes) of the labyrinth*/
   public Relic[] getRelics(){
      ArrayList<Relic> relics = new ArrayList<Relic>();
      for (ChamberI room: flatten()) if (room instanceof Relic) 
         relics.add((Relic)room);
      return relics.toArray(new Relic[0]);
   }
}