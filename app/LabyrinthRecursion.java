import java.util.ArrayList;

/** Helper class to group the recursive labyrinth traversal methods and enfore encapsulation.
@author Lincoln*/
class LabyrinthRecursion{
   private static final int MAX_RECURSION_DEPTH = 10;
   
   
   /** Sets up and calls the recursive method.
   @param maxRecusionDepth How many layers to go through before deciding something's gone wrong.*/
   public static void updateReachableRooms(ArrayList<ChamberI> rooms){
      //This is insurance for if the labyrinth is being actively edited. Old paths won't clog it up.
      for (ChamberI room: rooms){  
         room.setPath(null); 
      }
      //Call recursive method with correct arguments.
      traverseLabyrinth(MAX_RECURSION_DEPTH, rooms.get(0), new Path(-1, new ArrayList<String>())); 
   }
   
   
   /** Recursion helper method. The signature is a mess and it requires all of the nodes have null path values before starting. Should ONLY EVER be called from updateReachableRooms(). 
   @param maxRecusionDepth How many layers to go through before deciding something's gone wrong.
   @param room The current node in the labyrinth.
   @param pathSoFar Takes an instance of the Path class to keeps a record of the nodes traversed to get here.*/
   private static void traverseLabyrinth(int maxRecursionDepth, ChamberI room, Path pathSoFar){
      if (pathSoFar.isOpen() || (!pathSoFar.isOpen() && !room.getReachable())) {
         room.setPath(pathSoFar);  //This instance of path does not get passed down. Is safe to assign.
      }
      if (maxRecursionDepth < 0) { //Check nothing has gone horribly wrong. Shouldn't trigger.
         System.out.println("Error too much recursion");
      }
      else if (room instanceof Chamber) { //If this node connects to others --> call on each new node.
         for (ChamberI nextRoom: ((Chamber)room).getPassages()) {
            traverseLabyrinth(maxRecursionDepth - 1, nextRoom, pathSoFar.addStep(room.getRoomName(), ((Chamber)room).getDanger()));
         }
      }
   }
}