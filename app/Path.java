import java.util.ArrayList;
import java.lang.StringBuilder;

/** Small data class that builds and stores a string representation of the path travelled by the recursive labyrinth traversal method.
@author Lincoln*/
class Path{
   /** Stores the name of the forst blocked node on the path. A null value is interpretted to mean the pat is fully traversable*/
   String blockedNode;
   /** The maximum danger encountered along the path*/
   int maxDanger;
   /** The list of nodes making up the path*/
   ArrayList<String> path;
   
   
   // Constructors
   
   /** Main constructor. Also used to make copies.*/
   public Path(int maxDanger, ArrayList<String> path){
      this.maxDanger = maxDanger;
      this.path = path;
   }
   /** Copy constructor that turns a traversable path into a blocked path.*/
   public Path(String blockedNode, int maxDanger, ArrayList<String> path){
      this(maxDanger, path);
      this.blockedNode = blockedNode;
   }
   
   
   //Mutator, I think?
   
   /** Returns a modified deep copy of path. 
   @param name The name of the node to be added to the path. 
   @param danger The danger level of the node to be added to the path.
   @return modified deep copy of path*/
   public Path addStep(String name, int danger){ 
      //Could take a ChamberI, but I wanted to avoid circular referencing for when I have to change java versions and rebuild everything again.
      if (isOpen() && danger > 100) {
         blockedNode = name;
         maxDanger = danger;
      }
      else if (danger > maxDanger) {
         maxDanger = danger;
      }
      ArrayList<String> newPath = new ArrayList<String>(path);
      newPath.add(name);
      return new Path(blockedNode, maxDanger, newPath); //IMPORTANT: returns a new path. Does NOT modify in place.
   }


   // Getters
   
   /** returns whether the path contains a blocked node or can can be safely traversed by adventurers.
   @return true means the path is not blocked*/
   public boolean isOpen(){
      return blockedNode == null;
   }
   
   
   // toString variants
   
   /** Returns a string representation of the path.
   @return a string representation of the path*/
   public String toString(){
      StringBuilder output = new StringBuilder();
      for (String node: path){
         output.append(node);
         output.append(" --> ");
      }
      return output.toString();
   }
   /** Returns a string with supplementary path data.
   @return Max danger and name of any blocked node.*/
   public String getStats(){
      if (blockedNode == null) return "".format("Max Danger: %d (≤ 100)", maxDanger);
      else return "".format("Blocked Node: %s (%d > 100)",  blockedNode, maxDanger);
   }
}