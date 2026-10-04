import java.util.ArrayList;

/** Helper class to group the recursive labyrinth traversal methods and enfore encapsulation.
@author Lincoln*/
class LabyrinthRecursion{
   /**MAX_RECURSION_DEPTH Static constant for how many layers to go through before deciding something's gone wrong.*/
   private static final int MAX_RECURSION_DEPTH = 10;
   
   
   /** Calls the recursive method with the correct arguments.*/
   public static void updateReachableRooms(Chamber entrance){
      //Call recursive method with correct arguments.
      traverseLabyrinth(MAX_RECURSION_DEPTH, entrance, new Path(-1, new ArrayList<String>())); 
   }
   
   
   /** Recursion helper method. The signature is a mess and it requires all of the nodes have null path values before starting. Should ONLY EVER be called from updateReachableRooms(). 
   @param maxRecusionDepth How many layers to go through before deciding something's gone wrong.
   @param room The current node in the labyrinth.
   @param pathSoFar Takes an instance of the Path class to keeps a record of the nodes traversed to get here.*/
   private static void traverseLabyrinth(int maxRecursionDepth, Chamber room, Path pathSoFar){
      if (pathSoFar.isOpen() || (!pathSoFar.isOpen() && !room.getReachable())) {
         room.setPath(pathSoFar);  //This instance of path does not get passed down. Is safe to assign.
      }
      for (Chamber nextRoom: room.getPassages()) if (room.getContent() instanceof Danger){
         traverseLabyrinth(maxRecursionDepth - 1, nextRoom, pathSoFar.addStep(room.getRoomName(), ((Danger)room.getContent()).getDanger())); //pathSoFar.addStep() returns a modified copy of pathSoFar.
      }
   }
}