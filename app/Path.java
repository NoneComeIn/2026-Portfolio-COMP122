import java.util.ArrayList;
import java.lang.StringBuilder;

/** 
@author Lincoln*/
class Path{
   String blockedNode;
   int maxDanger;
   ArrayList<String> path;
   
   public Path(int maxDanger, ArrayList<String> path){
      this.maxDanger = maxDanger;
      this.path = path;
   }
   public Path(String blockedNode, int maxDanger, ArrayList<String> path){
      this(maxDanger, path);
      this.blockedNode = blockedNode;
   }
   
   public boolean isOpen(){
      return blockedNode == null;
   }
   public Path addStep(String name, int danger){ //Could take a ChamberI, but I wanted to avoid circular referencing for if I had to change java versions and rebuild everything again.
      if (isOpen() && danger > 100)
         blockedNode = name;
      if (danger > maxDanger)
         maxDanger = danger;
      ArrayList<String> newPath = new ArrayList<String>(path);
      newPath.add(name);
      return new Path(blockedNode, maxDanger, newPath); //IMPORTANT: returns a new path.  
   }
   public String toString(){
      StringBuilder output = new StringBuilder();
      for (String node: path){
         output.append(node);
         output.append(" --> ");
      }
      return output.toString();
   }
   public String getStats(){
      if (blockedNode == null) return "".format("Max Danger: %d (≤ 100)", maxDanger);
      else return "".format("Blocked Node: %s (%d > 100)",  blockedNode, maxDanger);
   }
}