import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Supplier;

/** Stores one labyrinth. Because the labyrinth structure is a directed graph, the terms node/chamber/room and root/entrance/first are used interchangeably in comments and variable names.
Eww, have I split off so much functionality that this is a data class now??
@author Lincoln*/
public class Labyrinth{
   
   /** A reference to the root/entrance node of the labyrinth*/
   private ChamberI entrance;
   
   
   /** Main constructor. Reads in a labyrinth from file then calls recursion to calculate reachable rooms.
   @param filename where to load the labyrinth from.*/
   public Labyrinth(String filename){
      entrance = LabyrinthIO.readFile(filename); //read in the saved labyrinth
      LabyrinthRecursion.updateReachableRooms(flatten()); //Call the recursion class to calculate which rooms are reachable.
   }
   
   
   /** Small helper method that puts all roooms into a 1D ArrayList. Useful when needing to iterating through nodes.
   @return an arraylist of all the nodes in the labyrinth once each*/
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
   
   
   /** Prints the nodes of the labyrinth to System.out*/
   public void display(){ //FIX ME Do I belong in the final app or just for debug?
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
   
   
   /** returns all the relics (leaf nodes) in the labyrinth
   @return Relic[] with each relic once.*/
   public Relic[] getRelics(){
      ArrayList<Relic> relics = new ArrayList<Relic>();
      for (ChamberI room: flatten()) if (room instanceof Relic) 
         relics.add((Relic)room);
      return relics.toArray(new Relic[0]);
   }
}