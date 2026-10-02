import java.util.ArrayList;
import java.lang.StringBuilder;

/** Chamber class
@author Lincoln*/
public class Chamber implements ChamberI{
   /** The danger level of this node in the labyrinth*/
   private int danger;
   /** The name of this node of the labyrinth*/
   private String name;
   /** passages A list of all connected rooms in the labyrinth. 
   Note that the labyrinth is a is a directed graph. Connections only go one way. The graph must not be cyclic.*/
   private ArrayList<ChamberI> passages = new ArrayList<ChamberI>();
   /**path A private Path object that holds the names of intermediate nodes from the labyrinth entrance to here, plus miscellaneous path data. Does not record its own node.*/
   private Path path;
   
   
   /** Main constructor*/
   public Chamber(String name, int danger){
      this.name = name;
      this.danger = danger;
   }
   
   
   // Getters
   
   /** Calcuates if this node is reachable by traversing the stored path. If path is null, returns false.
   @return reachable*/
   public boolean getReachable(){
      return path != null && path.isOpen();
   }
   /** returns the danger value of this node of the labyrinth
   @return danger The danger of this node*/
   public int getDanger(){
      return danger;
   }
   /** returns the list of connected passages as an immutable array to prevent sideeffects
   @return passages the connected nodes of the labyrinth.*/
   public ChamberI[] getPassages(){
      return passages.toArray(new ChamberI[0]);
   }
   /** Returns the name of the labyrinth node.
   @return roomName*/
   public String getRoomName(){
      return name;
   }
   
   
   // Mutators
   
   /** connections mutator because when reading from a file the connected nodes cannot be constructed until after every node has been declared and the constructor has exitted.
   @param neighbour the ChamberI object to be included in passages.*/
   public void addConnection(ChamberI neighbour){
      passages.add(neighbour);
   }
   
   
   // Setters
   
   /** Path setter because the path cannot be calculated until after the constructor ended.
   @param path the path object containing the route to relic from the entrance.*/
   public void setPath(Path path){
      this.path = path;
   }
   
   
   //toString() variants
   
   /** Returns the path to this node as a formatted string. Also exists to enforce encapsulation.
   @return A string representation of the stored path.*/
   public String getPath(){
      return "".format("\tPath: %s%s\n\t\t%s", path, name, path.getStats()); 
   }
   /** Returns the passages of this node as a formatted string.
   @return A string representation of this node's connections*/
   public String getPassagesAsString(){
      StringBuilder output = new StringBuilder();
      output.append("\tConnecting passages lead to:");
      for (ChamberI room: passages){
         output.append("\n\t\tRoom ");
         output.append(room.getRoomName());
      }
      return output.toString();
   }
   /** Returns the labyrinth related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String toString(){
      return "".format("Room %s is a%sreachable chamber: ", name, getReachable() ? " " : "n un");
   }
   /** Returns the information of this node as a string formatted for a save file
   @return A save compatible string representation of the relic.*/
   public String toSaveString(){
      return "".format("false %s %d", name, danger);
   }
}
