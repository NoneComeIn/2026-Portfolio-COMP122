import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Supplier;

/** Stores one labyrinth. Because the labyrinth structure is a directed graph, the terms node/chamber/room and root/entrance/first are used interchangeably in comments and variable names.
Eww, have I split off so much functionality that this is a data class now??
@author Lincoln*/
public class Labyrinth{
   
   /** A reference to the root/entrance node of the labyrinth*/
   private Chamber entrance;
   
   
   /** Main constructor. Reads in a labyrinth from file then calls recursion to calculate reachable rooms.
   @param filename where to load the labyrinth from.*/
   public Labyrinth(String filename){
      entrance = LabyrinthIO.readFile(filename); //read in the saved labyrinth
      LabyrinthRecursion.updateReachableRooms(entrance); //Call the recursion class to calculate which rooms are reachable.
   }
   
   
   /** Small helper method that puts all roooms into a 1D ArrayList. Useful when needing to iterating through nodes.
   @return an arraylist of all the nodes in the labyrinth once each*/
   private ArrayList<Chamber> flatten(){
      ArrayList<Chamber> toVisit = new ArrayList<Chamber>();
      ArrayList<Chamber> visited = new ArrayList<Chamber>();
      toVisit.add(entrance);
      for (int i = 0; !toVisit.isEmpty(); i++){ 
         Chamber room = toVisit.get(0);
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
      ArrayList<Chamber> rooms = flatten();
      for (Chamber room: rooms){
         System.out.println(room);
         System.out.println("\tPath: " + room.getPath());
         System.out.println("\t\t" +room.getPathStats());
         if (room.hasConnections()) System.out.println(room.getPassagesAsString());
         if (room.getContent() instanceof Danger) System.out.println(room.getContent());
         if (room.getContent() instanceof Relic) System.out.println("Contains a" + room.getContent());
         System.out.println();
      }
   }
   
   
   /** returns all the relics (leaf nodes) in the labyrinth
   @return Relic[] with each relic once.*/
   public Relic[] getRelics(){
      ArrayList<Relic> relics = new ArrayList<Relic>();
      for (Chamber room: flatten()) if (room.getContent() instanceof Relic) 
         relics.add((Relic)room.getContent());
      return relics.toArray(new Relic[0]);
   }
}